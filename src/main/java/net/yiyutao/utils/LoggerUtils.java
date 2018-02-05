package net.yiyutao.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author masterYI
 * Date: 2017/9/18
 * Time: 9:53
 * Description:
 */
public class LoggerUtils {

    public static void debug(String title, String des, Class cl) {
        Logger logger = LogManager.getLogger(cl);
        logger.debug("【{}】{}", title, des);
    }

    public static void info(String title, String des, Class cl) {
        Logger logger = LogManager.getLogger(cl);
        logger.info("【{}】{}", title, des);
    }

    public static void warn(String title, String des, Class cl) {
        Logger logger = LogManager.getLogger(cl);
        logger.warn("【{}】{}", title, des);
    }

    public static void error(String title, Object des, Class cl) {
        Logger logger = LogManager.getLogger(cl);
        logger.error("【{}】{}", title, des);
    }

    public static void debug(String title, Object param, String des, Class cl) {
        Logger logger = LogManager.getLogger(cl);
        logger.debug("【{}】参数：{}描述：{}", param, title, des);
    }

    public static void info(String title, Object param, String des, Class cl) {
        Logger logger = LogManager.getLogger(cl);
        logger.info("【{}】参数：{}描述：{}", param, title, des);
    }

    public static void warn(String title, Object param, String des, Class cl) {
        Logger logger = LogManager.getLogger(cl);
        logger.warn("【{}】参数：{}描述：{}", param, title, des);
    }

    public static void error(String title, Object param, String des, Class cl) {
        Logger logger = LogManager.getLogger(cl);
        logger.error("【{}】参数：{}描述：{}", param, title, des);
    }

}
