package com.gsatechworld.spexkart.utils;


import com.gsatechworld.spexkart.beans.Social.SocialLogin;
import com.gsatechworld.spexkart.beans.addaddress.AddAddress;
import com.gsatechworld.spexkart.beans.addaddress.AddAddressInputData;
import com.gsatechworld.spexkart.beans.addressdelete.DeleteAddress;
import com.gsatechworld.spexkart.beans.addressdelete.DeleteAddressInputData;
import com.gsatechworld.spexkart.beans.addresslist.AddressList;
import com.gsatechworld.spexkart.beans.addresslist.AddressListInputData;
import com.gsatechworld.spexkart.beans.addressupdate.UpdateAddress;
import com.gsatechworld.spexkart.beans.addressupdate.UpdateAddressInputData;
import com.gsatechworld.spexkart.beans.addtocart.AddtoCart;
import com.gsatechworld.spexkart.beans.addtocart.AddtoCartInputData;
import com.gsatechworld.spexkart.beans.addwishlist.AddtoWishlist;
import com.gsatechworld.spexkart.beans.addwishlist.AddtoWishlistInputData;
import com.gsatechworld.spexkart.beans.agegroup.AgeGroup;
import com.gsatechworld.spexkart.beans.agegroup.AgeInputData;
import com.gsatechworld.spexkart.beans.Social.SocialInputData;
import com.gsatechworld.spexkart.beans.applycoupon.ApplyCoupon;
import com.gsatechworld.spexkart.beans.applycoupon.ApplyCouponInputData;
import com.gsatechworld.spexkart.beans.banner.HomeBanner;
import com.gsatechworld.spexkart.beans.bookapointment.AppointmentInputData;
import com.gsatechworld.spexkart.beans.bookapointment.BookApointment;
import com.gsatechworld.spexkart.beans.cartlist.CartList;
import com.gsatechworld.spexkart.beans.cartlist.CartListInputData;
import com.gsatechworld.spexkart.beans.category.CategoryInputData;
import com.gsatechworld.spexkart.beans.category.GlassCategory;
import com.gsatechworld.spexkart.beans.checkout.CheckOut;
import com.gsatechworld.spexkart.beans.checkout.CheckoutInputData;
import com.gsatechworld.spexkart.beans.coupons.CouponList;
import com.gsatechworld.spexkart.beans.coupons.CouponsInputData;
import com.gsatechworld.spexkart.beans.editprofile.EditProfile;
import com.gsatechworld.spexkart.beans.editprofile.EditProfileInputdata;
import com.gsatechworld.spexkart.beans.faceshapes.FaceShapes;
import com.gsatechworld.spexkart.beans.faceshapes.InputData;
import com.gsatechworld.spexkart.beans.forgetpassword.ForgetPasswordInputData;
import com.gsatechworld.spexkart.beans.forgetpassword.Forgetpassword;
import com.gsatechworld.spexkart.beans.generateorder.GenerateOrder;
import com.gsatechworld.spexkart.beans.generateorder.GenerateOrderInputData;
import com.gsatechworld.spexkart.beans.getuserprofile.GetUserProfile;
import com.gsatechworld.spexkart.beans.getuserprofile.UserProfileInputData;
import com.gsatechworld.spexkart.beans.lensescategory.LensesType;
import com.gsatechworld.spexkart.beans.lensescategory.LensesTypeInput;
import com.gsatechworld.spexkart.beans.lensestype.Lenses;
import com.gsatechworld.spexkart.beans.lensestype.LensesListInputData;
import com.gsatechworld.spexkart.beans.login.Login;
import com.gsatechworld.spexkart.beans.login.LoginInputData;
import com.gsatechworld.spexkart.beans.newproducts.NewProductList;
import com.gsatechworld.spexkart.beans.orderhistory.OrderHistory;
import com.gsatechworld.spexkart.beans.orderhistory.OrderHistoryInputData;
import com.gsatechworld.spexkart.beans.productbycategory.ProductByCat;
import com.gsatechworld.spexkart.beans.productbycategory.ProductByCategoryInputData;
import com.gsatechworld.spexkart.beans.productbygender.ProductByGender;
import com.gsatechworld.spexkart.beans.productbygender.ProductByGenderInputData;
import com.gsatechworld.spexkart.beans.productbyspecialcat.SpecialProduct;
import com.gsatechworld.spexkart.beans.productbyspecialcat.SpecialProductInputData;
import com.gsatechworld.spexkart.beans.productbysubcat.ProductBYSubcat;
import com.gsatechworld.spexkart.beans.productbysubcat.ProductBySubInputData;
import com.gsatechworld.spexkart.beans.productdetails.ProductDetails;
import com.gsatechworld.spexkart.beans.productdetails.ProductDetailsInputData;
import com.gsatechworld.spexkart.beans.removeall.RemoveAll;
import com.gsatechworld.spexkart.beans.removeall.RemoveAllInputData;
import com.gsatechworld.spexkart.beans.removecoupon.RemoveCoupon;
import com.gsatechworld.spexkart.beans.removecoupon.RemoveCouponInputData;
import com.gsatechworld.spexkart.beans.removeitem.RemoveCartItemInputData;
import com.gsatechworld.spexkart.beans.removeitem.RemoveItemFromCart;
import com.gsatechworld.spexkart.beans.removewishlist.RemoveFromWishlist;
import com.gsatechworld.spexkart.beans.search.SearchInput;
import com.gsatechworld.spexkart.beans.search.SearchResults;
import com.gsatechworld.spexkart.beans.signup.SignUp;
import com.gsatechworld.spexkart.beans.signup.SignUpInputData;
import com.gsatechworld.spexkart.beans.similarproducts.SimilarProduct;
import com.gsatechworld.spexkart.beans.similarproducts.SimilarProductInputData;
import com.gsatechworld.spexkart.beans.specialcategory.SpecialCategory;
import com.gsatechworld.spexkart.beans.specialcategory.SpecialCategoryInputData;
import com.gsatechworld.spexkart.beans.subcategory.SubCategory;
import com.gsatechworld.spexkart.beans.subcategory.SubCategoryInputData;
import com.gsatechworld.spexkart.beans.updatecart.UpdateCart;
import com.gsatechworld.spexkart.beans.updatecart.UpdateCartInputData;
import com.gsatechworld.spexkart.beans.uploadprescription.PrescriptionInputData;
import com.gsatechworld.spexkart.beans.uploadprescription.UploadPrescription;
import com.gsatechworld.spexkart.beans.wishlist.Wishlist;
import com.gsatechworld.spexkart.beans.wishlist.WishlistInputData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GitApi {

    @POST("master-data/getFaceshapes")
    Call<FaceShapes>faceshapes(@Body InputData inputData);

    @POST("master-data/getAgegroups")
    Call<AgeGroup>agegroup(@Body AgeInputData inputData);

    @POST("users/login")
    Call<Login>login(@Body LoginInputData loginInputData);

    @POST("users/forgotPassword")
    Call<Forgetpassword>forgetpassword(@Body ForgetPasswordInputData forgetPasswordInputData);

    @POST("users/signup")
    Call<SignUp>signup(@Body SignUpInputData signUpInputData);

    @POST("master-data/socialiteLogin")
    Call<SocialLogin>sociallogin(@Body SocialInputData socialInputData);

    @POST("master-data/getCategory")
    Call<GlassCategory>category(@Body CategoryInputData categoryInputData);

    @POST("getBanners")
    Call<HomeBanner>banner(@Body CategoryInputData categoryInputData);

    @POST("master-data/getSubcategory")
    Call<SubCategory>subcategory(@Body SubCategoryInputData subCategoryInputData);

    @POST("products/lists")
    Call<NewProductList>newproductlist(@Body CategoryInputData categoryInputData);

    @POST("products/details")
    Call<ProductDetails>productdetails(@Body ProductDetailsInputData productDetailsInputData);

    @POST("products/similartypes")
    Call<SimilarProduct>similarproduct(@Body SimilarProductInputData similarProductInputData);

    @POST("products/lensetypes")
    Call<LensesType>lensestype(@Body LensesTypeInput lensesTypeInput);

    @POST("products/lenses")
    Call<Lenses>lenses(@Body LensesListInputData lensesListInputData);

    @POST("products/categorywise")
    Call<ProductByCat>productbycat(@Body ProductByCategoryInputData productByCategoryInputData);

    @POST("products/subcategorywise")
    Call<ProductBYSubcat>productbysubcat(@Body ProductBySubInputData productBySubInputData);

    @POST("users/getDetails")
    Call<GetUserProfile>getuserinfo(@Body UserProfileInputData userProfileInputData);

    @POST("users/editProfile")
    Call<EditProfile>editprofile(@Body EditProfileInputdata editProfileInputdata);

    @POST("products/genderwise")
    Call<ProductByGender>productbygender(@Body ProductByGenderInputData productByGenderInputData);

    @POST("wishlist-add")
    Call<AddtoWishlist>addtowishlist(@Body AddtoWishlistInputData addtoWishlistInputData);

    @POST("wishlist-remove")
    Call<RemoveFromWishlist>removefromwishlist(@Body AddtoWishlistInputData addtoWishlistInputData);

    @POST("products/search")
    Call<SearchResults>search(@Body SearchInput searchInput);


    @POST("wishlist")
    Call<Wishlist>wishlist(@Body WishlistInputData wishlistInputData);

    @POST("coupons-lists")
    Call<CouponList>couponlist(@Body CouponsInputData couponsInputData);

    @POST("coupons-add")
    Call<ApplyCoupon>applycoupon(@Body ApplyCouponInputData applyCouponInputData);

    @POST("coupons-remove")
    Call<RemoveCoupon>removecoupon(@Body RemoveCouponInputData removeCouponInputData);

    @POST("cart-add")
    Call<AddtoCart>addtocart(@Body AddtoCartInputData addtoCartInputData);

    @POST("cart-lists")
    Call<CartList>cartlist(@Body CartListInputData cartListInputData);

    @POST("cart-delete")
    Call<RemoveItemFromCart>removecartitem(@Body RemoveCartItemInputData removeCartItemInputData);

    @POST("cart-update")
    Call<UpdateCart>updatecart(@Body UpdateCartInputData updateCartInputData);

    @POST("cart-delete-all")
    Call<RemoveAll>removeall(@Body RemoveAllInputData removeAllInputData);

    @POST("address-add")
    Call<AddAddress>addaddress(@Body AddAddressInputData addAddressInputData);

    @POST("address-lists")
    Call<AddressList>addresslist(@Body AddressListInputData addressListInputData);

    @POST("address-update")
    Call<UpdateAddress>updateaddress(@Body UpdateAddressInputData updateAddressInputData);

    @POST("address-delete")
    Call<DeleteAddress>deleteaddress(@Body DeleteAddressInputData deleteAddressInputData);

    @POST("checkout")
    Call<CheckOut>checkout(@Body CheckoutInputData checkoutInputData);

    @POST("orderHistory")
    Call<OrderHistory>orderhistory(@Body OrderHistoryInputData orderHistoryInputData);

    @POST("cart-prescription")
    Call<UploadPrescription>uploadprescription(@Body PrescriptionInputData prescriptionInputData);

    @POST("book-appointment")
    Call<BookApointment>bookapointment(@Body AppointmentInputData appointmentInputData);

    @POST("master-data/getEyeglassCategory")
    Call<SpecialCategory>bookapointment(@Body SpecialCategoryInputData specialCategoryInputData);

    @POST("products/eyeglasscategory")
    Call<SpecialProduct>specialproduct(@Body SpecialProductInputData specialProductInputData);

    @POST("generateOrder")
    Call<GenerateOrder>generateorder(@Body GenerateOrderInputData generateOrderInputData);

}
