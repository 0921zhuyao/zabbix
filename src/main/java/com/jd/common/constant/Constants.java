package com.jd.common.constant;

/**
 * 通用常量信息
 */
public class Constants {
	/**
	 * UTF-8 字符集
	 */
	public static final String UTF8 = "UTF-8";

	/**
	 * 通用成功标识
	 */
	public static final String SUCCESS = "0";

	/**
	 * 通用失败标识
	 */
	public static final String FAIL = "1";

	/**
	 * 登录成功
	 */
	public static final String LOGIN_SUCCESS = "Success";

	/**
	 * 注销
	 */
	public static final String LOGOUT = "Logout";

	/**
	 * 登录失败
	 */
	public static final String LOGIN_FAIL = "Error";

	/**
	 * 自动去除表前缀
	 */
	public static String AUTO_REOMVE_PRE = "true";

	/**
	 * 当前记录起始索引
	 */
	public static String PAGE_NUM = "pageNum";

	/**
	 * 每页显示记录数
	 */
	public static String PAGE_SIZE = "pageSize";

	/**
	 * 排序列
	 */
	public static String ORDER_BY_COLUMN = "orderByColumn";

	/**
	 * 排序的方向 "desc" 或者 "asc".
	 */
	public static String IS_ASC = "isAsc";


	/**
	 *等级根节点code值
	 */
	public static String LEVEL_ROOT_CODE = "1";

	/**
	 * 删除标志 0 未删 1 已删
	 */
	public static Long IS_NOT_DELETE = 0L;

	/**
	 * 系统消息状态 0 未读 1 已读
	 */
	public static Long MASSAGE_NOT_READ = 0L;

	public static Long MASSAGE_READED = 1L;


	public static Long MASSAGE_SENDED = 1L;

	/**
	 * 字典职位类型
	 */
	public static String DICT_POSITION = "sys_position";

	/**
	 *  用户类型 0 非企业 ，1 企业用户
	 */
	public static Long ENTERPRISE_YES = 1L;

	public static Long ENTERPRISE_NO = 0L;

}
