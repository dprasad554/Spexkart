
package com.gsatechworld.spexkart.beans.cartlist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartLists {

    @SerializedName("cart_id")
    @Expose
    private Integer cartId;
    @SerializedName("sub_total")
    @Expose
    private Integer subTotal;
    @SerializedName("gst_percentage")
    @Expose
    private String gstPercentage;
    @SerializedName("gst_amount")
    @Expose
    private String gstAmount;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("coupon_id")
    @Expose
    private String couponId;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("coupon_short_desc")
    @Expose
    private String couponShortDesc;
    @SerializedName("coupon_details")
    @Expose
    private String couponDetails;
    @SerializedName("grand_total")
    @Expose
    private Integer grandTotal;
    @SerializedName("product_items")
    @Expose
    private List<ProductItem> productItems = null;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    public String getGstPercentage() {
        return gstPercentage;
    }

    public void setGstPercentage(String gstPercentage) {
        this.gstPercentage = gstPercentage;
    }

    public String getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(String gstAmount) {
        this.gstAmount = gstAmount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponShortDesc() {
        return couponShortDesc;
    }

    public void setCouponShortDesc(String couponShortDesc) {
        this.couponShortDesc = couponShortDesc;
    }

    public String getCouponDetails() {
        return couponDetails;
    }

    public void setCouponDetails(String couponDetails) {
        this.couponDetails = couponDetails;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

}
