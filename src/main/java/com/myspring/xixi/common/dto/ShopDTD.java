package com.myspring.xixi.common.dto;

public class ShopDTD {
    //分类名称
    String className;
    //分类id
    Integer classifyId ;
    //照片
    byte[] commodityImage;
    //商品名称
    String commodityName;
    //商品价格
    Integer commodityPrice;
    //联系方式
    Integer contactWay;
    //商品id
    Integer commodityId;
    //商品状态 0审核 1通过 2不通过
    Integer pass;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public byte[] getCommodityImage() {
        return commodityImage;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public void setCommodityImage(byte[] commodityImage) {
        this.commodityImage = commodityImage;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public void setCommodityPrice(Integer commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public void setContactWay(Integer contactWay) {
        this.contactWay = contactWay;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCommodityPrice() {
        return commodityPrice;
    }

    public Integer getContactWay() {
        return contactWay;
    }

    public Integer getCommodityId() {
        return commodityId;
    }



}
