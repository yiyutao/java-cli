package net.yiyutao.base;

import net.yiyutao.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author: masterYI
 * @date: 2017/12/4
 * @time: 17:02
 * Description:
 */
public class BaseService {

    /**
     * 获取当前登录用户的登录账号
     *
     * @return
     */
    protected String getAccount() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        if (session == null) {
            return "";
        }
        Object account = session.getAttribute("account");
        if (account == null || StringUtils.isBlank(account.toString())) {
            return "";
        }

        return account.toString();
    }
}
