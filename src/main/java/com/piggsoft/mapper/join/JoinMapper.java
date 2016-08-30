/*
 *
 * Copyright (C) 1999-2016 IFLYTEK Inc.All Rights Reserved.
 * History：
 * Version   Author      Date                              Operation
 * 1.0       yaochen4    2016/8/8                           Create
 */
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
