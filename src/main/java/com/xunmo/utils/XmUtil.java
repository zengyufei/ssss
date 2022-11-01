package com.xunmo.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.xunmo.annotations.AuditFunc;
import com.xunmo.annotations.AuditMapFunc;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.ListCompareAlgorithm;
import org.javers.core.diff.changetype.ValueChange;
import org.noear.solon.core.handle.Context;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 寻墨工具类
 *
 * @author zengyufei
 * @date 2021/11/22
 */
@Slf4j
public class XmUtil {

    private final static Javers JAVERS = JaversBuilder.javers()
            .withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE)
            .build();


    public static  <T> String getField(XmMap.SFunction<T, ?> fn) {
        return XmMap.getField(fn);
    }

    public static void compare(Object left, Object right, AuditFunc auditFunc) {
        final Changes changes = JAVERS.compare(left, right).getChanges();
        final List<ValueChange> valueChanges = changes.stream().map(change -> (ValueChange) change).collect(Collectors.toList());
        auditFunc.audit(valueChanges);
    }


    public static void compareMap(Object left, Object right, AuditMapFunc auditFunc) {
        final Changes changes = JAVERS.compare(left, right).getChanges();
        final XmMap<String, ValueChange> changeEduMap = new XmMap<>();
        changes.stream()
                .map(change -> (ValueChange) change)
                .forEach(valueChange -> changeEduMap.put(valueChange.getPropertyName(), valueChange));
        auditFunc.audit(changeEduMap);
    }

