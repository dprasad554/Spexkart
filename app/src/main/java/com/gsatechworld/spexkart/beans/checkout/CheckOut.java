package com.gsatechworld.spexkart.beans.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckOut {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("order_slno")
    @Expose
    private Integer orderSlno;
    @SerializedName("order_id")
    @Expose
    private String orderId;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getOrderSlno() {
        return orderSlno;
    }

    public void setOrderSlno(Integer orderSlno) {
        this.orderSlno = orderSlno;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}