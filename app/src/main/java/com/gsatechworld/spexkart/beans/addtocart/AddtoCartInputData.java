package com.gsatechworld.spexkart.beans.addtocart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddtoCartInputData {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("lense_id")
    @Expose
    private Integer lenseId;
    @SerializedName("frame_size")
    @Expose
    private String frameSize;
    @SerializedName("color_id")
    @Expose
    private Integer colorId;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("api_token")
    @Expose
    private String apiToken;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getLenseId() {
        return lenseId;
    }

    public void setLenseId(Integer lenseId) {
        this.lenseId = lenseId;
    }

    public String getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(String frameSize) {
        this.frameSize = frameSize;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

}