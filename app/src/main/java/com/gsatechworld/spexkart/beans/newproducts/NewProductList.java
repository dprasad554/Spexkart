
package com.gsatechworld.spexkart.beans.newproducts;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewProductList {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("product_lists")
    @Expose
    private List<ProductList> productLists = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ProductList> getProductLists() {
        return productLists;
    }

    public void setProductLists(List<ProductList> productLists) {
        this.productLists = productLists;
    }

}
