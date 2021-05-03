package com.gsatechworld.spexkart.beans.productbysubcat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductBySubInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("sub_category_id")
    @Expose
    private String subCategoryId;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

}