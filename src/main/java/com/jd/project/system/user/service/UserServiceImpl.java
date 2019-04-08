package com.jd.project.system.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jd.common.support.Convert;
import com.jd.common.utils.StringUtils;
import com.jd.framework.config.ProjectPropertiesConfig;
import com.jd.framework.shiro.service.PasswordService;
import com.jd.project.system.role.domain.Role;
import com.jd.project.system.role.mapper.RoleMapper;
import com.jd.project.system.user.domain.User;
import com.jd.project.system.user.domain.UserRole;
import com.jd.project.system.user.mapper.UserMapper;
import com.jd.project.system.user.mapper.UserRoleMapper;

/**
 * 用户 业务层处理
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private PasswordService passwordService;

	@Autowired
	private IUserService userService;

	/**
	 * 根据条件分页查询用户对象
	 * 
	 * @param user
	 *            用户信息
	 * @return 用户信息集合信息
	 */
	@Override
	public List<User> selectUserList(User user) {
		return userMapper.selectUserList(user);
	}

	/**
	 * 通过用户名查询用户
	 * 
	 * @param userName
	 *            用户名
	 * @return 用户对象信息
	 */
	@Override
	public User selectUserByLoginName(String userName) {
		return userMapper.selectUserByLoginName(userName);
	}

	/**
	 * 通过手机号码查询用户
	 * 
	 * @return 用户对象信息
	 */
	@Override
	public User selectUserByPhoneNumber(String phoneNumber) {
		return userMapper.selectUserByPhoneNumber(phoneNumber);
	}

	/**
	 * 通过邮箱查询用户
	 * 
	 * @param email
	 *            邮箱
	 * @return 用户对象信息
	 */
	@Override
	public User selectUserByEmail(String email) {
		return userMapper.selectUserByEmail(email);
	}

	/**
	 * 通过用户ID查询用户
	 * 
	 * @param userId
	 *            用户ID
	 * @return 用户对象信息
	 */
	@Override
	public User selectUserById(Long userId) {
		return userMapper.selectUserById(userId);
	}

	/**
	 * 通过用户ID删除用户
	 * 
	 * @param userId
	 *            用户ID
	 * @return 结果
	 */
	@Override
	public int deleteUserById(Long userId) {
		userRoleMapper.deleteUserRoleByUserId(userId);
		return userMapper.deleteUserById(userId);
	}

	/**
	 * 批量删除用户信息
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteUserByIds(String ids) throws Exception {
		Long[] userIds = Convert.toLongArray(ids);
		for (Long userId : userIds) {
			if (User.isAdmin(userId)) {
				throw new Exception("不允许删除超级管理员用户");
			}
		}
		return userMapper.deleteUserByIds(userIds);
	}

	/**
	 * 新增保存用户信息
	 * 
	 * @param user
	 *            用户信息
	 * @return 结果
	 */
	@Override
	public int insertUser(User user) {
		user.randomSalt();
		user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
		// user.setCreateBy(ShiroUtils.getLoginName());
		// 新增用户信息
		int rows = userMapper.insertUser(user);
		if (StringUtils.isEmpty(user.getRoleIds())) {
			Long[] roleIds = { ProjectPropertiesConfig.getStreetRoleId() };
			user.setRoleIds(roleIds);
			if (user.getLsIsEnterprise() == 1L) {
				Long[] roleIds2 = { ProjectPropertiesConfig.getCompanyRoleId() };
				user.setRoleIds(roleIds2);
			}
		}

		// 新增用户与角色管理
		if (null != user.getRoleIds() && user.getRoleIds().length > 0)
			insertUserRole(user);
		return rows;
	}

	/**
	 * 修改保存用户信息
	 * 
	 * @param user
	 *            用户信息
	 * @return 结果
	 */
	@Override
	public int updateUser(User user) {
		Long userId = user.getUserId();
		// user.setUpdateBy(ShiroUtils.getLoginName());
		if (StringUtils.isEmpty(user.getRoleIds())) {
			Long[] roleIds = { ProjectPropertiesConfig.getStreetRoleId() };
			user.setRoleIds(roleIds);
			if (user.getLsIsEnterprise() == 1L) {
				Long[] roleIds2 = { ProjectPropertiesConfig.getCompanyRoleId() };
				user.setRoleIds(roleIds2);
			}
		}

		userRoleMapper.deleteUserRoleByUserId(userId);
		insertUserRole(user);
		return userMapper.updateUser(user);
	}

	/**
	 * 修改用户个人详细信息
	 *
	 * @param user
	 *            用户信息
	 * @return 结果
	 */
	@Override
	public int updateUserInfo(User user) {
		return userMapper.updateUser(user);
	}

	/**
	 * 修改用户密码
	 *
	 * @param user
	 *            用户信息
	 * @return 结果
	 */
	@Override
	public int resetUserPwd(User user) {
		user.randomSalt();
		user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
		return updateUserInfo(user);
	}

	/**
	 * 新增用户角色信息
	 *
	 * @param user
	 *            用户对象
	 */
	public void insertUserRole(User user) {
		// 新增用户与角色管理
		List<UserRole> list = new ArrayList<UserRole>();
		for (Long roleId : user.getRoleIds()) {
			UserRole ur = new UserRole();
			ur.setUserId(user.getUserId());
			ur.setRoleId(roleId);
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchUserRole(list);
		}
	}

	/**
	 * 校验用户名称是否唯一
	 *
	 * @param loginName
	 *            用户名
	 * @return
	 */
	@Override
	public boolean checkLoginNameUnique(String loginName) {
		int count = userMapper.checkLoginNameUnique(loginName);
		if (count > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 校验用户名称是否唯一
	 * 
	 * @return
	 */
	@Override
	public boolean checkPhoneUnique(User user) {
		Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
		User info = userMapper.checkPhoneUnique(user.getPhonenumber());
		if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
			return false;
		}
		return true;
	}

	/**
	 * 校验email是否唯一
	 * 
	 * @return
	 */
	@Override
	public boolean checkEmailUnique(User user) {
		Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
		User info = userMapper.checkEmailUnique(user.getEmail());
		if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
			return false;
		}
		return true;
	}

	/**
	 * 查询用户所属角色组
	 *
	 * @param userId
	 *            用户ID
	 * @return 结果
	 */
	@Override
	public String selectUserRoleGroup(Long userId) {
		List<Role> list = roleMapper.selectRolesByUserId(userId);
		StringBuffer idsStr = new StringBuffer();
		for (Role role : list) {
			idsStr.append(role.getRoleName()).append(",");
		}
		if (StringUtils.isNotEmpty(idsStr.toString())) {
			return idsStr.substring(0, idsStr.length() - 1);
		}
		return idsStr.toString();
	}

	@Override
	public User selectUserByLsUserId(String lsUserId) {
		return userMapper.selectUserByLsUserId(lsUserId);
	}

	public List<User> selectUserList() {
		User user = new User();
		return userMapper.selectUserList(user);
	}

	@Override
	public int syncUserRole(List<JSONObject> syncData) {
		List<UserRole> insertList = new ArrayList<>();
		List<UserRole> deleteList = new ArrayList<>();
		// 清空表
		// userRoleMapper.cleanTable();
		int flag = 0;
		syncData.forEach(x -> {
			UserRole ur = new UserRole();
			ur.setLsUserId(x.getString("userid"));
			ur.setLsRoleId(x.getString("roleid"));
			// 关联出roleid和userid
			Role role = new Role();
			role.setLsRoleId(ur.getLsRoleId());
			List<Role> rlist = roleMapper.selectRoleList(role);
			User user = new User();
			user.setLsUserId(ur.getLsUserId());
			List<User> ulist = userMapper.selectUserList(user);
			if (rlist.size() > 0 && ulist.size() > 0) {
				ur.setRoleId(rlist.get(0).getRoleId());
				ur.setUserId(ulist.get(0).getUserId());
				insertList.add(ur);
			}
		});
		if (insertList.size() > 0) {
			flag = userRoleMapper.batchUserRole(insertList);
		}
		return flag;
	}
}
