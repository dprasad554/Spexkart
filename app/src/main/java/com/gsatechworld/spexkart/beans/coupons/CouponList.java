
package com.gsatechworld.spexkart.beans.coupons;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponList {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data_list")
    @Expose
    private List<DataList> dataList = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<DataList> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataList> dataList) {
        this.dataList = dataList;
    }

}
