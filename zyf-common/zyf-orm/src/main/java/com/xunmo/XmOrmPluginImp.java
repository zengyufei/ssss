package com.xunmo;

import org.noear.solon.Solon;
import org.noear.solon.SolonApp;
import org.noear.solon.Utils;
import org.noear.solon.core.AopContext;
import org.noear.solon.core.Plugin;

import java.util.Properties;

public class XmOrmPluginImp implements Plugin {

    @Override
    public void start(AopContext context) {
        final SolonApp app = Solon.app();

//        context.getProps().loadAdd("zyf-orm.yml");
        Properties properties = Utils.loadProperties("zyf-orm.yml");
        properties.forEach((k, v) -> context.getProps().putIfAbsent(k, v));

        System.out.println("zyf-orm 包加载完毕!");
    }

    @Override
    public void stop() throws Throwable {
        System.out.println("插件关闭");
    }
}
