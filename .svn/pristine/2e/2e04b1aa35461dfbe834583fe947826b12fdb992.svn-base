package com.jd.framework.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jd.common.utils.StringUtils;
import com.jd.common.utils.security.ShiroUtils;
import com.jd.framework.web.domain.BaseEntity;
import com.jd.framework.web.domain.Result;
import com.jd.framework.web.page.PageDomain;
import com.jd.framework.web.page.TableDataInfo;
import com.jd.framework.web.page.TableSupport;
import com.jd.project.system.user.domain.User;

/**
 * web层通用数据处理
 */
public class BaseController {
	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 设置请求分页数据
	 */
	protected void startPage() {
		PageDomain pageDomain = TableSupport.buildPageRequest();
		Integer pageNum = pageDomain.getPageNum();
		Integer pageSize = pageDomain.getPageSize();
		if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
			String orderBy = pageDomain.getOrderBy();
			PageHelper.startPage(pageNum, pageSize, orderBy);
		}
	}

	/**
	 * 设置请求分页数据
	 */
	protected void startPageDataQx(BaseEntity base) {
		PageDomain pageDomain = TableSupport.buildPageRequest();
		Integer pageNum = pageDomain.getPageNum();
		Integer pageSize = pageDomain.getPageSize();
		if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
			String orderBy = pageDomain.getOrderBy();
			PageHelper.startPage(pageNum, pageSize, orderBy);
		}
		setDataQx(base);
	}

	/**
	 * 设置请求分页数据
	 */
	protected void setDataQx(BaseEntity base) {
		User user = getUser();
		if (user == null) {
			return;
		}
		base.setDataQx(user.getDataQx());
		base.setDataQxIds(user.getDataQxIds());
	}

	/**
	 * 响应请求分页数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected TableDataInfo getDataTable(List<?> list) {
		TableDataInfo rspData = new TableDataInfo();
		rspData.setCode(0);
		rspData.setRows(list);
		rspData.setTotal(new PageInfo(list).getTotal());
		return rspData;
	}

	/**
	 * 响应返回结果
	 *
	 * @param rows
	 *            影响行数
	 * @return 操作结果
	 */
	protected Result toAjax(int rows) {
		return rows > 0 ? success() : error();
	}

	/**
	 * 返回成功
	 */
	public Result success() {
		return Result.success();
	}

	/**
	 * 返回失败消息
	 */
	public Result error() {
		return Result.error();
	}

	/**
	 * 返回成功消息
	 */
	public Result success(String message) {
		return Result.success(message);
	}

	/**
	 * 返回失败消息
	 */
	public Result error(String message) {
		return Result.error(message);
	}

	/**
	 * 返回错误码消息
	 */
	public Result error(int code, String message) {
		return Result.error(code, message);
	}

	public Result success(Object object) {
		return Result.success(object);
	}

	/**
	 * 页面跳转
	 */
	public String redirect(String url) {
		return StringUtils.format("redirect:{}", url);
	}

	public User getUser() {
		return ShiroUtils.getUser();
	}

	public void setUser(User user) {
		ShiroUtils.setUser(user);
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getLoginName() {
		return getUser().getLoginName();
	}
}
