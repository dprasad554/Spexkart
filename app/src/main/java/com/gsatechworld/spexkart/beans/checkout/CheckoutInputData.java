package com.gsatechworld.spexkart.beans.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckoutInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("address_id")
    @Expose
    private String addressId;
    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("payment_id")
    @Expose
    private String paymentId;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

}