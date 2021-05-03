
package com.gsatechworld.spexkart.beans.agegroup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgeGroup {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("lists")
    @Expose
    private java.util.List<List> lists = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public java.util.List<List> getLists() {
        return lists;
    }

    public void setLists(java.util.List<List> lists) {
        this.lists = lists;
    }

}