//    public static void setVar(String key, Object o) {
//        RequestContextHolder.currentRequestAttributes().setAttribute(key, o, RequestAttributes.SCOPE_REQUEST);
//    }
//
//    public static void removeVar(String key) {
//        RequestContextHolder.currentRequestAttributes().removeAttribute(key, RequestAttributes.SCOPE_REQUEST);
//    }
//
    public static <T> T getVar(String key) {
        final Context current = Context.current();
        return current.attr(key);
    }

    public static <T> T getVar(String key, T defaultValue) {
        final Context current = Context.current();
        final T attribute = current.attr(key, defaultValue);
        if (attribute == null) {
            return defaultValue;
        }
        return attribute;
    }


    public static String getStaticValue(Class<?> targetClass, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        // 这里必须预先实例化对象,否则它的静态字段不会加载
        Field name = targetClass.getField(fieldName);
        // 注意，上面的Field实例是通过Class获取的，但是下面的获取静态属性的值没有依赖到Class
        return (String) unsafe.getObject(unsafe.staticFieldBase(name), unsafe.staticFieldOffset(name));
    }

    public static <K, V> XmMap<K, V> newYlMap() {
        return new XmMap<>();
    }

    public static List<String> getSortNames(List<String> tempNames) {
        String sortAttr = "0123456789零一二三四五六七八九十";
        return CollUtil.sort(tempNames, (o1, o2) -> {
            final int o1Len = o1.length();
            final int o2Len = o2.length();
            final int length = sortAttr.length();

            int o1Scope = 0;
            int o2Scope = 0;
            for (int i = 0; i < Math.min(o1Len, o2Len); i++) {
                final char c1 = o1.charAt(i);
                final char c2 = o2.charAt(i);
                final int i1 = sortAttr.indexOf(c1);
                final int i2 = sortAttr.indexOf(c2);
                if (i1 > -1 && i2 > -1) {
                    if (i == 0) {
                        o1Scope = o1Scope + i1;
                        o2Scope = o2Scope + i2;
                    } else {
                        o1Scope = o1Scope + i1 + (i * length);
                        o2Scope = o2Scope + i2 + (i * length);
                    }
                    if (i1 > 9) {
                        o1Scope = o1Scope + 100;
                    }
                    if (i2 > 9) {
                        o2Scope = o2Scope + 100;
                    }
                } else if (i1 > -1) {
                    if (i == 0) {
                        o1Scope = o1Scope + i1;
                        o2Scope = o2Scope + 100000;
                    } else {
                        o1Scope = o1Scope + i1 + +(i * length);
                        o2Scope = o2Scope + 0;
                    }
                    if (i1 > 9) {
                        o1Scope = o1Scope + 100;
                    }
                    break;
                } else if (i2 > -1) {
                    if (i == 0) {
                        o1Scope = o1Scope + 100000;
                        o2Scope = o2Scope + i2;
                    } else {
                        o1Scope = o1Scope + 0;
                        o2Scope = o2Scope + i2 + +(i * length);
                    }
                    if (i2 > 9) {
                        o2Scope = o2Scope + 100;
                    }
                    break;
                } else {
                    break;
                }
            }
//            System.out.println(o1 + ", " + o2 + " === " + o1Scope + ", " + o2Scope);
            if (o1Scope > o2Scope) {
                return 1; // o1后面
            } else {
                return -1; // o1前面
            }
        });
    }


    public static <T> List<T> getSortNames(List<T> tempNames, XmMap.SFunction<T, String> func) {
        String sortAttr = "0123456789零一二三四五六七八九十";
        return CollUtil.sort(tempNames, (t1, t2) -> {
            final String o1 = (String) ReflectUtil.getFieldValue(t1, XmMap.getField(func));
            final String o2 = (String) ReflectUtil.getFieldValue(t2, XmMap.getField(func));
            final int o1Len = o1.length();
            final int o2Len = o2.length();
            final int length = sortAttr.length();

            int o1Scope = 0;
            int o2Scope = 0;
            for (int i = 0; i < Math.min(o1Len, o2Len); i++) {
                final char c1 = o1.charAt(i);
                final char c2 = o2.charAt(i);
                final int i1 = sortAttr.indexOf(c1);
                final int i2 = sortAttr.indexOf(c2);
                if (i1 > -1 && i2 > -1) {
                    if (i == 0) {
                        o1Scope = o1Scope + i1;
                        o2Scope = o2Scope + i2;
                    } else {
                        o1Scope = o1Scope + i1 + (i * length);
                        o2Scope = o2Scope + i2 + (i * length);
                    }
                    if (i1 > 9) {
                        o1Scope = o1Scope + 100;
                    }
                    if (i2 > 9) {
                        o2Scope = o2Scope + 100;
                    }
                } else if (i1 > -1) {
                    if (i == 0) {
                        o1Scope = o1Scope + i1;
                        o2Scope = o2Scope + 100000;
                    } else {
                        o1Scope = o1Scope + i1 + +(i * length);
                        o2Scope = o2Scope + 0;
                    }
                    if (i1 > 9) {
                        o1Scope = o1Scope + 100;
                    }
                    break;
                } else if (i2 > -1) {
                    if (i == 0) {
                        o1Scope = o1Scope + 100000;
                        o2Scope = o2Scope + i2;
                    } else {
                        o1Scope = o1Scope + 0;
                        o2Scope = o2Scope + i2 + +(i * length);
                    }
                    if (i2 > 9) {
                        o2Scope = o2Scope + 100;
                    }
                    break;
                } else {
                    break;
                }
            }
//            System.out.println(o1 + ", " + o2 + " === " + o1Scope + ", " + o2Scope);
            if (o1Scope > o2Scope) {
                return 1; // o1后面
            } else {
                return -1; // o1前面
            }
        });
    }


