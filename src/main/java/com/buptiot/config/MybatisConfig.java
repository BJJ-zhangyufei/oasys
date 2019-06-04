package com.buptiot.config;

import com.buptiot.interceptor.PrepareInterceptor;
//import com.buptiot.interceptor.ResultInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zyf on 2019/5/31.
 */
@Configuration
public class MybatisConfig {

    /**
     * 注入sql注入器
     */
    @Bean
    public PrepareInterceptor prepareInterceptor(){
        return new PrepareInterceptor();
    }

//    @Bean
//    public ResultInterceptor resultInterceptor(){
//        return  new ResultInterceptor();
//    }


}
