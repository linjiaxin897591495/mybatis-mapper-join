package com.piggsoft.demo;

import com.piggsoft.mapper.join.JoinExample;
import org.junit.Test;

/**
 * @author yaochen4
 * @version 1.0
 * @create 2016/8/30
 * @since 1.0
 */
public class TestCase {

    private ZCommonMapper zCommonMapper;

    @Test
    public void test() {
        JoinExample joinExample = new JoinExample(CardInfo.class);
        joinExample.createCriteria().andEqualTo("subCard.id", "003324930b46435996c1d1345d4c4c31");
        joinExample.orderBy("subCard.id").desc();
        Object o = zCommonMapper.joinSelect(joinExample);
    }

}
