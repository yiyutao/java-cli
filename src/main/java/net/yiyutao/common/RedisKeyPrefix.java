package net.yiyutao.common;

/**
 * @author: masterYI
 * @date: 2017/10/30
 * @time: 17:04
 * Description:
 */
public class RedisKeyPrefix {

    private static final String BASEKEY = "oms:";

    public static final String INCR_4 = BASEKEY + "incr_4";

    /**
     * shiro的session存放在redis中的前缀
     */
    public static final String SESSION_KEY = BASEKEY + "shiro:session:";

    /**
     * shiro的缓存key
     */
    public static String SHIRO_CACHE_KEY = BASEKEY + "shiro:cache:";
}
