package com.xunmo.config;

import lombok.extern.slf4j.Slf4j;
import org.noear.solon.core.aspect.Interceptor;
import org.noear.solon.core.aspect.Invocation;
import org.noear.solon.data.annotation.Tran;
import org.noear.solon.data.annotation.TranAnno;
import org.noear.solon.data.tran.TranUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 事务aop拦截器
 *
 * @author zengyufei
 * @date 2022/11/19
 */
@Slf4j
public class TranAopInterceptor implements Interceptor {

    private final static List<String> nameList = new ArrayList<>();

    static {
        nameList.add("add");
        nameList.add("insert");
        nameList.add("create");
        nameList.add("save");
        nameList.add("delete");
        nameList.add("edit");
        nameList.add("modify");
        nameList.add("update");
        nameList.add("remove");
        nameList.add("del");
        nameList.add("move");
    }

    @Override
    public Object doIntercept(Invocation inv) throws Throwable {
        final Method method = inv.method().getMethod();
        final String methodName = method.getName();
        boolean isHasTran = getIsHasTran(method);
        boolean isAddTran = getIsAddTran(methodName);
        if (!isHasTran && isAddTran) {
            System.out.println(methodName+" 自动加事务");
            AtomicReference<Object> rst = new AtomicReference<>();
            TranUtils.execute(new TranAnno(), () -> {
                rst.set(inv.invoke());
            });
            // 事务
            return rst.get();
        } else {
            // 非事务
            return inv.invoke();
        }
    }

    private boolean getIsHasTran(Method method) {
        boolean isHasTran = false;
        final Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Tran) {
                isHasTran = true;
                break;
            }
        }
        return isHasTran;
    }

    /**
     * 判断方法是否需要添加事务
     *
     * @param methodName 方法名称
     * @return boolean
     */
    private boolean getIsAddTran(String methodName) {
        boolean isAddTran = false;
        for (String name : nameList) {
            if (methodName.startsWith(name)) {
                isAddTran = true;
                break;
            }
        }
        return isAddTran;
    }

}
