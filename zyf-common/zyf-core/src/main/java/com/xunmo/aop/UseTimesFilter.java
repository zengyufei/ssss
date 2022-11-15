package com.xunmo.aop;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;

@Slf4j
@Component
public class UseTimesFilter implements Filter {
    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        //1.开始计时（用于计算响应时长）
        final TimeInterval timer = DateUtil.timer();
        chain.doFilter(ctx);
        //2.获得接口响应时长
        log.info("接口 {} 用时：{} 毫秒", ctx.path(), timer.intervalMs());

    }
}  
