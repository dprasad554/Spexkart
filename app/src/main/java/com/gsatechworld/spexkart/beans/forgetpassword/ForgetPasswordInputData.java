package com.gsatechworld.spexkart.beans.forgetpassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPasswordInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("email")
    @Expose
    private String email;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}