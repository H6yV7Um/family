package org.yxyqcy.family.common.util;


import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by lcy on 16/11/9.
 */
public class FamilyLogger {
    //调试logger
    protected static  Logger logger = LoggerFactory.getLogger(FamilyLogger.class);

    //日志 sys
    protected static  org.apache.log4j.Logger sysLogger = org.apache.log4j.Logger.getLogger("sysLog");

    public static void debug(Object obj){
        logger.debug("obj : {}",obj);
    }

    public static void error(Object obj){
        logger.debug("obj : {}",obj);
    }

    /**
     * 日志  -> sysLog
     * @param createUserId  创建人
     * @param message       日志
     */
    public static void sysInfo(String createUserId,String message){
        MDC.put("businessId",IdGen.uuid());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MDC.put("createDate",sf.format(new Date()));
        MDC.put("updateDate",sf.format(new Date()));
        sysLogger.info(createUserId + " " + message);
    }

    /**
     * error
     * @param createUserId
     * @param message
     */
    public static void sysError(String createUserId,String message){
        MDC.put("businessId",IdGen.uuid());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MDC.put("createDate",sf.format(new Date()));
        MDC.put("updateDate",sf.format(new Date()));
        sysLogger.error(createUserId + " " + message);
    }
}
