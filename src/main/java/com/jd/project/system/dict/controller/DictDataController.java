package com.jd.project.system.dict.controller;

import com.jd.common.utils.StringUtils;
import com.jd.common.utils.poi.ExcelUtil;
import com.jd.common.utils.security.ShiroUtils;
import com.jd.framework.aspectj.lang.annotation.Log;
import com.jd.framework.aspectj.lang.enums.BusinessType;
import com.jd.framework.web.controller.BaseController;
import com.jd.framework.web.domain.Result;
import com.jd.framework.web.page.TableDataInfo;
import com.jd.project.system.dict.domain.DictData;
import com.jd.project.system.dict.service.IDictDataService;
import com.jd.project.system.sysorg.entity.SysorgEntity;
import com.jd.project.system.sysorg.service.SysorgService;
import com.jd.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典信息
 */
@Controller
@RequestMapping("/system/dict/data")
public class DictDataController extends BaseController {
    private String prefix = "system/dict/data";

    @Autowired
    private IDictDataService dictDataService;
    @Autowired
    private SysorgService sysorgService;
    @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dictData() {
        return prefix + "/data";
    }

    @PostMapping("/list")
    @RequiresPermissions("system:dict:list")
    @ResponseBody
    public TableDataInfo list(DictData dictData) {
        startPage();
        List<DictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    @ResponseBody
    public Result export(DictData dictData) throws Exception {
        try {
            List<DictData> list = dictDataService.selectDictDataList(dictData);
            ExcelUtil<DictData> util = new ExcelUtil<DictData>(DictData.class);
            return util.exportExcel(list, "dictData");
        } catch (Exception e) {
            return error("导出Excel失败，请联系网站管理员！");
        }
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add/{dictType}")
    public String add(@PathVariable("dictType") String dictType, ModelMap mmap) {
        mmap.put("dictType", dictType);
        return prefix + "/add";
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/add")
    @ResponseBody
    public Result addSave(DictData dict) {
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改字典类型
     */
    @GetMapping("/edit/{dictCode}")
    public String edit(@PathVariable("dictCode") Long dictCode, ModelMap mmap) {
        mmap.put("dict", dictDataService.selectDictDataById(dictCode));
        return prefix + "/edit";
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PostMapping("/edit")
    @ResponseBody
    public Result editSave(DictData dict) {
        return toAjax(dictDataService.updateDictData(dict));
    }

    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dict:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Result remove(String ids) {
        return toAjax(dictDataService.deleteDictDataByIds(ids));
    }
    
    
    @GetMapping("/treeData/{dictType}")
    @ResponseBody
    public List<Map<String, Object>> treeDyFzData(@PathVariable("dictType") String dictType) {
        List<Map<String, Object>> tree = dictDataService.selectDictDataTree(dictType);
        return tree;
    }
    
    
    @GetMapping("/listData/{dictType}")
    @ResponseBody
    public  List<DictData> listData(@PathVariable("dictType") String dictType) {
        List<DictData> list = dictDataService.selectDictDataByType(dictType);
        return list;
    }

    @GetMapping("/JsonDate/{dictType}")
    @ResponseBody
    public  List<HashMap<String,Object>> JsonDate(@PathVariable("dictType") String dictType) {
        //subordinate_streets 为所属街道，需要单独从组织机构表查询(暂时写死，数据确定后可改成配置文件)
        if("subordinate_streets".equals(dictType)){
            User user = ShiroUtils.getUser();
            if (null != user) {
                String orgName="169";
                if (StringUtils.isNotEmpty(user.getDeptId())) {
                    SysorgEntity sysorgEntity=sysorgService.selectSysOrgCodeById(user.getDeptId());
                    if(Long.parseLong(sysorgEntity.getOrgid())>169L){
                        orgName=sysorgEntity.getOrgid();
                    }
                }
                SysorgEntity org=sysorgService.selectOrgById(orgName);
                HashMap<String,Object> result=new HashMap<>();
                List<HashMap<String,Object>> list=new ArrayList<>();
                list.add(sysorgService.sysorgEntityChildren(org.getOrgid(),org,result));
                return list;
            } else {
                return null;
            }
        }
        List<HashMap<String,Object>> list = dictDataService.selectDictDataByTypeForMobile(dictType);
        return list;
    }
}
