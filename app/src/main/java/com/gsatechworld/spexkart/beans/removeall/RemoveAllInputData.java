package com.gsatechworld.spexkart.beans.removeall;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoveAllInputData {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("cart_id")
    @Expose
    private Integer cartId;
    @SerializedName("api_token")
    @Expose
    private String apiToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

}