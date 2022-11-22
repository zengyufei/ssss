package com.xunmo;

import com.xunmo.aop.ArgsInterceptor;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Mapping;

/**
 * 启动 
 * @author kong 
 */
public class GenCodeApp {

	public static void main(String[] args) {
		Solon.start(GenCodeApp.class, args, app -> {
			//将拦截器注册到容器
			Solon.context().beanAroundAdd(Mapping.class, new ArgsInterceptor(), 120);

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
