package com.gsatechworld.spexkart.beans.subcategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}