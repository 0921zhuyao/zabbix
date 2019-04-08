package com.jd.framework.activiti.controller;

import com.jd.framework.activiti.vo.DeploymentVo;
import com.jd.framework.web.domain.Result;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程部署
 */
@Controller
@RequestMapping("/activiti/deploy")
public class DeploymentController {

    private String prefix = "activiti";

    @Autowired
    RepositoryService repositoryService;

    @GetMapping("/modelList")
    @RequiresPermissions("activiti:deploy:view")
    public String modelList() {
        return prefix + "/deployList";
    }

    @RequestMapping("get/{id}")
    @ResponseBody
    @RequiresPermissions("activiti:deploy:view")
    public Result getOne(@PathVariable("id") String id) {
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(id).singleResult();
        return Result.success().put("data", new DeploymentVo(deployment));
    }

    @RequestMapping("list")
    @ResponseBody
    @RequiresPermissions("activiti:deploy:view")
    public Result getList(Integer pageSize, Integer pageNum,String modelId) {
        //根据模型id查询流程部署列表
        List<Deployment> deployments = repositoryService.createDeploymentQuery().listPage(pageSize * (pageNum - 1), pageSize);
        long count = repositoryService.createDeploymentQuery().count();
        List<DeploymentVo> list = new ArrayList<>();
        for (Deployment deployment : deployments) {
            list.add(new DeploymentVo(deployment));
        }
        return Result.success().put("total", count).put("rows", list);
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    @RequiresPermissions("activiti:deploy:delete")
    public Result deleteOne(@PathVariable("id") String id) {
        repositoryService.deleteDeployment(id);
        return Result.success();
    }
}
