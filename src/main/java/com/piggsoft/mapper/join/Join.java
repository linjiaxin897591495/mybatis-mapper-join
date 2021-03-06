package com.piggsoft.mapper.join;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yaochen4
 * @version 1.0
 * @create 2016/8/8
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Join {
    String field();
    Class<?> referenceClass();
    String referenceField();
    int index();
}
