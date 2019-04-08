package com.jd.framework.activiti.controller;

import com.jd.common.utils.StringUtils;
import com.jd.framework.web.domain.Result;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模型管理
 */
@Controller
@RequestMapping("/activiti/models")
public class ModelController {

    private String prefix = "activiti";

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/modelList")
    @RequiresPermissions("activiti:models:view")
    public String modelList() {
        return prefix + "/modelList";
    }


    @GetMapping("/add")
    @RequiresPermissions("activiti:models:add")
    public String add(ModelMap mmap) {
        return prefix + "/modelForm";
    }

    /**
     * 新建一个空模型
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("addOrUpdateModel")
    @RequiresPermissions(value = {"activiti:models:add", "activiti:models:update"}, logical = Logical.OR)
    @ResponseBody
    public Result newModel(String id, String modelName, String description) throws UnsupportedEncodingException {
        //初始化一个空模型
        Model model = StringUtils.isEmpty(id) ? repositoryService.newModel() : repositoryService.getModel(id);

        //设置一些默认信息
        int revision = model.getVersion() == null ? 1 : (model.getVersion().intValue() + 1);
        String key = "model-" + modelName;

        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, modelName);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(modelName);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        String modelId = model.getId();

        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(modelId, editorNode.toString().getBytes("utf-8"));

        return Result.success().put("modelId", modelId);
    }

    @RequestMapping("edit/{id}")
    @RequiresPermissions("activiti:models:update")
    public String edit(@PathVariable("id") String id, ModelMap modelMap) {
        try {
            Model model = repositoryService.createModelQuery().modelId(id).singleResult();
            JsonNode jsonNode = objectMapper.readTree(model.getMetaInfo());
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("modelName", model.getName());
            map.put("description", jsonNode.get("description").textValue());
            modelMap.put("model", map);
        } catch (IOException e) {
            throw new RuntimeException("获取工作流模型数据失败~");
        }
        return prefix + "/modelForm";
    }

    @RequestMapping("get/{id}")
    @RequiresPermissions("activiti:models:view")
    @ResponseBody
    public Result getModel(@PathVariable("id") String id) {
        try {
            Model model = repositoryService.createModelQuery().modelId(id).singleResult();
            JsonNode jsonNode = objectMapper.readTree(model.getMetaInfo());
            return Result.success().put("modelName", model.getName()).put("description", jsonNode.get("description"));
        } catch (IOException e) {
            throw new RuntimeException("获取工作流模型数据失败~");
        }
    }

    @RequestMapping("list")
    @RequiresPermissions("activiti:models:view")
    @ResponseBody
    public Result list(Integer pageSize, Integer pageNum, String modelName) {
        ModelQuery modelQuery = repositoryService.createModelQuery();
        if (modelName != null) {
            modelQuery.modelNameLike("%" + modelName + "%");
        }
        List<Model> modelList = modelQuery.listPage(pageSize * (pageNum - 1), pageSize);
        long count = modelQuery.count();
        return Result.success().put("total", count).put("rows", modelList);
    }

    @RequestMapping("delete")
    @RequiresPermissions("activiti:models:delete")
    @ResponseBody
    public Result delete(String ids) {
        String[] idArrays = ids.split(",");
        for (String id : idArrays) {
            repositoryService.deleteModel(id);
        }
        return Result.success();
    }


    /**
     * 发布模型为流程定义
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/deploy/{id}")
    @RequiresPermissions("activiti:models:deploy")
    @ResponseBody
    public Result deploy(@PathVariable("id") String id) throws Exception {
        //获取模型
        Model modelData = repositoryService.getModel(id);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
        if (bytes == null) {
            return Result.error("模型数据为空，请先设计流程并成功保存，再进行发布。");
        }
        JsonNode modelNode = new ObjectMapper().readTree(bytes);
        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if (model.getProcesses().size() == 0) {
            return Result.error("数据模型不符要求，请至少设计一条主线流程。");
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        try {
            //发布流程
            String modelName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
                    .addString(modelName, new String(bpmnBytes, "UTF-8")).deploy();
            modelData.setDeploymentId(deployment.getId());
            repositoryService.saveModel(modelData);
            return Result.success("流程发布成功~");
        } catch (Exception e) {
            return Result.error("流程发布失败，请确保流程图符合bpmn2.0标准~");
        }
    }


}
