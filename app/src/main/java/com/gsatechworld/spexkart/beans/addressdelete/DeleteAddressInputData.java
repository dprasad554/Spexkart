package com.gsatechworld.spexkart.beans.addressdelete;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteAddressInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("address_id")
    @Expose
    private String addressId;

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

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

}