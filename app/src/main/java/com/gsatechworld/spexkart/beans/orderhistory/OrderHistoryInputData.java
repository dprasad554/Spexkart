package com.gsatechworld.spexkart.beans.orderhistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderHistoryInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}