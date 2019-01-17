package com.buptiot.filter;

/**
 * Created by zyf on 2019/1/16.
 */
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class ThroughFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.setHeader("Access-Control-Allow-Origin", "http://10.112.181.83:8080");
        res.setHeader("Access-Control-Allow-Methods", "*");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }

}

