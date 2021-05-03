
package com.gsatechworld.spexkart.beans.productdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetails {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("product_details")
    @Expose
    private ProductDetails_ productDetails;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ProductDetails_ getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails_ productDetails) {
        this.productDetails = productDetails;
    }

}
