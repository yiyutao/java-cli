package net.yiyutao.shiro;

import net.yiyutao.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: masterYI
 * @date: 2017/11/30
 * @time: 10:43
 * Description:用户认证授权
 */
@Component
public class ShiroRealm extends AuthorizingRealm {


    /**
     * 用户授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String account = (String) principalCollection.getPrimaryPrincipal();
        List<String> permsList;
//        if ("administrator".equals(account)) {
//            permsList = roleMenuMapper.adminUrls();
//        } else {
//            permsList = roleMenuMapper.userUrls(account);
//        }
//        if (CollectionUtils.isNotEmpty(permsList)) {
//            List<String> collect = permsList.stream().filter(url -> StringUtils.isNotBlank(url)).collect(Collectors.toList());
//            Set<String> permissions = new HashSet<>(collect);
//            authorizationInfo.setStringPermissions(permissions);
//        }
        return authorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        //用户名
        String account = upToken.getUsername();
        if (StringUtils.isBlank(account)) {
            throw new UnknownAccountException();
        }
//        User user = userMapper.getUserPass(account);
//        //判断用户是否存在
//        if (user == null) {
//            throw new UnknownAccountException();
//        }
//        //判断用户状态
//        if (user.getState() == null || StateEnum.ENABLE.state != user.getState()) {
//            throw new LockedAccountException();
//        }
//        String password = user.getPassword();
//        String salt = user.getSalt();
        String password = "";
        String salt = "";
        return new SimpleAuthenticationInfo(account, password, ByteSource.Util.bytes(salt), this.getName());
    }

    @PostConstruct
    private void initShiroRealm() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        this.setCredentialsMatcher(hashedCredentialsMatcher);
    }


}
