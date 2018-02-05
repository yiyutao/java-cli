package net.yiyutao.shiro;

import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author masterYI
 */
@Component
public class ShiroFilterChainManager {


    @Autowired
    private DefaultFilterChainManager defaultFilterChainManager;

    private Map<String, NamedFilterList> defaultFilterChains;


    @PostConstruct
    public void init() {

        defaultFilterChains = new HashMap<>(defaultFilterChainManager.getFilterChains());
    }

    public void initFilterChains(List<String> urls) {
        //1、首先删除以前老的filter chain并注册默认的
        defaultFilterChainManager.getFilterChains().clear();
        if (defaultFilterChains != null) {
            defaultFilterChainManager.getFilterChains().putAll(defaultFilterChains);
        }

        //2、循环URL Filter 注册filter chain
        for (String url : urls) {
            defaultFilterChainManager.addToChain(url, "perms", url);
        }

    }

    /**
     * 注册拦截器到shiro，spring容器启动或对角色权限进行增删改时会生效
     */
    @PostConstruct
    public void initFilterChain() {

        //可从数据库加载地址
        List<String> urls = new ArrayList<>();

        initFilterChains(urls);
    }


}

