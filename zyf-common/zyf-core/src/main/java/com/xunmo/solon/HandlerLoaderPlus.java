package com.xunmo.solon;

import cn.hutool.core.util.ObjectUtil;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.BeanWrap;
import org.noear.solon.core.handle.Action;
import org.noear.solon.core.handle.HandlerLoader;
import org.noear.solon.core.handle.HandlerSlots;
import org.noear.solon.core.handle.MethodType;
import org.noear.solon.core.handle.MethodTypeUtil;
import org.noear.solon.core.util.PathUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HandlerLoaderPlus extends HandlerLoader {
    public HandlerLoaderPlus(BeanWrap wrap) {
        super(wrap);
    }

    @Override
    protected Method[] findMethods(Class<?> clz) {
        return clz.getMethods();
    }

    @Override
    protected void loadActionDo(HandlerSlots slots, boolean all) {
        String m_path;

        if (bPath == null) {
            bPath = "";
        }

        Set<MethodType> b_method = new HashSet<>();

        loadControllerAide(b_method);

        Set<MethodType> m_method;
        Mapping m_map;
        int m_index = 0;

        Map<String, Mapping> interfaceMethodMap = new HashMap<>();
        Class<?>[] interfaces = bw.clz().getInterfaces();
        if (ObjectUtil.isNotNull(interfaces) && interfaces.length > 0) {
            for (Class<?> anInterface : interfaces) {
                Controller annotation = anInterface.getAnnotation(Controller.class);
                if (annotation != null) {
                    Method[] methods = anInterface.getMethods();
                    for (Method method : methods) {
                        String name = method.getName();
                        Mapping mappingAnnotation = method.getAnnotation(Mapping.class);
                        if (mappingAnnotation != null) {
                            interfaceMethodMap.put(name, mappingAnnotation);
                        }
                    }
                }
            }
        }

        //????????? public ????????? Action
        for (Method method : findMethods(bw.clz())) {
            m_map = method.getAnnotation(Mapping.class);
            if (m_map == null && !interfaceMethodMap.isEmpty()) {
                m_map = interfaceMethodMap.get(method.getName());
            }
            m_index = 0;
            m_method = new HashSet<>();

            //?????? action ???methodTypes
            MethodTypeUtil.findAndFill(m_method, t -> method.getAnnotation(t) != null);

            //??????path and method
            if (m_map != null) {
                m_path = Utils.annoAlias(m_map.value(), m_map.path());

                if (m_method.size() == 0) {
                    //???????????????????????????Mapping????????????
                    m_method.addAll(Arrays.asList(m_map.method()));
                }
                m_index = m_map.index();
            } else {
                m_path = method.getName();

                if (m_method.size() == 0) {
                    //?????? controller ???methodTypes
                    MethodTypeUtil.findAndFill(m_method, t -> bw.clz().getAnnotation(t) != null);
                }

                if (m_method.size() == 0) {
                    //???????????????????????????Mapping????????????????????????
                    if (bMapping == null) {
                        m_method.add(MethodType.HTTP);
                    } else {
                        m_method.addAll(Arrays.asList(bMapping.method()));
                    }
                }
            }

            //?????????service???method ????????????map
            if (m_map != null || all) {
                String newPath = PathUtil.mergePath(bPath, m_path);

                Action action = createAction(bw, method, m_map, newPath, bRemoting);

                //m_method ????????????????????????????????????  //???????????? Cors
                loadActionAide(method, action, m_method);
                if (b_method.size() > 0 &&
                        m_method.contains(MethodType.HTTP) == false &&
                        m_method.contains(MethodType.ALL) == false) {
                    //???????????? Cors
                    m_method.addAll(b_method);
                }

                for (MethodType m1 : m_method) {
                    if (m_map == null) {
                        slots.add(newPath, m1, action);
                    } else {
                        if ((m_map.after() || m_map.before())) {
                            if (m_map.after()) {
                                slots.after(newPath, m1, m_index, action);
                            } else {
                                slots.before(newPath, m1, m_index, action);
                            }
                        } else {
                            slots.add(newPath, m1, action);
                        }
                    }
                }
            }
        }
    }
}
