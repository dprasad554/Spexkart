
package com.gsatechworld.spexkart.beans.lensescategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LensesType {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("lense_types")
    @Expose
    private List<LenseType> lenseTypes = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<LenseType> getLenseTypes() {
        return lenseTypes;
    }

    public void setLenseTypes(List<LenseType> lenseTypes) {
        this.lenseTypes = lenseTypes;
    }

}
