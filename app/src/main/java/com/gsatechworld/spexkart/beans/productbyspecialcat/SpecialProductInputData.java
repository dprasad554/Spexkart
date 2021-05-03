package com.gsatechworld.spexkart.beans.productbyspecialcat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecialProductInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("eyeglass_category_id")
    @Expose
    private String eyeglassCategoryId;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getEyeglassCategoryId() {
        return eyeglassCategoryId;
    }

    public void setEyeglassCategoryId(String eyeglassCategoryId) {
        this.eyeglassCategoryId = eyeglassCategoryId;
    }

}