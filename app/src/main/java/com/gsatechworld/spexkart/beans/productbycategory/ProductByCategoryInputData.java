package com.gsatechworld.spexkart.beans.productbycategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductByCategoryInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("category_id")
    @Expose
    private String categoryId;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

}