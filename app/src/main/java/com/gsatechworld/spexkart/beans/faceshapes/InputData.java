package com.gsatechworld.spexkart.beans.faceshapes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("type")
    @Expose
    private String type;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}