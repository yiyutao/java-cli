package net.yiyutao.conf;

import net.yiyutao.interceptor.SignInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: masterYI
 * @date: 2017/10/19
 * @time: 15:19
 * Description:
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Value("${includeURL}")
    private String includeURL;

    @Value("${excludeURL}")
    private String excludeURL;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        SignInterceptor signInterceptor = new SignInterceptor();

        String[] includeUrls = includeURL.split(",");
        String[] excludeUrls = excludeURL.split(",");

        registry.addInterceptor(signInterceptor).addPathPatterns(includeUrls).excludePathPatterns(excludeUrls);
    }
}
