package com.xunmo;

import cn.hutool.core.util.StrUtil;
import com.xunmo.aop.ArgsInterceptor;
import com.xunmo.config.HandlerLoaderPlus;
import org.noear.snack.ONode;
import org.noear.snack.core.Options;
import org.noear.solon.Solon;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.Bridge;
import org.noear.solon.core.event.PluginLoadEndEvent;
import org.noear.solon.core.handle.Context;
import org.noear.solon.serialization.snack3.SnackJsonActionExecutor;
import org.noear.solon.web.cors.CrossHandler;

public class DemoApp {
    public static void main(String[] args) {
        Solon.start(DemoApp.class, args, app -> {

            // 跨域
            app.before(new CrossHandler().allowedOrigins("*"));

            // 处理参数左右空白
            app.before(ctx -> {
                ctx.paramMap().forEach((k, v) -> {
                    if (StrUtil.isBlank(v)) {
                        ctx.paramMap().remove(k);
                    } else {
                        ctx.paramMap().put(k, StrUtil.trim(v));
                    }
                });
            });

            // 处理空字符串入参，设置为null
            app.onEvent(PluginLoadEndEvent.class, e->{
                Bridge.actionExecutorRemove(SnackJsonActionExecutor.class);
                Bridge.actionExecutorAdd(new SnackJsonActionExecutorNew());
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

    public static class SnackJsonActionExecutorNew extends SnackJsonActionExecutor {
        Options options = Options.def();
        public SnackJsonActionExecutorNew() {
            options.addDecoder(String.class, (node, type) -> {
                if (Utils.isEmpty(node.getString())) {
                    return null;
                } else {
                    return StrUtil.trim(node.getString());
                }
            });
        }

        @Override
        protected Object changeBody(Context ctx) throws Exception {
            String json = ctx.bodyNew();

            if (Utils.isNotEmpty(json)) {
                return ONode.loadStr(json, options);
            } else {
                return null;
            }
        }
    }
}
