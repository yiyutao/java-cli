package net.yiyutao.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: masterYI
 * @date: 2017/12/1
 * @time: 15:02
 * Description:redis序列化配置
 */
@Configuration
public class RedisConfiguration {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 字符串序列化工具，redis的key需调用此方法
     *
     * @return
     */
    @Bean
    public RedisSerializer stringSerializer(@Qualifier("redisTemplate") RedisTemplate redisTemplate) {
        return redisTemplate.getStringSerializer();
    }

    /**
     * 值序列化工具，默认用jdk的序列化
     *
     * @return
     */
    @Bean
    public RedisSerializer valueSerializer(@Qualifier("redisTemplate") RedisTemplate redisTemplate) {
        return redisTemplate.getValueSerializer();
    }

    @Bean("redisTemplate")
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
