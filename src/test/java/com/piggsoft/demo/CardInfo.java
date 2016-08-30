/*
 *
 * Copyright (C) 1999-2016 IFLYTEK Inc.All Rights Reserved.
 * History：
 * Version   Author      Date                              Operation
 * 1.0       yaochen4    2016/7/14                           Create
 */
package com.piggsoft.demo;


import com.piggsoft.mapper.join.Join;

import java.io.Serializable;

/**
 * @author yaochen4
 * @version 1.0
 * @create 2016/7/14
 * @since 1.0
 */
public class CardInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private CardDistributeHis cardDistributeHis;
    @Join(field ="id", referenceClass = CardDistributeHis.class, referenceField = "cardId", index = 1)
    private Card card;

    public CardDistributeHis getCardDistributeHis() {
        return cardDistributeHis;
    }

    public void setCardDistributeHis(CardDistributeHis cardDistributeHis) {
        this.cardDistributeHis = cardDistributeHis;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