//    /**
//     * 获取值到 request
//     *
//     * @param key 关键
//     */
//    public static String getParam(String key) {
//        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getParameter(key);
//    }
//
//    /**
//     * 获取值到 request
//     */
//    public static String getSortVar() {
//        String sort = getParam("sort");
//        if (StrUtil.isBlank(sort)) {
//            sort = getVar("sort");
//        }
//        if (StrUtil.isBlank(sort)) {
//            sort = getParam("orderBy");
//        }
//        if (StrUtil.isBlank(sort)) {
//            sort = getVar("orderBy");
//        }
//        return sort;
//    }

    public static <T> void addOrderBy(String sort, QueryWrapper<T> queryWrapper) {
        if (StrUtil.isNotBlank(sort)) {
            final List<String> split = StrUtil.split(sort, ',');
            for (final String orderColumnAndSort : split) {
                if (StrUtil.isBlank(orderColumnAndSort)) {
                    continue;
                }
                final List<String> keyVal;
                if (StrUtil.contains(orderColumnAndSort, "-")) {
                    keyVal = StrUtil.split(orderColumnAndSort, '-');
                } else {
                    keyVal = StrUtil.split(orderColumnAndSort, '_');
                }
                final String requestProperty = keyVal.get(0);
                String requestOrder = keyVal.get(1);


                // 判断列名称的合法性，防止SQL注入。只能是【字母，数字，下划线】
                if (!requestProperty.matches("[A-Za-z0-9_]+")) {
                    throw new IllegalArgumentException("非法的排序字段名称：" + requestProperty);
                }

                if (StrUtil.isBlank(requestOrder)) {
                    requestOrder = "asc";
                } else {
                    if (!(StrUtil.equalsIgnoreCase(requestOrder, "ASC") || StrUtil.equalsIgnoreCase(requestOrder, "DESC"))) {
                        throw new IllegalArgumentException("非法的排序策略：" + requestOrder);
                    }
                }
                queryWrapper.orderBy(true, StrUtil.equalsIgnoreCase("asc", requestOrder), requestProperty);
            }
        }
    }

    public static <T> void addOrderBy(String sort, LambdaQueryWrapper<T> lambda, Class<T> clazz) {
        if (StrUtil.isNotBlank(sort)) {
            if (clazz == null) {
                throw new RuntimeException("class 不能为空");
            }
            final List<String> split = StrUtil.split(sort, ',');
            for (final String orderColumnAndSort : split) {
                if (StrUtil.isBlank(orderColumnAndSort)) {
                    continue;
                }
                final List<String> keyVal;
                if (StrUtil.contains(orderColumnAndSort, "-")) {
                    keyVal = StrUtil.split(orderColumnAndSort, '-');
                } else {
                    keyVal = StrUtil.split(orderColumnAndSort, '_');
                }
                final String requestProperty = keyVal.get(0);
                String requestOrder = keyVal.get(1);

                // 判断列名称的合法性，防止SQL注入。只能是【字母，数字，下划线】
                if (!requestProperty.matches("[A-Za-z0-9_]+")) {
                    throw new IllegalArgumentException("非法的排序字段名称：" + requestProperty);
                }

                if (StrUtil.isBlank(requestOrder)) {
                    requestOrder = "asc";
                } else {
                    if (!(StrUtil.equalsIgnoreCase(requestOrder, "ASC") || StrUtil.equalsIgnoreCase(requestOrder, "DESC"))) {
                        throw new IllegalArgumentException("非法的排序策略：" + requestOrder);
                    }
                }

                final Method getMethod = BeanUtil.getBeanDesc(clazz).getGetter(requestProperty);
                final SFunction<T, ?> tsFunction = SFunctionUtil.create(clazz, getMethod);
                lambda.orderBy(true, StrUtil.equalsIgnoreCase("asc", requestOrder), tsFunction);
            }
        }
    }



    public static String getOrderByStr(String orderBy, Class<?> clazz) {
        if (StrUtil.isBlank(orderBy)) {
            return null;
        }
        final TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        final List<TableFieldInfo> fieldList = tableInfo.getFieldList();
        final Map<String, String> fieldNameByTableFieldMap = new HashMap<>();
        fieldNameByTableFieldMap.put(tableInfo.getKeyProperty(), tableInfo.getKeyColumn());
        for (TableFieldInfo tableFieldInfo : fieldList) {
            fieldNameByTableFieldMap.put(tableFieldInfo.getProperty(), tableFieldInfo.getColumn());
        }

        StringBuilder stringBuilder = new StringBuilder();
        final List<String> split = StrUtil.split(orderBy, ',');

        boolean isAppend = false;
        for (final String orderColumnAndSort : split) {
            if (StrUtil.isBlank(orderColumnAndSort)) {
                continue;
            }
            final List<String> keyVal;
            if (StrUtil.contains(orderColumnAndSort, "-")) {
                keyVal = StrUtil.split(orderColumnAndSort, '-');
            } else {
                keyVal = StrUtil.split(orderColumnAndSort, '_');
            }
            final String requestProperty = keyVal.get(0);
            String requestOrder = keyVal.get(1);

            if (StrUtil.isBlank(requestOrder)) {
                requestOrder = "ASC";
            } else if (!(StrUtil.equalsIgnoreCase(requestOrder, "ASC") || StrUtil.equalsIgnoreCase(requestOrder, "DESC"))) {
                throw new IllegalArgumentException("非法的排序策略：" + requestProperty);
            }

            // 判断列名称的合法性，防止SQL注入。只能是【字母，数字，下划线】
            if (!requestProperty.matches("[A-Za-z0-9_]+")) {
                throw new IllegalArgumentException("非法的排序字段名称：" + requestProperty);
            }

            if (isAppend) {
                stringBuilder.append(", ");
            }

            if (fieldNameByTableFieldMap.containsKey(requestProperty)) {
                final String column = fieldNameByTableFieldMap.get(requestProperty);
                stringBuilder.append(column).append(" ").append(requestOrder);
                isAppend = true;
            }
        }
        return stringBuilder.toString();
    }

    // =================================================================================================================
    // =================================================================================================================
    // =================================================================================================================

    /**
     * 将List映射为List，比如List<Person> personList转为List<String> nameList
     *
     * @param originList 原数据
     * @param mappers    映射规则集合
     * @param <T>        原数据的元素类型
     * @return List<T>
     */
    @SafeVarargs
    public static <T> List<T> filterToList(List<T> originList,
                                           Predicate<T>... mappers) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }
        Stream<T> stream = originList.stream();
        for (Predicate<T> mapper : mappers) {
            stream = stream.filter(mapper);
        }
        return stream.collect(Collectors.toList());
    }

    /**
     * 删除空
     *
     * @param list 列表
     * @return {@link List}<{@link T}>
     */
    public static <T> List<T> removeNull(List<T> list) {
        return remove(list, Objects::isNull);
    }

    /**
     * 过滤移除
     *
     * @param originList       源列表
     * @param removeConditions 删除条件
     * @return {@link List}<{@link T}>
     */
    @SafeVarargs
    public static <T> List<T> remove(List<T> originList, Predicate<? super T>... removeConditions) {
        if (!Objects.isNull(originList) && !originList.isEmpty()) {
            Stream<T> stream = originList.stream();
            for (Predicate<? super T> removeCondition : removeConditions) {
                stream = stream.filter(item -> !removeCondition.test(item));
            }
            return stream.collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * 找到
     *
     * @param originList 原数据
     * @param mappers    映射规则集合
     * @param <T>        原数据的元素类型
     * @return Optional<T>
     */
    @SafeVarargs
    public static <T> Optional<T> findFirst(List<T> originList,
                                            Predicate<T>... mappers) {
        if (originList == null || originList.isEmpty()) {
            return Optional.empty();
        }
        Stream<T> stream = originList.stream();
        for (Predicate<T> mapper : mappers) {
            stream = stream.filter(mapper);
        }
        return stream.findFirst();
    }


    /**
     * 匹配
     *
     * @param originList 原数据
     * @param mapper     映射规则
     * @param <T>        原数据的元素类型
     * @return boolean
     */
    public static <T> boolean anyMatch(List<T> originList,
                                       Predicate<T> mapper) {
        if (originList == null || originList.isEmpty()) {
            return false;
        }
        return originList.stream().anyMatch(mapper);
    }

    /**
     * map后match
     *
     * @param originList 原数据
     * @param mapper     映射规则
     * @param <T>        原数据的元素类型
     * @param <R>        新数据的元素类型
     * @return boolean
     */
    public static <T, R> boolean mapToAnyMatch(List<T> originList,
                                               Function<T, R> mapper,
                                               Predicate<R> predicate) {
        if (originList == null || originList.isEmpty()) {
            return false;
        }
        return originList.stream().map(mapper).anyMatch(predicate);
    }

    /**
     * 将List映射为List，比如List<Person> personList转为List<String> nameList
     *
     * @param originList 原数据
     * @param mapper     映射规则
     * @param <T>        原数据的元素类型
     * @param <R>        新数据的元素类型
     * @return List<R>
     */
    public static <T, R> List<R> mapToList(List<T> originList,
                                           Function<T, R> mapper) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }
        return originList.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 将List映射为List，比如List<Person> personList转为List<String> nameList
     *
     * @param originList 原数据
     * @param mapper     映射规则
     * @param <T>        原数据的元素类型
     * @param <R>        新数据的元素类型
     * @return List<R>
     */
    public static <T, R> List<R> mapToDistinctList(List<T> originList,
                                                   Function<T, R> mapper) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }
        return originList.stream().map(mapper).distinct().collect(Collectors.toList());
    }

    /**
     * 将List映射为List，比如List<Person> personList转为List<String> nameList
     *
     * @param originList 原数据
     * @param mapper     映射规则
     * @param <T>        原数据的元素类型
     * @param <R>        新数据的元素类型
     * @return List<R>
     */
    public static <T, R> List<R> mapToDistinctList(List<T> originList,
                                                   Function<T, R> mapper,
                                                   Predicate<R> filter) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }
        return originList.stream().map(mapper).filter(filter).distinct().collect(Collectors.toList());
    }

    /**
     * 将List映射为List，比如List<Person> personList转为List<String> nameList
     *
     * @param originList 原数据
     * @param mapper     映射规则
     * @param <T>        原数据的元素类型
     * @param <R>        新数据的元素类型
     * @return List<R>
     */
    @SuppressWarnings({"all"})
    public static <T, R> List<R> mapToList(List<T> originList,
                                           Function<T, R> mapper,
                                           Predicate<R>... filters) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }
        return filterToList(mapToList(originList, mapper), filters);
    }

    /**
     * 将List转为Map
     *
     * @param list         原数据
     * @param keyExtractor Key的抽取规则
     * @param <K>          Key
     * @param <V>          Value
     * @return Map<K, V>
     */
    public static <K, V> Map<K, V> listToMap(List<V> list,
                                             Function<V, K> keyExtractor) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        Map<K, V> map = new HashMap<>(list.size());
        for (V element : list) {
            K key = keyExtractor.apply(element);
            if (key == null) {
                continue;
            }
            if (!map.containsKey(key)) {
                map.put(key, element);
            }
        }
        return map;
    }

    /**
     * 将List分组
     *
     * @param list         原数据
     * @param keyExtractor Key的抽取规则
     * @param <K>          Key
     * @param <V>          Value
     * @return Map<K, List < V>>
     */
    public static <K, V> Map<K, List<V>> groupByToMap(List<V> list,
                                                      Function<V, K> keyExtractor) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }
        return list.stream().collect(Collectors.groupingBy(keyExtractor));
    }


    /**
     * 将List映射为List，比如List<Person> personList转为List<String> nameList
     *
     * @param originList 原数据
     * @param <T>        原数据的元素类型
     * @param <R>        新数据的元素类型
     * @return List<R>
     */
    public static <T, R> List<R> copyToList(List<T> originList,
                                            Class<R> clazz) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }

        List<R> newList = new ArrayList<>(originList.size());
        for (T originElement : originList) {
            final R r = BeanUtil.copyProperties(originElement, clazz);
            newList.add(r);
        }
        return newList;
    }


    /**
     * 按照属性排重
     *
     * @param originList 原数据
     * @param <T>        原数据的元素类型
     * @return List<T>
     */
    public static <T, U extends Comparable<? super U>> List<T> comparing(List<T> originList,
                                                                         Function<? super T, ? extends U> function) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }
        return originList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(function))), ArrayList::new));
    }


    /**
     * 拼接
     *
     * @param originList 源列表
     * @param delimiter  分隔符
     * @param mapper     方法
     * @return {@link String}
     */
    public static <T> String join(List<T> originList, String delimiter, Function<T, String> mapper) {
        if (Objects.isNull(originList)) {
            return null;
        } else {
            originList = removeNull(originList);
            if (originList.isEmpty()) {
                return "";
            } else {
                List<String> valueList = new ArrayList<>();
                for (T obj : originList) {
                    valueList.add(mapper.apply(obj));
                }
                return String.join(delimiter, valueList);
            }
        }
    }

    public static   <T> SFunction<T, ?> getSFunction(Class<T> clazz, XmMap.SFunction<T, Object> sFunction) {
        final Method disabledMethod = BeanUtil.getBeanDesc(clazz).getGetter(XmUtil.getField(sFunction));
        return SFunctionUtil.create(clazz, disabledMethod);
    }
}
