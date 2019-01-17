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
        res.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        res.setHeader("Access-Control-Allow-Methods", "*");
        res.setHeader("Access-Control-Allow-Headers", "content-type");
        res.setHeader("Access-Control-Allow-Credentials", "true");
       // res.setHeader("Set-Cookie","name=SessionId;value=/api/v1/info/authorize;path=/;HttpOnly");
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }

}

