package net.yiyutao.shiro;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: masterYI
 * @date: 2018/2/6
 * @time: 16:03
 * Description:跨域拦截器
 */
public class CorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        String origin = req.getHeader("Origin");
        rep.setContentType("text/html;charset=UTF-8");
        rep.setHeader("Access-Control-Allow-Origin", origin);
        rep.setHeader("Access-Control-Allow-Credentials", "true");
        rep.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
        rep.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, x-requested-with");
        chain.doFilter(req, rep);
    }
}
