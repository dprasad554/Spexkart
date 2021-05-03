
package com.gsatechworld.spexkart.beans.faceshapes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FaceShapes {

    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("lists")
    @Expose
    public List<Lists> lists = null;
    public List<Lists> getlists() {
        return lists;
    }
    public void setLists(List<Lists> lists) {
        this.lists = lists;
    }
}
