package com.xunmo.aop;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.xunmo.utils.AjaxJson;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.event.EventListener;
import org.noear.solon.core.handle.Context;


/**
 * 全局异常处理
 *
 * @author noear
 */
@Slf4j
@Component
public class GlobalException implements EventListener<Throwable> {

	@Override
	public void onEvent(Throwable e) throws Throwable {
		Context c = Context.current();

		if (c != null) {
			// 不同异常返回不同状态码
			String aj = null;
			if (e instanceof NullPointerException) {    // 如果是未登录异常
				NullPointerException ee = (NullPointerException) e;
				aj = ee.getMessage();
				c.result = aj;
			} else {    // 普通异常, 输出：500 + 异常信息
				log.error(ExceptionUtil.stacktraceToString(e));
				c.render(AjaxJson.getError(e.getMessage()));
			}
		}
	}
}
