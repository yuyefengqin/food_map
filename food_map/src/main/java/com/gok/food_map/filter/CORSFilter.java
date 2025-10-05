package com.gok.food_map.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//@WebFilter("/*")
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // 从请求头中获取Origin 也可根据需求设置为固定的前端域名
        String origin = req.getHeader("Origin");
        // 重要 设置允许访问的单一特定域名，不能为 "*"
        resp.addHeader(  "Access-Control-Allow-Origin","*");//允许所有来源访同
        resp.addHeader(  "Access-Control-Allow-Method","POST,GET");//允许访问的方式
        resp.setHeader("Access-Control-Allow-Origin", origin);
        // 重要 允许跨域请求携带Cookie
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        // 对于OPTIONS预检请求，可直接返回成功状态，否则继续执行过滤链
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
