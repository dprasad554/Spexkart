
package com.gsatechworld.spexkart.beans.banner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeBanner {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("lists")
    @Expose
    private java.util.List<com.gsatechworld.spexkart.beans.banner.List> lists = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public java.util.List<com.gsatechworld.spexkart.beans.banner.List> getLists() {
        return lists;
    }

    public void setLists(java.util.List<com.gsatechworld.spexkart.beans.banner.List> lists) {
        this.lists = lists;
    }

}
