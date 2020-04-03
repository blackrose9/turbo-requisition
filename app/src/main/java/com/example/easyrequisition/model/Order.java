package com.example.easyrequisition.model;

public class Order {
    private String mCustomerName;
    private String mOrderContent;

    public Order() {
    }

    public Order(String customerName, String orderContent) {
        this.mCustomerName = customerName;
        this.mOrderContent = orderContent;
    }

    public String getmCustomerName() {
        return mCustomerName;
    }

    public void setmCustomerName(String mCustomerName) {
        this.mCustomerName = mCustomerName;
    }

    public String getmOrderContent() {
        return mOrderContent;
    }

    public void setmOrderContent(String mOrderContent) {
        this.mOrderContent = mOrderContent;
    }
}
