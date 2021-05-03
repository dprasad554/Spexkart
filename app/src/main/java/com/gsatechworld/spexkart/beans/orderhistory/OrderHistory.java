
package com.gsatechworld.spexkart.beans.orderhistory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderHistory {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("orders_list")
    @Expose
    private List<OrdersList> ordersList = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<OrdersList> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<OrdersList> ordersList) {
        this.ordersList = ordersList;
    }

}
