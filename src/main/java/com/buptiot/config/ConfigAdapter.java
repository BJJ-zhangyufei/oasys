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
                .addPathPatterns("/api/v1/user/role") //需要拦截的请求
                .addPathPatterns("/api/v1/user/allRolesByUserId")
                .addPathPatterns("/api/v1/user/deleteRoleById")
                .addPathPatterns("/api/v1/user/findRoleById")
                .addPathPatterns("/api/v1/user/role/user")
                .addPathPatterns("/api/v1/user/findRoleByPermissionId")
                .addPathPatterns("/api/v1/user/userByPage")
                .addPathPatterns("/api/v1/user/userPages")
                .addPathPatterns("/api/v1/user/userById")//删，差如何区别？
                .addPathPatterns("/api/v1/user/userByGroupId")
                .addPathPatterns("/api/v1/user/userCount")
                .addPathPatterns("/api/v1/user/user") //增，改判断？
                .addPathPatterns("/api/v1/user/idByName")
                .addPathPatterns("/api/v1/user/allUsers")
                .addPathPatterns("/api/v1/user/reservePlanByPage")
                .addPathPatterns("/api/v1/user/reservePlanPages")
                .addPathPatterns("/api/v1/user/reservePlanById")
                .addPathPatterns("/api/v1/user/reservePlanByUserId")
                .addPathPatterns("/api/v1/user/reservePlanCount")
                .addPathPatterns("/api/v1/user/reservePlan")
                .addPathPatterns("/api/v1/user/noReservePlan")
                .addPathPatterns("/api/v1/user/alreadyReservePlan")
                .addPathPatterns("/api/v1/user/cantReservePlan")
                .addPathPatterns("/api/v1/user/agreeReservePlan")
                .addPathPatterns("/api/v1/user/disagreeReservePlan")
                .addPathPatterns("/api/v1/user/nextBoss")
                .addPathPatterns("/api/v1/user/userInfoByPlanId")
                .addPathPatterns("/api/v1/user/planAuditByPage")
                .addPathPatterns("/api/v1/user/planAuditPages")
                .addPathPatterns("/api/v1/user/planAuditById")
                .addPathPatterns("/api/v1/user/planAuditCount")
                .addPathPatterns("/api/v1/user/planAudit")
                .addPathPatterns("/api/v1/user/permission")
                .addPathPatterns("/api/v1/user/role")
                .addPathPatterns("/api/v1/user/roles")
                .addPathPatterns("/api/v1/user/permissions")
                .addPathPatterns("/api/v1/user/findAllByUserId")
                .addPathPatterns("/api/v1/user/findPermissionIdsByRoleId")
                .addPathPatterns("/api/v1/user/findPermissionNamesByRoleId")
                .addPathPatterns("/api/v1/user/rolePermission")
                .addPathPatterns("/api/v1/user/roleNotOwnedPermission")
                .addPathPatterns("/api/v1/user/user/role")
                .addPathPatterns("/api/v1/user/user/deleteRoleByUserId")
                .addPathPatterns("/api/v1/user/roles")
                .addPathPatterns("/api/v1/user/notOwnedRoles")
                .addPathPatterns("/api/v1/user/UserRoles")
                .addPathPatterns("/api/v1/user/findRolesNameByUserId")
                .addPathPatterns("/api/v1/user/role/findRoleByPermissionId")
                .addPathPatterns("/api/v1/user/")
                .excludePathPatterns("/api/v1/user/reservePlanById");  //不拦截的请求
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/api/v1/user/reservePlanById");
    }
}
