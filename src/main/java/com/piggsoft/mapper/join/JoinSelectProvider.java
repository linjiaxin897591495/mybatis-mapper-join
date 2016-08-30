/*
 *
 * Copyright (C) 1999-2016 IFLYTEK Inc.All Rights Reserved.
 * History：
 * Version   Author      Date                              Operation
 * 1.0       yaochen4    2016/8/8                           Create
 */
package com.piggsoft.mapper.join;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.util.ReflectionUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * @author yaochen4
 * @version 1.0
 * @create 2016/8/8
 * @since 1.0
 */
public class JoinSelectProvider extends MapperTemplate {

    public JoinSelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String joinSelect(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append("<if test=\"distinct\">distinct</if> ");
        StringBuilder from = new StringBuilder(" FROM ");
        setResultType(ms, entityClass);

        List<Item> items = new ArrayList<>();
        for (PropertyDescriptor field : ReflectUtils.getBeanProperties(entityClass)) {
            Class<?> fieldClazz = field.getPropertyType();
            sql.append(getAllColumns(fieldClazz));
            Join join = null;
            join = ReflectionUtils.findField(entityClass, field.getName()).getAnnotation(Join.class);
            if (join != null) {
                String s = " LEFT JOIN " + tableName(fieldClazz) + " AS " + fieldClazz.getSimpleName()
                        + " ON " + getColumn(fieldClazz, join.field()) + " = " + getColumn(join.referenceClass(), join.referenceField());
                items.add(new Item(join.index(), s));
            } else {
                String s = tableName(fieldClazz) + " AS " + fieldClazz.getSimpleName();
                items.add(new Item(0, s));
            }
        }
        sql.deleteCharAt(sql.length() - 1);

        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getIndex() - o2.getIndex();
            }
        });
        for (Item item : items) {
            from.append(item.getSql());
        }

        sql.append(from);

        /*JoinCondition condition = entityClass.getAnnotation(JoinCondition.class);
        if (condition != null) {
            //sql.append(" WHERE " + condition.value());

        }*/

        sql.append(SqlHelper.exampleWhereClause());
        sql.append(exampleOrderBy());

        return sql.toString();
    }

    private static String getColumn(Class<?> clazz, String property) {
        Set<EntityColumn> entityColumns = EntityHelper.getColumns(clazz);
        String column = null;
        for (EntityColumn entityColumn : entityColumns) {
            if (entityColumn.getProperty().equals(property)) {
                column = entityColumn.getColumn();
                break;
            }
        }
        if (column == null) {
            throw new RuntimeException("当前实体类不包含名为" + property + "的属性!");
        }
        return clazz.getSimpleName() + "." + column;
    }

    private static String exampleOrderBy() {
        StringBuilder sql = new StringBuilder();
        sql.append("<if test=\"orderByClause != null\">");
        sql.append("order by ${orderByClause}");
        sql.append("</if>");
        return sql.toString();
    }

    public static String getAllColumns(Class<?> entityClass) {
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        String prefix = entityClass.getSimpleName();
        StringBuilder sql = new StringBuilder();
        for (EntityColumn entityColumn : columnList) {
            sql.append(prefix + "." + entityColumn.getColumn() + " AS " + prefix + "_" + entityColumn.getColumn()).append(",");
        }
        return sql.toString();
    }

    protected void setResultType(MappedStatement ms, Class<?> entityClass) {
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        resultMaps.add(newResultMap(ms, entityClass));
        MetaObject metaObject = SystemMetaObject.forObject(ms);
        metaObject.setValue("resultMaps", Collections.unmodifiableList(resultMaps));
        /*metaObject.setValue("hasNestedResultMaps", true);*/
    }

    private ResultMap newResultMap(MappedStatement ms, Class<?> entityClass) {
        List<ResultMapping> resultMappings = new ArrayList<ResultMapping>();
        for (PropertyDescriptor field : ReflectUtils.getBeanProperties(entityClass)) {
            Class<?> fieldClazz = field.getPropertyType();

            addResultType(ms, field.getName(), fieldClazz, resultMappings);

           /* ResultMapping.Builder builder = new ResultMapping.Builder(
                    ms.getConfiguration(),
                    field.getName()
            ).columnPrefix(fieldClazz.getSimpleName() + "_")
                    .nestedResultMapId("com.iflytek.auto.cpmp.mapper." + fieldClazz.getSimpleName() + "Mapper." + "BaseResultMap");
            resultMappings.add(builder.build());*/
        }
        ResultMap.Builder builder = new ResultMap.Builder(ms.getConfiguration(), entityClass.getName() + ".joinSelect", entityClass, resultMappings);
        return builder.build();
    }

    public void addResultType(MappedStatement ms, String filedName, Class<?> entityClass, List<ResultMapping> resultMappings) {
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);

        String prefix = entityClass.getSimpleName() + "_";
        String propertyPrefix = filedName + ".";

        for (EntityColumn entityColumn : columnList) {
            Configuration configuration = ms.getConfiguration();
            ResultMapping.Builder builder = new ResultMapping.Builder(configuration,
                    propertyPrefix + entityColumn.getProperty(),
                    prefix + entityColumn.getColumn(),
                    entityColumn.getJavaType());

            if (entityColumn.getJdbcType() != null) {
                builder.jdbcType(entityColumn.getJdbcType());
            }
            if (entityColumn.getTypeHandler() != null) {
                try {
                    builder.typeHandler(entityColumn.getTypeHandler().newInstance());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            /*List<ResultFlag> flags = new ArrayList<ResultFlag>();
            if (entityColumn.isId()) {
                flags.add(ResultFlag.ID);
            }*/
            resultMappings.add(builder.build());
        }
    }

    public static class Item {
        public Item(int index, String sql) {
            this.index = index;
            this.sql = sql;
        }

        private int index;
        private String sql;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }
    }

}
