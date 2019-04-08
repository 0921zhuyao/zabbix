package com.jd.project.system.sysorg.controller;

import com.jd.framework.aspectj.lang.annotation.Log;
import com.jd.framework.aspectj.lang.enums.BusinessType;
import com.jd.framework.web.controller.BaseController;
import com.jd.framework.web.domain.Result;
import com.jd.project.system.sysorg.entity.SysorgEntity;
import com.jd.project.system.sysorg.service.SysorgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 组织机构 信息操作处理
 * 
 * @author xml
 * @date 2018-11-27
 */
@Controller
@RequestMapping("/system/sysorg")
public class SysorgController extends BaseController{
    private String prefix = "system/sysorg";
	
	@Autowired
	private SysorgService sysorgService;
	
	@RequiresPermissions("system:sysorg:view")
	@GetMapping()
	public String initList() {
	    return prefix + "/sysorg_list";
	}
	
	/**
	 * 查询组织机构列表
	 */
	@RequiresPermissions("system:sysorg:list")
	@GetMapping("/query")
	@ResponseBody
	public List<SysorgEntity> list(SysorgEntity sysorg) {
		List<SysorgEntity> list = sysorgService.selectsysorgList(sysorg);
		return list;
	}

	/**
	 * 新增组织机构
	 */
	@GetMapping("/initAdd")
	public String initAdd(ModelMap mmap) {
	    mmap.put("sysorg",new SysorgEntity());
	    return prefix + "/sysorg_edit";
	}
	
	/**
	 * 新增保存组织机构
	 */
	@RequiresPermissions("system:sysorg:add")
	@Log(title = "组织机构", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public Result add(SysorgEntity sysorg) {
		return toAjax(sysorgService.insertsysorg(sysorg));
	}

	/**
	 * 修改组织机构
	 */
	@GetMapping("/initEdit/{orgid}")
	public String initEdit(@PathVariable("orgid") String orgid, ModelMap mmap) {
		SysorgEntity sysorg = sysorgService.selectsysorgById(orgid);
		mmap.put("sysorg", sysorg);
	    return prefix + "/sysorg_edit";
	}
	
	/**
	 * 修改保存组织机构
	 */
	@RequiresPermissions("system:sysorg:edit")
	@Log(title = "组织机构", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public Result edit(SysorgEntity sysorg) {
		return toAjax(sysorgService.updatesysorg(sysorg));
	}
	
	/**
	 * 删除组织机构
	 */
	@RequiresPermissions("system:sysorg:remove")
	@Log(title = "组织机构", businessType = BusinessType.DELETE)
	@PostMapping( "/del")
	@ResponseBody
	public Result del(String ids) {		
		return toAjax(sysorgService.deletesysorgByIds(ids));
	}

	/**
	 * 选择部门树
	 */
	@GetMapping("/selectSysorgTree/{orgid}")
	public String selectDeptTree(@PathVariable("orgid") String orgid, ModelMap mmap) {
		mmap.put("org", sysorgService.selectsysorgById(orgid));
		return prefix + "/tree";
	}

	/**
	 * 加载部门列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData() {
		List<Map<String, Object>> tree = sysorgService.sysorgTreeData();
		return tree;
	}

	/**
	 * 查看详情
	 * @param orgId
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("system:sysorg:detail")
	@GetMapping("/detail/{orgId}")
	public String detail(@PathVariable("orgId") String orgId, ModelMap mmap) {
		mmap.put("org", sysorgService.selectsysorgById(orgId));
		return prefix + "/detail";
	}
	
	
	/**
	 * 新增保存组织机构
	 */
	//@RequiresPermissions("system:sysorg:add")
	@Log(title = "组织机构编码生成", businessType = BusinessType.INSERT)
	@PostMapping("/createOrgCode")
	@ResponseBody
	public Result createOrgCode() {
		return toAjax(sysorgService.createOrgCode());
	}

}
