package com.gsatechworld.spexkart.beans.specialcategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecialCategoryInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

}