package com.piggsoft.demo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_card_distribute_his")
public class CardDistributeHis implements Serializable {
    @Id
    private String id;

    /**
     * 渠道卡id
     */
    @Column(name = "card_id")
    private String cardId;

    /**
     * 分配者
     */
    private String distributer;

    /**
     * 接收者
     */
    private String reciver;

    private String creator;

    @Column(name = "create_tm")
    private Date createTm;

    private String updater;

    @Column(name = "update_tm")
    private Date updateTm;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取渠道卡id
     *
     * @return card_id - 渠道卡id
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 设置渠道卡id
     *
     * @param cardId 渠道卡id
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取分配者
     *
     * @return distributer - 分配者
     */
    public String getDistributer() {
        return distributer;
    }

    /**
     * 设置分配者
     *
     * @param distributer 分配者
     */
    public void setDistributer(String distributer) {
        this.distributer = distributer;
    }

    /**
     * 获取接收者
     *
     * @return reciver - 接收者
     */
    public String getReciver() {
        return reciver;
    }

    /**
     * 设置接收者
     *
     * @param reciver 接收者
     */
    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return create_tm
     */
    public Date getCreateTm() {
        return createTm;
    }

    /**
     * @param createTm
     */
    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    /**
     * @return updater
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * @param updater
     */
    public void setUpdater(String updater) {
        this.updater = updater;
    }

    /**
     * @return update_tm
     */
    public Date getUpdateTm() {
        return updateTm;
    }

    /**
     * @param updateTm
     */
    public void setUpdateTm(Date updateTm) {
        this.updateTm = updateTm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cardId=").append(cardId);
        sb.append(", distributer=").append(distributer);
        sb.append(", reciver=").append(reciver);
        sb.append(", creator=").append(creator);
        sb.append(", createTm=").append(createTm);
        sb.append(", updater=").append(updater);
        sb.append(", updateTm=").append(updateTm);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}