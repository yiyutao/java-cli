package net.yiyutao.utils;

import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author: PCF Date: 2017/10/12 16:00
 * Description : 接口参数验证
 */
public class InterParamUtils {

    public static String key = "c1f02461713a006334596235cfff6e68";

    /**
     * 验证时间戳是否超时(5分钟)
     *
     * @param time
     */
    public static boolean verifyTimeStamp(String time) {
        if (StringUtils.isBlank(time)) {
        	return false;
        }
        if (System.currentTimeMillis() - Long.parseLong(time) > 300000) {
        	return false;
        }
        return true;
    }

    /**
     * 验证参数加密是否正确
     *
     * @param dataMap 参数
     * @throws Exception
     */
    public static boolean verifySign(TreeMap<String, String> dataMap, String sign) throws Exception {
        StringBuilder paramStr = new StringBuilder();
        Set<Entry<String, String>> set = dataMap.entrySet();
        for (Entry<String, String> entry : set) {
            paramStr.append(entry.getKey()).append(entry.getValue());
        }
        String urlCode = URLEncoder.encode(paramStr.toString(), "UTF-8");// 编码

        StringBuilder secretStr = new StringBuilder();
        // 在字符串前后拼接密钥
        secretStr.append(key).append(urlCode).append(key);
        // MD5加密
        String currSign = MD5.md5(secretStr.toString());
        if (currSign.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }

}
