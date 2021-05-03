
package com.gsatechworld.spexkart.beans.faceshapes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lists {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("shape")
    @Expose
    private String shape;
    @SerializedName("face_image")
    @Expose
    private String faceImage;
    @SerializedName("glass_image")
    @Expose
    private String glassImage;
    public int getStatus() {

        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @SerializedName("status")
    @Expose
    private int status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public String getGlassImage() {
        return glassImage;
    }

    public void setGlassImage(String glassImage) {
        this.glassImage = glassImage;
    }

}
