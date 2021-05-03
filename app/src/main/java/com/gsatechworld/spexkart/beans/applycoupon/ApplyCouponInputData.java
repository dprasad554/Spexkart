package com.gsatechworld.spexkart.beans.applycoupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplyCouponInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("cart_id")
    @Expose
    private Integer cartId;
    @SerializedName("coupon_id")
    @Expose
    private String couponId;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}