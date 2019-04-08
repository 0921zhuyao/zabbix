package com.jd.project.utils;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AppFilterUtils implements Filter {

	//private IMemberinfoService memberinfoService;
	/**
	 * 封装，不需要过滤的list列表
	 */
	protected static Set<String> patterns = new HashSet<String>(){

	};

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//添加过滤白名单地址
		patterns.add("/app/login");
		System.out.println("appfilter init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		System.out.println("appfilter start");



		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

	 System.out.println("this is MyFilter,url :" + httpRequest.getRequestURI());
		if(isInclude(url)){
			chain.doFilter(httpRequest, httpResponse);
			return;
		}else{
			String token = httpRequest.getParameter("token");
//			if(token!=null && !"".equals(token)){
//
////				Memberinfo memberinfo = memberinfoService.selectMemberinfoByToken(token);
////				if(memberinfo!=null){
////					//注入member信息
////					httpRequest.setAttribute("memberinfo",memberinfo);
////					chain.doFilter(httpRequest, httpResponse);
////				}else{
////					//没有登陆信息重定向到logout地址
////					RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/app/logout");
////					dispatcher.forward(httpRequest, httpResponse);
////				}
//			}else{
//				RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/app/logout");
//				dispatcher.forward(httpRequest, httpResponse);
//			}
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
	}

	@Override
	public void destroy() {
		System.out.println("appfilter destroy");
	}


	/**
	 * 是否需要过滤
	 * @param url
	 * @return
	 */
	private boolean isInclude(String url) {
		if (patterns.contains(url)) {
			return true;
		}
		return false;
	}

}
