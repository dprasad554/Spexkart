
package com.gsatechworld.spexkart.beans.lensestype;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lenses {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("lense_list")
    @Expose
    private List<LenseList> lenseList = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<LenseList> getLenseList() {
        return lenseList;
    }

    public void setLenseList(List<LenseList> lenseList) {
        this.lenseList = lenseList;
    }

}
