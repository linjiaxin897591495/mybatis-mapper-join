package com.piggsoft.demo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_card")
public class Card implements Serializable {
    @Id
    private String id;

    /**
     * 卡号，操作时卡的标志
     */
    @Column(name = "card_no")
    private String cardNo;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private String isDelete;

    /**
     * 出厂时间（从移动运营商出厂）
     */
    @Column(name = "product_tm")
    private Date productTm;

    /**
     * 激活时间
     */
    @Column(name = "active_tm")
    private Date activeTm;

    private String creator;

    @Column(name = "create_tm")
    private Date createTm;

    private String updater;

    /**
     * 变更时间
     */
    @Column(name = "update_tm")
    private Date updateTm;

    /**
     * 导入原因
     */
    @Column(name = "import_reason")
    private String importReason;

    /**
     * 批次
     */
    @Column(name = "batch_no")
    private String batchNo;

    @Column(name = "operator_id")
    private String operatorId;

    private String iccid;

    @Column(name = "belong_term")
    private String belongTerm;

    /**
     * 生效时间
     */
    @Column(name = "inital_tm")
    private Date initalTm;

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
     * 获取卡号，操作时卡的标志
     *
     * @return card_no - 卡号，操作时卡的标志
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 设置卡号，操作时卡的标志
     *
     * @param cardNo 卡号，操作时卡的标志
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 获取是否删除
     *
     * @return is_delete - 是否删除
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
     *
     * @param isDelete 是否删除
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取出厂时间（从移动运营商出厂）
     *
     * @return product_tm - 出厂时间（从移动运营商出厂）
     */
    public Date getProductTm() {
        return productTm;
    }

    /**
     * 设置出厂时间（从移动运营商出厂）
     *
     * @param productTm 出厂时间（从移动运营商出厂）
     */
    public void setProductTm(Date productTm) {
        this.productTm = productTm;
    }

    /**
     * 获取激活时间
     *
     * @return active_tm - 激活时间
     */
    public Date getActiveTm() {
        return activeTm;
    }

    /**
     * 设置激活时间
     *
     * @param activeTm 激活时间
     */
    public void setActiveTm(Date activeTm) {
        this.activeTm = activeTm;
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
     * 获取变更时间
     *
     * @return update_tm - 变更时间
     */
    public Date getUpdateTm() {
        return updateTm;
    }

    /**
     * 设置变更时间
     *
     * @param updateTm 变更时间
     */
    public void setUpdateTm(Date updateTm) {
        this.updateTm = updateTm;
    }

    /**
     * 获取导入原因
     *
     * @return import_reason - 导入原因
     */
    public String getImportReason() {
        return importReason;
    }

    /**
     * 设置导入原因
     *
     * @param importReason 导入原因
     */
    public void setImportReason(String importReason) {
        this.importReason = importReason;
    }

    /**
     * 获取批次
     *
     * @return batch_no - 批次
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * 设置批次
     *
     * @param batchNo 批次
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * @return operator_id
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * @param operatorId
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * @return iccid
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * @param iccid
     */
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    /**
     * @return belong_term
     */
    public String getBelongTerm() {
        return belongTerm;
    }

    /**
     * @param belongTerm
     */
    public void setBelongTerm(String belongTerm) {
        this.belongTerm = belongTerm;
    }

    /**
     * 获取生效时间
     *
     * @return inital_tm - 生效时间
     */
    public Date getInitalTm() {
        return initalTm;
    }

    /**
     * 设置生效时间
     *
     * @param initalTm 生效时间
     */
    public void setInitalTm(Date initalTm) {
        this.initalTm = initalTm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cardNo=").append(cardNo);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", productTm=").append(productTm);
        sb.append(", activeTm=").append(activeTm);
        sb.append(", creator=").append(creator);
        sb.append(", createTm=").append(createTm);
        sb.append(", updater=").append(updater);
        sb.append(", updateTm=").append(updateTm);
        sb.append(", importReason=").append(importReason);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", iccid=").append(iccid);
        sb.append(", belongTerm=").append(belongTerm);
        sb.append(", initalTm=").append(initalTm);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}