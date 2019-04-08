package com.jd.project.system.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jd.common.constant.ShiroConstants;
import com.jd.common.utils.ServletUtils;
import com.jd.common.utils.StringUtils;
import com.jd.framework.shiro.authc.LoginType;
import com.jd.framework.shiro.token.UserToken;
import com.jd.framework.web.controller.BaseController;
import com.jd.framework.web.domain.Result;

/**
 * 登录验证
 */
@Controller
public class LoginController extends BaseController {

    private static final String ADMIN_LOGIN_TYPE = LoginType.AMDIN.toString();
    		
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result ajaxLogin(String username, String password, Boolean rememberMe, HttpServletRequest request) {
    	UserToken token = new UserToken(username, password, ADMIN_LOGIN_TYPE);
    	token.setRememberMe(rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
        	String captchaErro = (String) request.getAttribute(ShiroConstants.CURRENT_CAPTCHA);
        	if(ShiroConstants.CAPTCHA_ERROR.equals(captchaErro)){
        		return error("验证码错误");
        	}
            subject.login(token);
            return success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "/error/unauth";
    }
}
