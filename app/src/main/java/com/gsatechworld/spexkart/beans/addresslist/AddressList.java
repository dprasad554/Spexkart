
package com.gsatechworld.spexkart.beans.addresslist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressList {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("address_list")
    @Expose
    private List<AddressList_> addressList = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<AddressList_> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressList_> addressList) {
        this.addressList = addressList;
    }

}
