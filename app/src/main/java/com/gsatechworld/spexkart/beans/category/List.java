
package com.gsatechworld.spexkart.beans.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("sub_category_list")
    @Expose
    private java.util.List<SubCategoryList> subCategoryList = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public java.util.List<SubCategoryList> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(java.util.List<SubCategoryList> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

}
