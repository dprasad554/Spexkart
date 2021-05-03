package com.gsatechworld.spexkart.beans.uploadprescription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrescriptionInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("cart_item_id")
    @Expose
    private Integer cartItemId;
    @SerializedName("prescription")
    @Expose
    private String prescription;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

}