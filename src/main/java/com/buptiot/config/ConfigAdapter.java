package com.buptiot.config;

import com.buptiot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zyf on 2019/5/21.
 */
@Configuration
public class ConfigAdapter extends WebMvcConfigurerAdapter {

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/hello")  //需要拦截的请求
                .excludePathPatterns("/test");  //不拦截的请求
        //registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/test");
    }
}
