
package com.gsatechworld.spexkart.beans.productcolor;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductColor {

    @SerializedName("product_color")
    @Expose
    private List<ProductColor_> productColor = null;

    public List<ProductColor_> getProductColor() {
        return productColor;
    }

    public void setProductColor(List<ProductColor_> productColor) {
        this.productColor = productColor;
    }

}
