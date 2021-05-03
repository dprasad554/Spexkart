package com.gsatechworld.spexkart.beans.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchInput {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("search")
    @Expose
    private String search;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}