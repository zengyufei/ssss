package com.xunmo;

import com.xunmo.aop.ArgsInterceptor;
import com.xunmo.config.TranAopInterceptor;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.aspect.annotation.Service;

public class BizApp {
    public static void main(String[] args) {
        Solon.start(BizApp.class, args, app -> {
            // 将拦截器注册到容器
            Solon.context().beanAroundAdd(Mapping.class, new ArgsInterceptor(), 120);
            // 事务拦截器，自动将符合的方法名加入事务
            Solon.context().beanAroundAdd(Service.class, new TranAopInterceptor());

            // 自动添加 multipart
            app.filter(-1, (ctx, chain) -> {
                if (ctx.path().startsWith("/upload")) {
                    ctx.autoMultipart(true); //给需要的路径加 autoMultipart
                }
                chain.doFilter(ctx);
            });
        });
    }
}
