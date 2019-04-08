package com.jd.project.system.sysorg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jd.framework.web.mapper.BaseMapper;
import com.jd.project.system.sysorg.entity.SysorgEntity;

/**
 * 组织机构 数据层
 * 
 * @author xml
 * @date 2018-11-27
 */
public interface SysorgMapper extends BaseMapper<SysorgEntity> {
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
	 * 删除组织机构
	 *
	 * @param orgid
	 *            组织机构ID
	 * @return 结果
	 */
	public int deletesysorgById(String orgid);

	/**
	 * 批量删除组织机构
	 *
	 * @param orgids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deletesysorgByIds(String[] orgids);

	/**
	 * 查询所有数据
	 *
	 * @param
	 * @return 结果 list
	 */
	public List<SysorgEntity> selectSysorgAll();

	/**
	 * 根据ID查询子节点
	 *
	 * @param
	 * @return 结果 list
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
	 * 查询所有顶级数据
	 *
	 * @param
	 * @return 结果 list
	 */
	public List<SysorgEntity> selectsysorgTop();

	/**
	 * 修改组织机构编码
	 *
	 * @param sysorg
	 *            组织机构信息
	 * @return 结果
	 */
	public int updateOrgCode(SysorgEntity sysorg);

	/**
	 * 新增莱斯组织信息
	 * 
	 * @param syncData
	 * @return
	 */
	public int insertLsOrg(List<SysorgEntity> syncData);

	/**
	 * 修改同步的莱斯组织信息
	 * 
	 * @param updateLsOrgs
	 * @return
	 */
	public int updateLsOrg(List<SysorgEntity> updateLsOrgs);

	/**
	 * 查询组织ID
	 * 
	 * @return
	 */
	public List<String> selectOrgidList();

}