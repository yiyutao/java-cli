package net.yiyutao.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author: masterYI
 * @date: 2017/10/30
 * @time: 12:05
 * Description:
 */
public class IDUtils {

    /**
     * 默认生成ID的时间格式
     */
    private static final String FORMAT = "yyyyMMdd";


    /**
     * 根据时间格式生成ID
     *
     * @param format
     * @return
     */
    public static Long generateID(String format) {
        if (StringUtils.isBlank(format)) {
            format = FORMAT;
        }
        StringBuilder sb = new StringBuilder();
        String dateFormat = DateFormatUtils.format(new Date(), format);
        sb.append(dateFormat);
        String idStr = sb.toString();
        Long id = Long.parseLong(idStr);
        return id;
    }

    /**
     * 根据时间格式和自增序列生成ID
     *
     * @param format
     * @param incr
     * @return
     */
    public static Long generateID(String format, String incr) {
        if (StringUtils.isBlank(format)) {
            format = FORMAT;
        }
        StringBuilder sb = new StringBuilder();
        String dateFormat = DateFormatUtils.format(new Date(), format);
        sb.append(dateFormat);
        sb.append(incr);
        String idStr = sb.toString();
        Long id = Long.parseLong(idStr);
        return id;
    }


}
