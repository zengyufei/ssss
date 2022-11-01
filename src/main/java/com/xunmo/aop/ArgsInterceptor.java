package com.xunmo.aop;

import lombok.extern.slf4j.Slf4j;
import org.noear.solon.core.aspect.Interceptor;
import org.noear.solon.core.aspect.Invocation;

@Slf4j
public class ArgsInterceptor implements Interceptor {
    @Override
    public Object doIntercept(Invocation inv) throws Throwable {
        log.info("Args: {}", inv.args());
        //此处为拦截处理
        Object rst = inv.invoke();
        log.info("Return: {}", rst);
        return rst;
    }
}
