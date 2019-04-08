package com.jd.framework.shiro.service;

import com.jd.common.constant.ShiroConstants;
import com.jd.common.constant.UserConstants;
import com.jd.common.exception.user.CaptchaException;
import com.jd.common.exception.user.UserBlockedException;
import com.jd.common.exception.user.UserNotExistsException;
import com.jd.common.utils.DateUtils;
import com.jd.common.utils.ServletUtils;
import com.jd.common.utils.security.ShiroUtils;
import com.jd.project.system.user.domain.User;
import com.jd.project.system.user.domain.UserStatus;
import com.jd.project.system.user.service.IUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录service
 */
@Component
public class LoginService {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
//        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
//            throw new CaptchaException();
//        }
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new UserNotExistsException();
        }
        User user = userService.selectUserByLoginName(username);
        if (user == null && username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN)) {
            user = userService.selectUserByPhoneNumber(username);
        }
        if (user == null && username.matches(UserConstants.EMAIL_PATTERN)) {
            user = userService.selectUserByEmail(username);
        }
        if (user == null || UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            throw new UserNotExistsException();
        }
        passwordService.validate(user, password);
        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            throw new UserBlockedException(user.getRemark());
        }

     //   setUserDepartQx(user);
        
        recordLoginInfo(user);
        return user;
    }

//    public void setUserDepartQx(User user) {
//        Map<String, Object> data = new HashMap<>();
//        data.put("userId", user.getUserId());
//        data.put("departIds",  StringUtils.split(user.getDepartIds(), "\\|"));
//        
//        List<String> dataQxIds = userService.selectUserDepartQx(data);
//        
//        user.setDataQx(dataQxIds == null || dataQxIds.size() <= 0?"null": dataQxIds.get(dataQxIds.size()-1));
//        user.setDataQxIds(dataQxIds);
//    }
    /**
     * 记录登录信息
     * @param user
     */
    public void recordLoginInfo(User user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
    
    
    
    
    public User loginById(String username) {
//      if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
//          throw new CaptchaException();
//      }
      if (StringUtils.isEmpty(username) ) {
          throw new UserNotExistsException();
      }
      User user = userService.selectUserByLsUserId(username);
      if (user == null && username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN)) {
          user = userService.selectUserByPhoneNumber(username);
      }
      if (user == null && username.matches(UserConstants.EMAIL_PATTERN)) {
          user = userService.selectUserByEmail(username);
      }
      if (user == null || UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
          throw new UserNotExistsException();
      }
    
   //   setUserDepartQx(user);
      
      recordLoginInfo(user);
      return user;
  }

}
