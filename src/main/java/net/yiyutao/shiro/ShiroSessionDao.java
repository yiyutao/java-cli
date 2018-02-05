package net.yiyutao.shiro;

import net.yiyutao.common.RedisKeyPrefix;
import net.yiyutao.utils.LoggerUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author: masterYI
 * @date: 2017/11/30
 * @time: 11:52
 * Description:缓存session到redis
 */
public class ShiroSessionDao extends AbstractSessionDAO {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 会话过期时间
     */
    @Value("${sessionExpire}")
    private Long sessionExpire;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            LoggerUtils.debug("读取session", "session id is null", this.getClass());
            return null;
        }
        String key = RedisKeyPrefix.SESSION_KEY + sessionId;
        Object object = redisTemplate.opsForValue().get(key);
        if (object == null) {
            LoggerUtils.debug("读取session", "session is null", this.getClass());
            return null;
        }
        return (Session) object;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            LoggerUtils.debug("删除session", "session or session id is null", this.getClass());
            return;
        }
        redisTemplate.delete(RedisKeyPrefix.SESSION_KEY + session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }

    private void saveSession(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            LoggerUtils.error("创建session", "session or session id is null", this.getClass());
            return;
        }
        redisTemplate.opsForValue().set(RedisKeyPrefix.SESSION_KEY + session.getId(), session, sessionExpire, TimeUnit.SECONDS);
    }


}
