package com.jd.framework.web.exception;

import com.jd.common.exception.base.QyjcxxException;
import com.jd.common.utils.security.PermissionUtils;
import com.jd.framework.web.domain.Result;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 自定义异常处理器
 */
@RestControllerAdvice
public class DefaultExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    /**
     * 权限校验失败
     */
    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e) {
        log.error(e.getMessage(), e);
        return Result.error(PermissionUtils.getMsg(e.getMessage()));
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return Result.error("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截validator的数据校验异常
     */
    @ExceptionHandler(BindException.class)
    public Result bindException(BindException e) {
        List<ObjectError> errors=e.getAllErrors();
        log.error("validator的数据校验异常:", e);
        if(errors.size()>0){
            return Result.error("校验有误:" + errors.get(0).getDefaultMessage());
        }else{
            return Result.error("校验有误:" + e.getMessage());
        }
    }

    /**
     * 拦截自定义的validator的数据校验异常
     */
    @ExceptionHandler(QyjcxxException.class)
    public Result bindException(QyjcxxException e) {
        log.error("validator的数据校验异常:", e.getMessage());
        return Result.error("校验有误:" + e.getMessage());
    }

    /**
     * 主键关联异常
     */
    @ExceptionHandler(MySQLIntegrityConstraintViolationException.class)
    public Result handleMySQLIntegrityConstraintViolationException(MySQLIntegrityConstraintViolationException e) {
    	log.error("主键值被引用" + e.getMessage(), e);
    	return Result.error("主键值被引用，无法删除");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return Result.error("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error("服务器错误，请联系管理员");
    }

}
