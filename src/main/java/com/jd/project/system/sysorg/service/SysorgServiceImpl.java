package com.jd.project.system.sysorg.service;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jd.common.support.Convert;
import com.jd.common.utils.security.ShiroUtils;
import com.jd.framework.web.service.BaseService;
import com.jd.project.system.sysorg.entity.SysorgEntity;
import com.jd.project.system.sysorg.mapper.SysorgMapper;

/**
 * 组织机构 服务层实现
 * 
 * @author xml
 * @date 2018-11-27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysorgServiceImpl extends BaseService implements SysorgService {
	@Autowired
	private SysorgMapper sysorgMapper;

	/**
	 * 查询组织机构信息
	 * 
	 * @param orgid
	 *            组织机构ID
	 * @return 组织机构信息
	 */
	@Override
	public SysorgEntity selectsysorgById(String orgid) {
		return sysorgMapper.selectsysorgById(orgid);
	}

	/**
	 * 查询所有数据
	 *
	 * @param
	 * @return 结果 list
	 */
	@Override
	public List<Map<String, Object>> sysorgTreeData() {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		List<SysorgEntity> menuList = sysorgMapper.selectSysorgAll();
		trees = getTrees(menuList, false, null, false);
		return trees;
	}

	/**
	 * 根据父节点查询所有子节点数据
	 *
	 * @param
	 * @return 结果 list
	 */
	@Cacheable(key = "#p1.orgid", value = "sysorgEntityChildren")
	@Override
	public HashMap<String, Object> sysorgEntityChildren(String Id, SysorgEntity org, HashMap<String, Object> map) {
		List<SysorgEntity> children = selectsysorgList(new SysorgEntity(Id));
		map.put("text", org.getOrgname());
		map.put("value", org.getOrgid());
		if (children.size() > 0) {
			List<HashMap<String, Object>> childList = new ArrayList<>();
			for (SysorgEntity child : children) {
				HashMap<String, Object> childMap = new HashMap<>();
				childList.add(sysorgEntityChildren(child.getOrgid(), child, childMap));
			}
			map.put("children", childList);
		} else {
			return map;
		}
		return map;
	}

	@Override
	public SysorgEntity selectOrgById(String orgId) {
		return sysorgMapper.selectOrgById(orgId);
	}

	@Override
	public SysorgEntity selectSysOrgCodeById(String orgId) {
		return sysorgMapper.selectSysOrgCodeById(orgId);
	}

	@Override
	public List<Map<String, Object>> selectOrgTree() {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		List<SysorgEntity> orgList = sysorgMapper.selectSysorgAll();
		for (SysorgEntity org : orgList) {
			Map<String, Object> orgMap = new HashMap<String, Object>();
			orgMap.put("id", org.getOrgid());
			orgMap.put("pId", org.getParent());
			orgMap.put("name", org.getOrgname());
			orgMap.put("title", org.getOrgname());
			trees.add(orgMap);
		}
		return trees;
	}

	/**
	 * 对象转菜单树
	 *
	 * @param sysorgList
	 *            菜单列表
	 * @param isCheck
	 *            是否需要选中
	 * @param roleMenuList
	 *            角色已存在菜单列表
	 * @param permsFlag
	 *            是否需要显示权限标识
	 * @return
	 */
	public List<Map<String, Object>> getTrees(List<SysorgEntity> sysorgList, boolean isCheck, List<String> roleMenuList,
			boolean permsFlag) {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		for (SysorgEntity menu : sysorgList) {
			Map<String, Object> deptMap = new HashMap<String, Object>();
			deptMap.put("id", menu.getOrgid());
			deptMap.put("pId", menu.getParent());
			deptMap.put("name", transMenuName(menu, roleMenuList, permsFlag));
			deptMap.put("title", menu.getOrgname());
			if (isCheck) {
				deptMap.put("checked", roleMenuList.contains(menu.getOrgid() + menu.getParent()));
			} else {
				deptMap.put("checked", false);
			}
			trees.add(deptMap);
		}
		return trees;
	}

	public String transMenuName(SysorgEntity sysorgEntity, List<String> roleMenuList, boolean permsFlag) {
		StringBuffer sb = new StringBuffer();
		sb.append(sysorgEntity.getOrgname());
		if (permsFlag) {
			sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + sysorgEntity.getParent() + "</font>");
		}
		return sb.toString();
	}

	/**
	 * 查询组织机构列表
	 *
	 * @param sysorg
	 *            组织机构信息
	 * @return 组织机构集合
	 */
	@Override
	public List<SysorgEntity> selectsysorgList(SysorgEntity sysorg) {
		return sysorgMapper.selectsysorgList(sysorg);
	}

	private void infoTreeMap(HashMap<String, Object> map, SysorgEntity org) {
		map.put("text", org.getOrgname());
		map.put("value", org.getOrgid());
		HashMap<String, Object> child = new HashMap<>();
		while (org.getChildren() != null && org.getChildren().size() > 0) {
			for (SysorgEntity cd : org.getChildren()) {
				infoTreeMap(child, cd);
			}
		}
		map.put("children", child);
	}

	/**
	 * 新增组织机构
	 * 
	 * @param sysorg
	 *            组织机构信息,删除缓存的数据
	 * @return 结果
	 */
	@CacheEvict(value = "sysorgEntityChildren")
	@Override
	public int insertsysorg(SysorgEntity sysorg) {
		return sysorgMapper.insertsysorg(sysorg);
	}

	/**
	 * 修改组织机构
	 * 
	 * @param sysorg
	 *            组织机构信息,删除缓存的数据
	 * @return 结果
	 */
	@Override
	@CacheEvict(value = "sysorgEntityChildren")
	public int updatesysorg(SysorgEntity sysorg) {
		return sysorgMapper.updatesysorg(sysorg);
	}

	/**
	 * 删除组织机构对象,删除缓存的数据
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	@CacheEvict(value = "sysorgEntityChildren")
	public int deletesysorgByIds(String ids) {
		return sysorgMapper.deletesysorgByIds(Convert.toStrArray(ids));
	}

	@Override
	public int createOrgCode() {
		//
		List<SysorgEntity> lst = sysorgMapper.selectsysorgTop();
		if (null != lst && lst.size() > 0) {
			for (SysorgEntity sysorgEntity : lst) {
				if (StringUtils.isEmpty(sysorgEntity.getOrg_code())) {
					sysorgEntity.setOrg_code(sysorgEntity.getOrgid() + "|");
					sysorgMapper.updateOrgCode(sysorgEntity);

					updateChildrenOrgCode(sysorgEntity.getOrgid(), sysorgEntity.getOrg_code());
				}
			}
		}
		return 0;
	}

	public void updateChildrenOrgCode(String parentOrgid, String parentOrgcode) {

		SysorgEntity sysorg = new SysorgEntity();
		sysorg.setParent(parentOrgid);
		// 获取所有的子项列表
		List<SysorgEntity> lst = sysorgMapper.selectsysorgList(sysorg);
		if (null != lst && lst.size() > 0) {
			for (SysorgEntity sysorgEntity : lst) {
				final String childrenCode = parentOrgcode + sysorgEntity.getOrgid() + "|";
				if (StringUtils.isEmpty(sysorgEntity.getOrg_code()) || !childrenCode.equals(childrenCode)) {
					sysorgEntity.setOrg_code(childrenCode);
					sysorgMapper.updateOrgCode(sysorgEntity);
				}
				updateChildrenOrgCode(sysorgEntity.getOrgid(), sysorgEntity.getOrg_code());
			}
		}
	}
	/**
	 * 同步莱斯组织信息
	 */
	@Override
	public int modifyLsOrg(List<SysorgEntity> syncData) {
		List<SysorgEntity> updateLsOrgs = new ArrayList<>();// 存储需要修改的莱斯组织信息
		List<SysorgEntity> insertLsOrgs = new ArrayList<>();// 存储需要新增的莱斯组织信息
		int flag = 0;
		List<String> orgids = sysorgMapper.selectOrgidList();
		syncData.forEach(x -> {
			// x.setUpdUser(ShiroUtils.getUserId());
			if (orgids.contains(x.getOrgid())) {
				updateLsOrgs.add(x);
			} else {
				x.setCrtUser(1L);
				insertLsOrgs.add(x);
			}
		});
		if (insertLsOrgs.size() > 0) {
			flag = sysorgMapper.insertLsOrg(insertLsOrgs);
		}
		if (updateLsOrgs.size() > 0) {
			flag = sysorgMapper.updateLsOrg(updateLsOrgs);
		}
		updateLsOrgs.clear();
		//更新组织机构编码 ORG_CODE 便于后期sql语句中用like查询 这里的ID是写死的 有风险 ，后期需找到更合适的方法
		SysorgEntity se = sysorgMapper.selectOrgById("c09cd1a7111f4f11b6046d880eef3af2");
		se.setOrg_code(se.getOrgid());
		sysorgEntityChildren(se,updateLsOrgs);
		if (updateLsOrgs.size() > 0) {
			flag = sysorgMapper.updateLsOrg(updateLsOrgs);
		}
		return flag;
	}
	//递归生成部门编码列表
	public SysorgEntity sysorgEntityChildren( SysorgEntity org, List<SysorgEntity> updateLsOrgs) {
		SysorgEntity seParam = new SysorgEntity();
		seParam.setParent(org.getOrgid());
		List<SysorgEntity> children = selectsysorgList(seParam);
		updateLsOrgs.add(org);
		if (children.size() > 0) {
			for (SysorgEntity child : children) {
				child.setOrg_code(org.getOrg_code() + "|" + child.getOrgid());
				sysorgEntityChildren(child, updateLsOrgs);
			}
		} else {
			return org;
		}
		return org;
	}

	@Override
	public List<String> selectSysOrgCodesById(String orgId) {
		SysorgEntity sysorg = selectSysOrgCodeById(orgId);
		SysorgEntity cnd = new SysorgEntity();
		cnd.setOrg_code(sysorg != null ? sysorg.getOrg_code() : null);
		if (!StringUtils.isEmpty(cnd.getOrg_code())) {
			List<SysorgEntity> org = selectsysorgList(cnd);
			List<String> orgCodes = new ArrayList<String>();
			org.forEach(x -> {
				orgCodes.add(x.getOrgid());
			});
			return orgCodes;
		}
		return null;
	}

}
