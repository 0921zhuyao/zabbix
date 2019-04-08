package com.jd.framework.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jd.common.exception.user.CaptchaException;
import com.jd.common.exception.user.RoleBlockedException;
import com.jd.common.exception.user.UserBlockedException;
import com.jd.common.exception.user.UserNotExistsException;
import com.jd.common.exception.user.UserPasswordNotMatchException;
import com.jd.common.exception.user.UserPasswordRetryLimitExceedException;
import com.jd.common.utils.security.ShiroUtils;
import com.jd.framework.shiro.service.LoginService;
import com.jd.project.system.menu.service.IMenuService;
import com.jd.project.system.role.service.IRoleService;
import com.jd.project.system.user.domain.User;
import com.jd.project.system.user.service.IUserService;



public class AppRealm extends AuthorizingRealm {

	private static final Logger log = LoggerFactory.getLogger(AppRealm.class);

	  @Autowired
	    private IMenuService menuService;

	    @Autowired
	    private IRoleService roleService;

	    @Autowired
	    private LoginService loginService;
	    
	    
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		 Long userId = ShiroUtils.getUserId();
	        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	        // 角色加入AuthorizationInfo认证对象
	        info.setRoles(roleService.selectRoleKeys(userId));
	        // 权限加入AuthorizationInfo认证对象
	        info.setStringPermissions(menuService.selectPermsByUserId(userId));
	        return info;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        
        User user = null;
        try {
            user = loginService.loginById(upToken.getUsername());
		} catch (CaptchaException e) {
			throw new AuthenticationException(e.getMessage(), e);
		} catch (UserNotExistsException e) {
			throw new UnknownAccountException(e.getMessage(), e);
		} catch (UserPasswordNotMatchException e) {
			throw new IncorrectCredentialsException(e.getMessage(), e);
		} catch (UserPasswordRetryLimitExceedException e) {
			throw new ExcessiveAttemptsException(e.getMessage(), e);
		} catch (UserBlockedException e) {
			throw new LockedAccountException(e.getMessage(), e);
		} catch (RoleBlockedException e) {
			throw new LockedAccountException(e.getMessage(), e);
		} catch (Exception e) {
			log.info("对用户[" + upToken.getUsername() + "]进行登录验证..验证未通过{}", e.getMessage());
			throw new AuthenticationException(e.getMessage(), e);
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, upToken.getUsername(), getName());
		return info;
	}

	/**
	 * 清理缓存权限
	 */
	public void clearCachedAuthorizationInfo() {
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}

}
