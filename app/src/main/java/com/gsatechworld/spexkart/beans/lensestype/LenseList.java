
package com.gsatechworld.spexkart.beans.lensestype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LenseList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("features")
    @Expose
    private String features;
    @SerializedName("is_offer")
    @Expose
    private String isOffer;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("discount_percentage")
    @Expose
    private String discountPercentage;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @SerializedName("product_id")
    @Expose
    private String product_id;

    public String getColor_id() {
        return color_id;
    }

    public void setColor_id(String color_id) {
        this.color_id = color_id;
    }

    @SerializedName("color_id")
    @Expose
    private String color_id;

    public String getFrame_size() {
        return frame_size;
    }

    public void setFrame_size(String frame_size) {
        this.frame_size = frame_size;
    }

    @SerializedName("frame_size")
    @Expose
    private String frame_size;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getIsOffer() {
        return isOffer;
    }

    public void setIsOffer(String isOffer) {
        this.isOffer = isOffer;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

}
