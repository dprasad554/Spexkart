
package com.gsatechworld.spexkart.beans.productdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FramesDetail {

    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("width")
    @Expose
    private String width;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

}
