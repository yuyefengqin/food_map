package com.gok.food_map.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

//名字起长了，静态资源过滤器说是
@Component
//过滤这个静态请求
@WebFilter(urlPatterns = {"/static/*", "*.html", "*.css", "*.js", "*.png", "*.jpg", "*.jpeg"})
public class StaticResourceFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(StaticResourceFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 记录静态资源,访问日志
        if (request instanceof HttpServletRequest httpRequest) {
            String uri = httpRequest.getRequestURI();
            String method = httpRequest.getMethod();
            log.info("[静态资源过滤器] 访问: {} {}", method, uri);
        }

        // 继续执行过滤器链
        chain.doFilter(request, response);
    }
}
