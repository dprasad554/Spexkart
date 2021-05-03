
package com.gsatechworld.spexkart.beans.cartlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductItem {

    @SerializedName("cart_item_id")
    @Expose
    private Integer cartItemId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("lense_id")
    @Expose
    private Integer lenseId;
    @SerializedName("lense_type_id")
    @Expose
    private Integer lenseTypeId;
    @SerializedName("prescription_required")
    @Expose
    private String prescriptionRequired;
    @SerializedName("frame_size")
    @Expose
    private String frameSize;
    @SerializedName("color_id")
    @Expose
    private Integer colorId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("product_price")
    @Expose
    private Integer productPrice;
    @SerializedName("lense_price")
    @Expose
    private Integer lensePrice;
    @SerializedName("unit_price")
    @Expose
    private Integer unitPrice;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;
    @SerializedName("prescription")
    @Expose
    private String prescription;
    @SerializedName("booking_appointments")
    @Expose
    private BookingAppointments bookingAppointments;

    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getLenseId() {
        return lenseId;
    }

    public void setLenseId(Integer lenseId) {
        this.lenseId = lenseId;
    }

    public Integer getLenseTypeId() {
        return lenseTypeId;
    }

    public void setLenseTypeId(Integer lenseTypeId) {
        this.lenseTypeId = lenseTypeId;
    }

    public String getPrescriptionRequired() {
        return prescriptionRequired;
    }

    public void setPrescriptionRequired(String prescriptionRequired) {
        this.prescriptionRequired = prescriptionRequired;
    }

    public String getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(String frameSize) {
        this.frameSize = frameSize;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getLensePrice() {
        return lensePrice;
    }

    public void setLensePrice(Integer lensePrice) {
        this.lensePrice = lensePrice;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public BookingAppointments getBookingAppointments() {
        return bookingAppointments;
    }

    public void setBookingAppointments(BookingAppointments bookingAppointments) {
        this.bookingAppointments = bookingAppointments;
    }

}
