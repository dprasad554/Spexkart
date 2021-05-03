
package com.gsatechworld.spexkart.beans.cartlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartList {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("cart_lists")
    @Expose
    private CartLists cartLists;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public CartLists getCartLists() {
        return cartLists;
    }

    public void setCartLists(CartLists cartLists) {
        this.cartLists = cartLists;
    }

}
