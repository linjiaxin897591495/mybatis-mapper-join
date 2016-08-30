package com.piggsoft.mapper.join;

import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author yaochen4
 * @version 1.0
 * @create 2016/8/8
 * @since 1.0
 */
public interface JoinMapper<T> {

    @SelectProvider(type = JoinSelectProvider.class, method = "dynamicSQL")
    List<T> joinSelect(JoinExample params);

}
