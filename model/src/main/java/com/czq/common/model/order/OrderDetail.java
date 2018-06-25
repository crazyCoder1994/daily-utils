package com.czq.common.model.order;

import com.czq.common.model.order.annotations.Row;

public class OrderDetail {

    @Row("客户编号")
    private String custid;

    @Row("品类")
    private String category;

    @Row("店铺名称")
    private String shopName;

    @Row("型号")
    private String model;

    @Row("实付金额")
    private float price;

    @Row("省份")
    private String province;

    @Row("市")
    private String city;

    @Row("区")
    private String district;

    @Row("购买日期")
    private String buyDate;

    @Row("顾客姓名")
    private String customerName;

    @Row("顾客手机")
    private String mobile;

    @Row("地址")
    private String address;

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "custid='" + custid + '\'' +
                ", category='" + category + '\'' +
                ", shopName='" + shopName + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", buyDate='" + buyDate + '\'' +
                ", customerName='" + customerName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
