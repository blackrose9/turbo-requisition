package com.example.easyrequisition.model;

public class Order {
    private String mCustomerName;
    private String mOrderContent;
    private String index;

    public Order() {
    }

    public Order(String customerName, String orderContent) {
        this.mCustomerName = customerName;
        this.mOrderContent = orderContent;
        this.index = "notSpecified";
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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
