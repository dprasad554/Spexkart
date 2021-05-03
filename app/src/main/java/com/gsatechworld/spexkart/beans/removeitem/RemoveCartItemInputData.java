package com.gsatechworld.spexkart.beans.removeitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoveCartItemInputData {

    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("cart_item_id")
    @Expose
    private String cartItemId;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

}