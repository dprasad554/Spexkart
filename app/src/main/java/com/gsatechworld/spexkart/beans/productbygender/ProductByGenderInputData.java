package com.gsatechworld.spexkart.beans.productbygender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductByGenderInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("gender")
    @Expose
    private String gender;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}