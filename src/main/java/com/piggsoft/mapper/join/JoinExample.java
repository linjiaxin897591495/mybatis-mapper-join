package com.piggsoft.mapper.join;

import com.github.pagehelper.StringUtil;
import org.apache.commons.lang3.reflect.FieldUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.lang.reflect.Field;
import java.util.*;

public class JoinExample {
    protected String orderByClause;

    protected boolean distinct;

    protected Set<String> selectColumns;

    protected List<Criteria> oredCriteria = new ArrayList<>();

    protected Class<?> entityClass;

    protected Map<String, Class<?>> fieldMap = new HashMap<>();

    //动态表名
    protected String tableName;

    protected OrderBy ORDERBY;

    public JoinExample(Class<?> entityClass) {
        this.entityClass = entityClass;
        Field[] fields = FieldUtils.getAllFields(entityClass);
        for (Field field : fields) {
            fieldMap.put(field.getName(), field.getType());
        }
        this.ORDERBY = new OrderBy(this);
    }

    public Map<String, Class<?>> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Class<?>> fieldMap) {
        this.fieldMap = fieldMap;
    }

    private String getColumn(String property) {
        String[] strs = property.split("\\.");
        if (strs.length < 2) {
            throw new RuntimeException("属性名错误");
        }
        Class<?> clazz = getFieldMap().get(strs[0]);
        if (clazz == null) {
            throw new RuntimeException("属性名错误");
        }
        Set<EntityColumn> entityColumns = EntityHelper.getColumns(clazz);
        String column = null;
        for (EntityColumn entityColumn : entityColumns) {
            if (entityColumn.getProperty().equals(strs[1])) {
                column = entityColumn.getColumn();
                break;
            }
        }
        if (column == null) {
            throw new RuntimeException("当前实体类不包含名为" + property + "的属性!");
        }
        return clazz.getSimpleName() + "." + column;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public OrderBy orderBy(String property) {
        this.ORDERBY.orderBy(property);
        return this.ORDERBY;
    }

    public static class OrderBy {
        private JoinExample joinExample;
        private Boolean isProperty;
        //属性和列对应

        public OrderBy(JoinExample joinExample) {
            this.joinExample = joinExample;
        }

        public OrderBy orderBy(String property) {
            String column =property;
            if (column == null) {
                isProperty = false;
                return this;
            }
            if (StringUtil.isNotEmpty(joinExample.getOrderByClause())) {
                joinExample.setOrderByClause(joinExample.getOrderByClause() + "," + column);
            } else {
                joinExample.setOrderByClause(column);
            }
            isProperty = true;
            return this;
        }

        public OrderBy desc() {
            if (isProperty) {
                joinExample.setOrderByClause(joinExample.getOrderByClause() + " DESC");
                isProperty = false;
            }
            return this;
        }

        public OrderBy asc() {
            if (isProperty) {
                joinExample.setOrderByClause(joinExample.getOrderByClause() + " ASC");
                isProperty = false;
            }
            return this;
        }
    }

    public Set<String> getSelectColumns() {
        return selectColumns;
    }


    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * 设置表名
     *
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    protected abstract static class GeneratedCriteria {

        private JoinExample joinExample;

        protected List<Criterion> criteria;
        protected GeneratedCriteria(JoinExample joinExample) {
            super();
            this.joinExample = joinExample;
            criteria = new ArrayList<Criterion>();
        }


        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            if (condition.startsWith("null")) {
                return;
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value) {
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2) {
            criteria.add(new Criterion(condition, value1, value2));
        }

        private String getColumn(String property) {
            return joinExample.getColumn(property);
        }

        public Criteria andIsNull(String property) {
            String column = getColumn(property);
            addCriterion(column + " is null");
            return (Criteria) this;
        }

        public Criteria andIsNotNull(String property) {
            String column = getColumn(property);
            addCriterion(column + " is not null");
            return (Criteria) this;
        }

        public Criteria andEqualTo(String property, Object value) {
            String column = getColumn(property);
            addCriterion(column + " =", value);
            return (Criteria) this;
        }

        public Criteria andNotEqualTo(String property, Object value) {
            String column = getColumn(property);
            addCriterion(column + " <>", value);
            return (Criteria) this;
        }

        public Criteria andGreaterThan(String property, Object value) {
            String column = getColumn(property);
            addCriterion(column + " >", value);
            return (Criteria) this;
        }

        public Criteria andGreaterThanOrEqualTo(String property, Object value) {
            String column = getColumn(property);
            addCriterion(column + " >=", value);
            return (Criteria) this;
        }

        public Criteria andLessThan(String property, Object value) {
            String column = getColumn(property);
            addCriterion(column + " <", value);
            return (Criteria) this;
        }

        public Criteria andLessThanOrEqualTo(String property, Object value) {
            String column = getColumn(property);
            addCriterion(column + " <=", value);
            return (Criteria) this;
        }

        public Criteria andIn(String property, Collection<?> values) {
            String column = getColumn(property);
            addCriterion(column + " in", values);
            return (Criteria) this;
        }

        public Criteria andNotIn(String property, Collection<?> values) {
            String column = getColumn(property);
            addCriterion(column + " not in", values);
            return (Criteria) this;
        }

        public Criteria andBetween(String property, Object value1, Object value2) {
            String column = getColumn(property);
            addCriterion(column + " between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andNotBetween(String property, Object value1, Object value2) {
            String column = getColumn(property);
            addCriterion(column + " not between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andLike(String property, String value) {
            String column = getColumn(property);
            addCriterion(column + "  like", value);
            return (Criteria) this;
        }

        public Criteria andNotLike(String property, String value) {
            String column = getColumn(property);
            addCriterion(column + "  not like", value);
            return (Criteria) this;
        }

        /**
         * 手写条件
         *
         * @param condition 例如 "length(countryname)<5"
         * @return
         */
        public Criteria andCondition(String condition) {
            addCriterion(condition);
            return (Criteria) this;
        }

        /**
         * 手写左边条件，右边用value值
         *
         * @param condition 例如 "length(countryname)="
         * @param value     例如 5
         * @return
         */
        public Criteria andCondition(String condition, Object value) {
            criteria.add(new Criterion(condition, value));
            return (Criteria) this;
        }

    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria(JoinExample joinExample) {
            super(joinExample);
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof Collection<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }
    }
}