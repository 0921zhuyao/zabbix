package com.jd.framework.manager.factory;

import com.jd.common.utils.AddressUtils;
import com.jd.common.utils.spring.SpringUtils;
import com.jd.project.system.operlog.domain.OperLog;
import com.jd.project.system.operlog.service.IOperLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 */
public class AsyncFactory {

    private static final Logger logger = LoggerFactory.getLogger(AsyncFactory.class);

    /**
     * 操作日志记录
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final OperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(IOperLogService.class).insertOperlog(operLog);
                logger.info("write operLog info to db");
            }
        };
    }
}
