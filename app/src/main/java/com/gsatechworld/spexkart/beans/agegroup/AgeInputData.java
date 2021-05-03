package com.gsatechworld.spexkart.beans.agegroup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgeInputData {

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