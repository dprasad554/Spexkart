package com.gsatechworld.spexkart.utils;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.gsatechworld.spexkart.beans.Social.SocialInputData;
import com.gsatechworld.spexkart.beans.addaddress.AddAddressInputData;
import com.gsatechworld.spexkart.beans.addressdelete.DeleteAddressInputData;
import com.gsatechworld.spexkart.beans.addresslist.AddressListInputData;
import com.gsatechworld.spexkart.beans.addressupdate.UpdateAddressInputData;
import com.gsatechworld.spexkart.beans.addtocart.AddtoCartInputData;
import com.gsatechworld.spexkart.beans.addwishlist.AddtoWishlistInputData;
import com.gsatechworld.spexkart.beans.agegroup.AgeInputData;
import com.gsatechworld.spexkart.beans.applycoupon.ApplyCouponInputData;
import com.gsatechworld.spexkart.beans.bookapointment.AppointmentInputData;
import com.gsatechworld.spexkart.beans.cartlist.CartListInputData;
import com.gsatechworld.spexkart.beans.category.CategoryInputData;
import com.gsatechworld.spexkart.beans.checkout.CheckoutInputData;
import com.gsatechworld.spexkart.beans.coupons.CouponsInputData;
import com.gsatechworld.spexkart.beans.editprofile.EditProfileInputdata;
import com.gsatechworld.spexkart.beans.faceshapes.InputData;
import com.gsatechworld.spexkart.beans.forgetpassword.ForgetPasswordInputData;
import com.gsatechworld.spexkart.beans.generateorder.GenerateOrder;
import com.gsatechworld.spexkart.beans.generateorder.GenerateOrderInputData;
import com.gsatechworld.spexkart.beans.getuserprofile.UserProfileInputData;
import com.gsatechworld.spexkart.beans.lensescategory.LensesTypeInput;
import com.gsatechworld.spexkart.beans.lensestype.LensesListInputData;
import com.gsatechworld.spexkart.beans.login.LoginInputData;
import com.gsatechworld.spexkart.beans.orderhistory.OrderHistoryInputData;
import com.gsatechworld.spexkart.beans.productbycategory.ProductByCategoryInputData;
import com.gsatechworld.spexkart.beans.productbyspecialcat.SpecialProductInputData;
import com.gsatechworld.spexkart.beans.productbysubcat.ProductBySubInputData;
import com.gsatechworld.spexkart.beans.productdetails.ProductDetailsInputData;
import com.gsatechworld.spexkart.beans.removeall.RemoveAllInputData;
import com.gsatechworld.spexkart.beans.removecoupon.RemoveCouponInputData;
import com.gsatechworld.spexkart.beans.removeitem.RemoveCartItemInputData;
import com.gsatechworld.spexkart.beans.search.SearchInput;
import com.gsatechworld.spexkart.beans.signup.SignUpInputData;
import com.gsatechworld.spexkart.beans.similarproducts.SimilarProductInputData;
import com.gsatechworld.spexkart.beans.specialcategory.SpecialCategoryInputData;
import com.gsatechworld.spexkart.beans.subcategory.SubCategoryInputData;
import com.gsatechworld.spexkart.beans.updatecart.UpdateCartInputData;
import com.gsatechworld.spexkart.beans.uploadprescription.PrescriptionInputData;
import com.gsatechworld.spexkart.beans.wishlist.WishlistInputData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WebServices<T> {
    public static String SK_Services = "http://rnspexkart.com/api/";
    private static OkHttpClient.Builder builder;
    ApiType apiTypeVariable;
    Call<T> call = null;
    Context context;
    OnResponseListner<T> onResponseListner;
    android.app.ProgressDialog pdLoading;
    private T t;

    public WebServices(OnResponseListner<T> onResponseListner) {
        if (onResponseListner instanceof Activity) {
            this.context = (Context) onResponseListner;
        } else if (onResponseListner instanceof IntentService) {
            this.context = (Context) onResponseListner;
        } else if (onResponseListner instanceof android.app.DialogFragment) {
            android.app.DialogFragment dialogFragment = (android.app.DialogFragment) onResponseListner;
            this.context = dialogFragment.getActivity();
        } else {
            Fragment fragment = (Fragment) onResponseListner;
            this.context = fragment.getActivity();
        }

        this.onResponseListner = onResponseListner;
        builder = getHttpClient();
    }

    public WebServices(Context context, OnResponseListner<T> onResponseListner) {
        this.onResponseListner = onResponseListner;
        this.context = context;
        builder = getHttpClient();
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }


    public OkHttpClient.Builder getHttpClient() {
        if (builder == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.readTimeout(60, TimeUnit.SECONDS);
            client.connectTimeout(60, TimeUnit.SECONDS);
            client.addInterceptor(loggingInterceptor);
            return client;
        }
        return builder;
    }

    //FaceShapes
    public void FaceShapes(String api, final ApiType apiTypes, InputData inputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.faceshapes(inputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });

    }

    //Agegroups
    public void Agegroups(String api, final ApiType apiTypes, AgeInputData inputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.agegroup(inputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //LoginInputData
    public void LoginInputData(String api, final ApiType apiTypes, LoginInputData loginInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.login(loginInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }
        });

    }

    //ForgetPassword
    public void ForgetPassword(String api, final ApiType apiTypes, ForgetPasswordInputData forgetPasswordInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.forgetpassword(forgetPasswordInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //SignUp
    public void SignUp(String api, final ApiType apiTypes, SignUpInputData signUpInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.signup(signUpInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //FacebookLogin
    public void FacebookLogin(String api, final ApiType apiTypes, SocialInputData socialInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.sociallogin(socialInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Category
    public void Category(String api, final ApiType apiTypes, CategoryInputData categoryInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.category(categoryInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //SubCategory
    public void SubCategory(String api, final ApiType apiTypes, SubCategoryInputData subCategoryInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.subcategory(subCategoryInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Banner
    public void Banner(String api, final ApiType apiTypes, CategoryInputData categoryInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.banner(categoryInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Category
    public void NewProductList(String api, final ApiType apiTypes, CategoryInputData categoryInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.newproductlist(categoryInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //ProductDetails
    public void ProductDetails(String api, final ApiType apiTypes, ProductDetailsInputData productDetailsInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.productdetails(productDetailsInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Simpilarproduct
    public void Simpilarproduct(String api, final ApiType apiTypes, SimilarProductInputData similarProductInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.similarproduct(similarProductInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //LensesType
    public void LensesType(String api, final ApiType apiTypes, LensesTypeInput lensesTypeInput) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.lensestype(lensesTypeInput);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Lenses
    public void Lenses(String api, final ApiType apiTypes, LensesListInputData lensesListInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.lenses(lensesListInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //ProductByCat
    public void ProductByCat(String api, final ApiType apiTypes, ProductByCategoryInputData productByCategoryInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.productbycat(productByCategoryInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //ProductBySubCat
    public void ProductBySubCat(String api, final ApiType apiTypes, ProductBySubInputData productBySubInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.productbysubcat(productBySubInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //GetUserProfile
    public void GetUserProfile(String api, final ApiType apiTypes, UserProfileInputData userProfileInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getuserinfo(userProfileInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //EditProfile
    public void EditProfile(String api, final ApiType apiTypes, EditProfileInputdata editProfileInputdata) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.editprofile(editProfileInputdata);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    public void ProductBySpecialCategory(String api, final ApiType apiTypes, SpecialProductInputData specialProductInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.specialproduct(specialProductInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //AddtoWishlist
    public void AddtoWishlist(String api, final ApiType apiTypes, AddtoWishlistInputData addtoWishlistInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.addtowishlist(addtoWishlistInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //RemoveFromWishlist
    public void RemoveFromWishlist(String api, final ApiType apiTypes, AddtoWishlistInputData addtoWishlistInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.removefromwishlist(addtoWishlistInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Search
    public void Search(String api, final ApiType apiTypes, SearchInput searchInput) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.search(searchInput);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Wishlist
    public void Wishlist(String api, final ApiType apiTypes, WishlistInputData wishlistInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.wishlist(wishlistInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //CouponList
    public void CouponList(String api, final ApiType apiTypes, CouponsInputData couponsInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.couponlist(couponsInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //ApplyCoupon
    public void ApplyCoupon(String api, final ApiType apiTypes, ApplyCouponInputData applyCouponInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.applycoupon(applyCouponInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    String[] separated = response.errorBody().source().toString().split(":");
                    String error = separated[2];
                    String message = error.substring(0, error.length() - 2);
                    SnackBar.makeText(context, message, SnackBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Remove Apply Coupon
    public void RemoveCoupon(String api, final ApiType apiTypes, RemoveCouponInputData removeCouponInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.removecoupon(removeCouponInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Remove Apply Coupon
    public void AddtoCart(String api, final ApiType apiTypes, AddtoCartInputData addtoCartInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.addtocart(addtoCartInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Remove Apply Coupon
    public void Cartlist(String api, final ApiType apiTypes, CartListInputData cartListInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.cartlist(cartListInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //RemoveItem
    public void RemoveItem(String api, final ApiType apiTypes, RemoveCartItemInputData removeCartItemInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.removecartitem(removeCartItemInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //updatecart
    public void UpdateCart(String api, final ApiType apiTypes, UpdateCartInputData updateCartInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.updatecart(updateCartInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //RemoveAll
    public void RemoveAll(String api, final ApiType apiTypes, RemoveAllInputData removeAllInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.removeall(removeAllInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //AddAddress
    public void AddAddress(String api, final ApiType apiTypes, AddAddressInputData addAddressInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.addaddress(addAddressInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //Addresslist
    public void Addresslist(String api, final ApiType apiTypes, AddressListInputData addressListInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.addresslist(addressListInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //UpdateAddress
    public void UpdateAddress(String api, final ApiType apiTypes, UpdateAddressInputData updateAddressInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.updateaddress(updateAddressInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //DeleteAddress
    public void DeleteAddress(String api, final ApiType apiTypes, DeleteAddressInputData deleteAddressInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.deleteaddress(deleteAddressInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //CheckOut
    public void CheckOut(String api, final ApiType apiTypes, CheckoutInputData checkoutInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.checkout(checkoutInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //OrderHistory
    public void OrderHistory(String api, final ApiType apiTypes, OrderHistoryInputData orderHistoryInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.orderhistory(orderHistoryInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }
    //UploadPrescription
    public void UploadPrescription(String api, final ApiType apiTypes, PrescriptionInputData prescriptionInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.uploadprescription(prescriptionInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }
    //BookAppointment
    public void BookAppointment(String api, final ApiType apiTypes, AppointmentInputData appointmentInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.bookapointment(appointmentInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }
    //specialcategory
    public void SpecialCategory(String api, final ApiType apiTypes, SpecialCategoryInputData specialCategoryInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.bookapointment(specialCategoryInputData);
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    //generateorder
    public void GenerateOrder(String api, final ApiType apiTypes, GenerateOrderInputData generateOrderInputData) {
        ProgressDialog progressDialog = new ProgressDialog();
        try {
            this.pdLoading = progressDialog.getInstance(this.context);
            this.pdLoading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            if (this.pdLoading.isShowing()) {
                this.pdLoading.cancel();
            }
            this.pdLoading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.apiTypeVariable = apiTypes;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.generateorder(generateOrderInputData);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                try {
                    t = (T) response.body();
                    Log.e(apiTypes.toString(), t + ".");
                    if (pdLoading.isShowing())
                        pdLoading.cancel();
                    onResponseListner.onResponse(t, apiTypeVariable, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(apiTypes.toString(), t.getMessage() + ".");
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }

    public enum ApiType {
        faceshapes,
        agegroup,
        login,
        forgetpassword,
        signup,
        social,
        category,
        banner,
        subcategory,
        newproductlist,
        productdetails,
        similarproduct,
        lensestype,
        lenses,
        productbycat,
        productbysubcat,
        getuserprofile,
        edituserprofile,
        productbygender,
        addtowishlist,
        removefromwishlist,
        searchresluts,
        wishlist,
        couponlist,
        applycoupon,
        removecoupon,
        addtocart,
        cartlist,
        removeitem,
        updatecart,
        removeall,
        addaddress,
        addresslist,
        updateaddress,
        deleteaddress,
        checkout,
        orderhistory,
        uploadprescription,
        bookapointment,
        specialcategory,
        productbyspecialcategory,
        generateorder,
        checkoutonline

    }

}
