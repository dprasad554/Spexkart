package com.gsatechworld.spexkart.beans.productdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailsInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("product_id")
    @Expose
    private String productId;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}