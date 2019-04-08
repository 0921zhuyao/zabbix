package com.jd.project.system.sysorg.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jd.project.system.sysorg.entity.SysorgEntity;

/**
 * 组织机构 服务层
 * 
 * @author xml
 * @date 2018-11-27
 */
public interface SysorgService {
	/**
	 * 查询组织机构信息
	 * 
	 * @param orgid
	 *            组织机构ID
	 * @return 组织机构信息
	 */
	public SysorgEntity selectsysorgById(String orgid);

	/**
	 * 查询组织机构列表
	 * 
	 * @param sysorg
	 *            组织机构信息
	 * @return 组织机构集合
	 */
	public List<SysorgEntity> selectsysorgList(SysorgEntity sysorg);

	/**
	 * 新增组织机构
	 * 
	 * @param sysorg
	 *            组织机构信息
	 * @return 结果
	 */
	public int insertsysorg(SysorgEntity sysorg);

	/**
	 * 修改组织机构
	 * 
	 * @param sysorg
	 *            组织机构信息
	 * @return 结果
	 */
	public int updatesysorg(SysorgEntity sysorg);

	/**
	 * 删除组织机构信息
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deletesysorgByIds(String ids);

	/**
	 * 查询组织机构树
	 *
	 * @return 所有组织机构信息
	 */
	List<Map<String, Object>> sysorgTreeData();

	/**
	 * 根据父节点查询所有子节点数据
	 *
	 * @param
	 * @return 结果 list
	 */
	public HashMap<String, Object> sysorgEntityChildren(String Id, SysorgEntity org, HashMap<String, Object> map);

	/**
	 * 根据ID查询子节点
	 *
	 * @param
	 */
	SysorgEntity selectOrgById(String orgId);

	/**
	 * 根据id查询SysOrgCode的值
	 *
	 * @param
	 * @return 结果 list
	 */
	SysorgEntity selectSysOrgCodeById(@Param("orgId") String orgId);
	
	/**
	 * 根据id查询自生及子SysOrgCode的值
	 *
	 * @param
	 * @return 结果 list
	 */
	List<String> selectSysOrgCodesById(@Param("orgId") String orgId);

	public int createOrgCode();

	List<Map<String, Object>> selectOrgTree();

	/**
	 * 同步莱斯组织信息
	 * 
	 * @param syncData
	 * @return
	 */
	public int modifyLsOrg(List<SysorgEntity> syncData);
}
