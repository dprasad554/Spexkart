package com.gsatechworld.spexkart.beans.lensestype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LensesListInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("lense_type_id")
    @Expose
    private String lenseTypeId;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getLenseTypeId() {
        return lenseTypeId;
    }

    public void setLenseTypeId(String lenseTypeId) {
        this.lenseTypeId = lenseTypeId;
    }

}