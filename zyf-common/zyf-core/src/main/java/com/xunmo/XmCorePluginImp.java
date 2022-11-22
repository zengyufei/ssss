package com.xunmo;

import cn.hutool.core.util.StrUtil;
import com.xunmo.solon.HandlerLoaderPlus;
import org.noear.solon.Solon;
import org.noear.solon.SolonApp;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Controller;
import org.noear.solon.core.AopContext;
import org.noear.solon.core.Plugin;
import org.noear.solon.serialization.snack3.SnackActionExecutor;
import org.noear.solon.web.cors.CrossHandler;

import java.util.Properties;

public class XmCorePluginImp implements Plugin {

    @Override
    public void start(AopContext context) {
        final SolonApp app = Solon.app();

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

        // 自动扫描父类接口
        Solon.context().beanBuilderAdd(Controller.class, (clz, bw, anno) -> {
            new HandlerLoaderPlus(bw).load(app);
        });

//        context.getProps().loadAdd("zyf-core.yml");
        Properties properties = Utils.loadProperties("zyf-core.yml");
        properties.forEach((k, v) -> context.getProps().putIfAbsent(k, v));

        System.out.println("zyf-core 包加载完毕!");
    }

    @Override
    public void stop() throws Throwable {
        System.out.println("zyf-core 插件关闭");
    }
}
