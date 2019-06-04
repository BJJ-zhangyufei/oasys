package com.buptiot.interceptor;

import com.buptiot.annotation.PermissionAop;
import com.buptiot.config.PermissionConfig;
import com.buptiot.utils.PermissionUtils;
import com.buptiot.utils.ReflectUtil;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;
import java.util.Set;

/**
 * Created by zyf on 2019/5/28.
 */
@Intercepts({
//          @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class })
//        @Signature(method = "query", type = Executor.class, args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
})
@Component
public class PrepareInterceptor implements Interceptor {
    /** 日志 */
    private static final Logger log = LoggerFactory.getLogger(PrepareInterceptor.class);

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {}

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(log.isInfoEnabled()){
            log.info("进入 PrepareInterceptor 拦截器...");
        }
        if(invocation.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
            StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
            //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
            //千万不能用下面注释的这个方法，会造成对象丢失，以致转换失败
            //MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

            PermissionAop permissionAop = PermissionUtils.getPermissionByDelegate(mappedStatement);
            if(permissionAop == null){
                if(log.isInfoEnabled()){
                    log.info("数据权限放行...");
                }
                return invocation.proceed();
            }
            if(log.isInfoEnabled()){
                log.info("数据权限处理【拼接SQL】...");
            }
            BoundSql boundSql = delegate.getBoundSql();
            //Connection connection = (Connection) invocation.getArgs()[0];
            ReflectUtil.setFieldValue(boundSql, "sql", permissionSql(boundSql.getSql()));
        }
        return invocation.proceed();


    }


    /**
     * 权限sql包装
     * @author GaoYuan
     * @date 2018/4/17 上午9:51
     */
    protected String permissionSql(String sql) {
        StringBuilder sbSql = new StringBuilder(sql);
        String userMethodPath = PermissionConfig.getConfig("permission.client.role.method");
        //获取当前登录用户的角色信息
        Set<String> currentRoles = (Set<String>) ReflectUtil.reflectByPath(userMethodPath);
        //如果角色为GeneralDispatcher 则只能查询第一条
        for(String role : currentRoles){
            if("GeneralDispatcher".equals(role)){

                //如果有动态参数 description
                if(true){

                    String premission_param = "description";

                    String methodPath = PermissionConfig.getConfig("permission.client.params." + premission_param);
                    Set<String> descriptions = (Set<String>) ReflectUtil.reflectByPath(methodPath);
                    for(String description : descriptions){
                        sbSql = new StringBuilder("").append(sbSql).append(" where description  = \""+ description +"\"");
                    }
                    //sbSql = new StringBuilder("select * from (").append(sbSql).append(" ) s where s.description like concat("+ description +",'%')  ");
                }

            }
        }
//        if("GeneralDispatcher".equals(role)){
//
//            //sbSql = sbSql.append(" limit 1 ");
//
//            //如果有动态参数 description
//            if(true){
//
//                String premission_param = "description";
//
//                //select * from (select id,name,region_cd from sys_exam ) where region_cd like '${}%'
//                String methodPath = PermissionConfig.getConfig("permission.client.params." + premission_param);
//                String description = (String)ReflectUtil.reflectByPath(methodPath);
//                //sbSql = new StringBuilder("select * from (").append(sbSql).append(" ) s where s.description like concat("+ description +",'%')  ");
//                sbSql = new StringBuilder("").append(sbSql).append(" where description  = \""+ description +"\"");
//            }
//
//        }
        return sbSql.toString();
    }



}

