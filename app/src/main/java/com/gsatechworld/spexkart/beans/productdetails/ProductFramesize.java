
package com.gsatechworld.spexkart.beans.productdetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductFramesize {

    @SerializedName("frame_size")
    @Expose
    private String frameSize;
    @SerializedName("frames_details")
    @Expose
    private List<FramesDetail> framesDetails = null;

    @SerializedName("status")
    @Expose
    private int status;

    public String getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(String frameSize) {
        this.frameSize = frameSize;
    }

    public List<FramesDetail> getFramesDetails() {
        return framesDetails;
    }

    public void setFramesDetails(List<FramesDetail> framesDetails) {
        this.framesDetails = framesDetails;
    }
    public int getStatus() {

        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }



}
