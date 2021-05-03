package com.gsatechworld.spexkart.beans.generateorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenerateOrderInputData {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("api_token")
    @Expose
    private String apiToken;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

}