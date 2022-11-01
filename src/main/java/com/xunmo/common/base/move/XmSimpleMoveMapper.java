package com.xunmo.common.base.move;

import cn.hutool.core.bean.BeanDesc;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.solon.toolkit.SqlHelper;
import com.xunmo.annotations.SortUni;
import com.xunmo.common.base.XmMapper;
import com.xunmo.utils.SFunctionUtil;
import com.xunmo.utils.XmUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface XmSimpleMoveMapper<T extends XmSimpleMoveEntity> extends XmMapper<T> {

    default int getMax(T sortEntigy) {
        LambdaQueryWrapper<T> lambdaQuery = Wrappers.lambdaQuery();
        final Class<T> clazz = currentModelClass();
        final SFunction<T, ?> sortFunction = XmUtil.getSFunction(clazz, T::getSort);
        setLambdaQuery(sortEntigy, lambdaQuery, clazz);
        List<T> dicts = this.selectList(lambdaQuery
                .orderByDesc(sortFunction));
        if (CollUtil.isEmpty(dicts)) {
            return 0;
        }
        return dicts.get(0).getSort();
    }

    default int plusSort(T sortEntigy) {
        LambdaQueryWrapper<T> lambdaQuery = Wrappers.lambdaQuery();
        final Class<T> clazz = currentModelClass();
        final SFunction<T, ?> sortFunction = XmUtil.getSFunction(clazz, T::getSort);
        setLambdaQuery(sortEntigy, lambdaQuery, clazz);
        List<T> dicts = this.selectList(lambdaQuery
                .lt(sortFunction, sortEntigy.getSort()));
        if (CollUtil.isNotEmpty(dicts)) {
            dicts.forEach(s -> {
                s.setSort(s.getSort() + 1);
                s.setLastUpdateUser(sortEntigy.getLastUpdateUser());
                s.setLastUpdateTime(sortEntigy.getLastUpdateTime());
                s.setLastUpdateUserNickName(sortEntigy.getLastUpdateUserNickName());
            });
            this.updateBatch(dicts);
            return 1;
        }
        return 0;
    }

    default T getBeforeOne(T sortEntigy) {
        LambdaQueryWrapper<T> lambdaQuery = Wrappers.lambdaQuery();
        final Class<T> clazz = currentModelClass();
        final SFunction<T, ?> sortFunction = XmUtil.getSFunction(clazz, T::getSort);

        setLambdaQuery(sortEntigy, lambdaQuery, clazz);
        List<T> dicts = this.selectList(lambdaQuery
                .lt(sortFunction, sortEntigy.getSort())
                .orderByDesc(sortFunction)
                .last(" limit 1"));
        if (CollUtil.isEmpty(dicts)) {
            return null;
        }
        return dicts.get(0);
    }

    default T getAfterOne(T sortEntigy) {
        LambdaQueryWrapper<T> lambdaQuery = Wrappers.<T>lambdaQuery();
        final Class<T> clazz = currentModelClass();
        final SFunction<T, ?> sortFunction = XmUtil.getSFunction(clazz, T::getSort);

        setLambdaQuery(sortEntigy, lambdaQuery, clazz);
        List<T> dicts = this.selectList(lambdaQuery
                .gt(sortFunction, sortEntigy.getSort())
                .orderByAsc(sortFunction)
                .last(" limit 1"));
        if (CollUtil.isEmpty(dicts)) {
            return null;
        }
        return dicts.get(0);
    }

    default int changeSort(@Param("preId") String preId, @Param("nextId") String nextId) {
        final Class<T> modelClass = currentModelClass();
        // 使用MybatisPlus自己的SqlHelper获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlHelper.sqlSessionFactory(modelClass);
        // 通过SqlSessionFactory创建一个新的SqlSession，并获取全局配置
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            Configuration configuration = sqlSessionFactory.getConfiguration();
            // 制作命名空间，用于将这个SQL创建的MappedStatement注册到MybatisPlus中
            final String id = this.getClass().getName() + "." + "changeSort";
            if (!configuration.hasStatement(id, false)) {
                final TableInfo tableInfo = TableInfoHelper.getTableInfo(modelClass);
                final String tableName = tableInfo.getTableName();
                final String keyColumn = tableInfo.getKeyColumn();

                Map<String, String> columnNameMap = new HashMap<>();
                columnNameMap.put("id", keyColumn);
                columnNameMap.put("tableName", tableName);
                columnNameMap.put("otherField", " ");

                Field[] fields = ReflectUtil.getFields(modelClass, field -> field.getDeclaredAnnotation(SortUni.class) != null);
                if (ArrayUtil.isNotEmpty(fields)) {

                    Map<String, String> nameByColumnNameMap = new HashMap<>();
                    if (CollUtil.isNotEmpty(tableInfo.getFieldList())) {
                        for (TableFieldInfo tableFieldInfo : tableInfo.getFieldList()) {
                            final String column = tableFieldInfo.getColumn();
                            final String name = tableFieldInfo.getField().getName();
                            nameByColumnNameMap.put(name, column);
                        }
                    }

                    StringBuilder otherField = new StringBuilder(", ");
                    for (Field field : fields) {
                        String name = field.getName();
                        if (nameByColumnNameMap.containsKey(name)) {
                            final String columnName = nameByColumnNameMap.get(name);
                            otherField.append(StrUtil.format("g1.{} = g2.{}, g2.{} = g1.{}", columnName, columnName, columnName, columnName));
                        }
                    }
                    final String value = otherField.toString();
                    if (value.length() > 2) {
                        columnNameMap.put("otherField", value);
                    }
                }


                String sql = StrUtil.format("UPDATE {tableName} AS g1, {tableName} AS g2 " +
                        " SET g1.sort = g2.sort, g2.sort = g1.sort {otherField} " +
                        " where ( g1.{id} = #{preId} AND g2.{id} = #{nextId} ) " +
                        "   OR ( g1.{id} = #{nextId} AND g2.{id} = #{preId} )", columnNameMap);
                RawSqlSource rawSqlSource = new RawSqlSource(configuration, sql, Object.class);
                MappedStatement.Builder builder = new MappedStatement.Builder(configuration, id, rawSqlSource, SqlCommandType.UPDATE);
                MappedStatement mappedStatement = builder.build();
                // 将创建的MappedStatement注册到配置中
                configuration.addMappedStatement(mappedStatement);
            }

            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("preId", preId);
            paramMap.put("nextId", nextId);

            // 使用SqlSession查询原生SQL
            int updateEffect = sqlSession.update(id, paramMap);
            sqlSession.close();
            return updateEffect;
        }
    }


    default void setLambdaQuery(T sortEntigy, LambdaQueryWrapper<T> lambdaQuery, Class<T> aClass) {
        BeanDesc beanDesc = BeanUtil.getBeanDesc(aClass);
        Field[] fields = ReflectUtil.getFields(aClass, field -> field.getDeclaredAnnotation(SortUni.class) != null);
        if (ArrayUtil.isNotEmpty(fields)) {
            for (Field field : fields) {
                SortUni sortUni = field.getDeclaredAnnotation(SortUni.class);
                String defaultValue = sortUni.defaultValue();
                String name = field.getName();
                final Method getMethod = beanDesc.getGetter(name);
                SFunction<T, ?> sFunction = SFunctionUtil.create(aClass, getMethod);
                Object fieldValue = ReflectUtil.getFieldValue(sortEntigy, name);
                boolean sortUniIsNull = sortUni.isNull();
                if (ObjectUtil.isNull(fieldValue) && sortUniIsNull) {
                    // null
                    lambdaQuery.isNull(sFunction);
                } else if (ObjectUtil.isNull(fieldValue) && !sortUniIsNull) {
                    // -1
                    lambdaQuery.eq(sFunction, defaultValue);
                } else {
                    lambdaQuery.eq(sFunction, fieldValue);
                }
            }
        }
    }


}