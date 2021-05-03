package com.gsatechworld.spexkart.beans.addtocart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddtoCart {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cart_item_id")
    @Expose
    private Integer cartItemId;
    @SerializedName("cart_id")
    @Expose
    private Integer cartId;

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

    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

}