package net.yiyutao.base;

import net.yiyutao.common.RedisKeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

/**
 * @author: masterYI
 * @date: 2017/10/30
 * @time: 16:17
 * Description:
 */
@Component
public class BaseRedis {

    @Autowired
    private StringRedisTemplate template;

    /**
     * redis自增序列格式化模板
     */
    private static final String INCR_4 = "0000";

    /**
     * redis自增序列步长
     */
    private static final Long DELTA = 1L;

    /**
     * redis自增序列最大值
     */
    private static final Integer MAX_INCR_4 = 9999;


    /**
     * redis自增序列
     *
     * @param key
     * @param delta
     * @return
     */

    public Long increment(String key, Long delta) {
        return template.opsForValue().increment(key, delta);
    }

    /**
     * redis getset
     *
     * @param key
     * @param value
     * @return
     */
    public String getSet(String key, String value) {
        return template.opsForValue().getAndSet(key, value);
    }

    /**
     * 返回四位自增序列，若到9999，下次返回0001
     *
     * @return
     */
    public String getIncr() {
        Long increment = increment(RedisKeyPrefix.INCR_4, DELTA);
        if (MAX_INCR_4.equals(increment)) {
            getSet(RedisKeyPrefix.INCR_4, "0");
        }
        String incr = new DecimalFormat(INCR_4).format(increment);
        return incr;
    }


}
