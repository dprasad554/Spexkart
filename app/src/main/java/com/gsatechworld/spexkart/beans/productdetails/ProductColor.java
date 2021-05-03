
package com.gsatechworld.spexkart.beans.productdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductColor {

    @SerializedName("color_id")
    @Expose
    private Integer colorId;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("code")
    @Expose
    private String code;

    public int getStatus() {

        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @SerializedName("status")
    @Expose
    private int status;


    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
