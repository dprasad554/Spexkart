package com.gsatechworld.spexkart.beans.Social;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialInputData {

    @SerializedName("social_id")
    @Expose
    private String socialId;
    @SerializedName("login_type")
    @Expose
    private String loginType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("api_token")
    @Expose
    private String apiToken;

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

}