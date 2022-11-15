package com.xunmo;

import cn.hutool.core.util.StrUtil;
import com.xunmo.aop.ArgsInterceptor;
import com.xunmo.config.HandlerLoaderPlus;
import org.noear.solon.Solon;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.serialization.snack3.SnackActionExecutor;
import org.noear.solon.web.cors.CrossHandler;

/**
 * 启动 
 * @author kong 
 */
public class GenCodeApp {

	public static void main(String[] args) {
		Solon.start(GenCodeApp.class, args, app -> {

			// 跨域
			app.before(new CrossHandler().allowedOrigins("*"));

			// 处理参数左右空白
			app.before(ctx -> {
				ctx.paramMap().forEach((k, v) -> {
					if (StrUtil.isBlank(v)) {
						// 清除url上空字符串，转为null或移除
						ctx.paramMap().remove(k);
					} else {
						ctx.paramMap().put(k, StrUtil.trim(v));
					}
				});
			});

			// 处理json空字符串入参，设置为null
			app.onEvent(SnackActionExecutor.class, executor -> {
				executor.config().addDecoder(String.class, (node, type) -> {
					if (Utils.isEmpty(node.getString())) {
						return null;
					} else {
						// 处理参数左右空白
						return StrUtil.trim(node.getString());
					}
				});
			});

			//将拦截器注册到容器
			Solon.context().beanAroundAdd(Mapping.class, new ArgsInterceptor(), 120);

			// 自动添加 multipart
			app.filter(-1, (ctx, chain) -> {
				if (ctx.path().startsWith("/upload")) {
					ctx.autoMultipart(true); //给需要的路径加 autoMultipart
				}
				chain.doFilter(ctx);
			});

			// 自动扫描父类接口
			Solon.context().beanBuilderAdd(Controller.class, (clz, bw, anno) -> {
				new HandlerLoaderPlus(bw).load(Solon.app());
			});
		});
	}
	
}
