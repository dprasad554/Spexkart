package com.gsatechworld.spexkart.beans.addwishlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddtoWishlistInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

}