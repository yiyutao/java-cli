package net.yiyutao.shiro;

import net.yiyutao.utils.StringUtils;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author masterYI
 * @date 2017/5/8
 */
public class CustomPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {

    /**
     * 可以匿名访问的URL
     */
    @Value("${anon}")
    private String anon;

    private CustomDefaultFilterChainManager customDefaultFilterChainManager;

    public void setCustomDefaultFilterChainManager(CustomDefaultFilterChainManager customDefaultFilterChainManager) {
        this.customDefaultFilterChainManager = customDefaultFilterChainManager;
        setFilterChainManager(customDefaultFilterChainManager);
    }

    @Override
    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = getFilterChainManager();
        if (!filterChainManager.hasChains()) {
            return null;
        }


        String requestURI = getPathWithinApplication(request);
        List<String> chainNames = new ArrayList<String>();
        //the 'chain names' in this implementation are actually path patterns defined by the user.  We just use them
        //as the chain name for the FilterChainManager's requirements
        for (String pathPattern : filterChainManager.getChainNames()) {

            // If the path does match, then pass on to the subclass implementation for specific checks:
            //shiro默认多个拦截器链都匹配了当前请求URL，那么只返回第一个找到的拦截器链，这里返回所有的拦截器链
            if (pathMatches(pathPattern, requestURI)) {
                chainNames.add(pathPattern);
            }
        }
        if (chainNames.size() == 0) {
            return null;
        }
        //登录地址可以匿名访问，因为设置了/**=authc,即使设置/rest/login = anon也无效，因为多个拦截器链，返回所有。
        if (StringUtils.isNotBlank(anon)) {
            String[] anonUrls = anon.split(",");
            List<String> anons = Arrays.asList(anonUrls);
            if (anons.contains(requestURI)) {
                chainNames.clear();
            }
        }

        return customDefaultFilterChainManager.proxy(originalChain, chainNames);
    }

}
