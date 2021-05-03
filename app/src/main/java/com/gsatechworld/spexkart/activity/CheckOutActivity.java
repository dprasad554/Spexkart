package com.gsatechworld.spexkart.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.adapter.AddressAdapter;
import com.gsatechworld.spexkart.beans.addressdelete.DeleteAddress;
import com.gsatechworld.spexkart.beans.addressdelete.DeleteAddressInputData;
import com.gsatechworld.spexkart.beans.addresslist.AddressList;
import com.gsatechworld.spexkart.beans.addresslist.AddressListInputData;
import com.gsatechworld.spexkart.beans.addresslist.AddressList_;
import com.gsatechworld.spexkart.beans.checkout.CheckOut;
import com.gsatechworld.spexkart.beans.checkout.CheckoutInputData;
import com.gsatechworld.spexkart.beans.generateorder.GenerateOrder;
import com.gsatechworld.spexkart.beans.generateorder.GenerateOrderInputData;
import com.gsatechworld.spexkart.utils.ConnectionDetector;
import com.gsatechworld.spexkart.utils.OnResponseListner;
import com.gsatechworld.spexkart.utils.Prefs;
import com.gsatechworld.spexkart.utils.SnackBar;
import com.gsatechworld.spexkart.utils.WebServices;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class CheckOutActivity extends AppCompatActivity implements OnResponseListner, AddressAdapter.UserAddress, PaymentResultListener {
    ImageView iv_back;
    Button btnNext, btnCOD, btnOnline;
    String cart_id, sub_total, discount, grand_total, gst_amount, gst_per, address_details;
    TextView tv_name, tv_address, tv_total, tv_discount, tv_grandtotal, tv_mob, tv_gst;
    LinearLayout ll_add_button, ll_add_content;
    AddressList_ addressList_ = new AddressList_();
    RecyclerView address_recyclerView;
    ConnectionDetector mDetector;
    AddressListInputData addressListInputData;
    AddressList addressList;
    DeleteAddressInputData deleteAddressInputData;
    DeleteAddress deleteAddress;
    LinearLayout ll_addaddress, ll_chekout;
    Dialog viewpopup;
    String Selected_id;
    CheckoutInputData checkoutInputData;
    GenerateOrderInputData generateOrderInputData;
    GenerateOrder generateOrder;
    CheckOut checkout;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_final);
        setValues();
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckOutActivity.this, CartListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        address_recyclerView = findViewById(R.id.address_recyclerView);
        cart_id = getIntent().getStringExtra("cart_id");
        sub_total = getIntent().getStringExtra("sub_total");
        discount = getIntent().getStringExtra("discount");
        grand_total = getIntent().getStringExtra("grand_total");
        gst_amount = getIntent().getStringExtra("gst_amount");
        gst_per = getIntent().getStringExtra("gst_per");

        tv_total = findViewById(R.id.tv_total);
        tv_discount = findViewById(R.id.tv_discount);
        tv_gst = findViewById(R.id.tv_gst);
        tv_grandtotal = findViewById(R.id.tv_grandtotal);
        ll_addaddress = findViewById(R.id.ll_addaddress);
        tv_name = findViewById(R.id.tv_name);
        tv_address = findViewById(R.id.tv_address);
        tv_mob = findViewById(R.id.tv_mob);
        ll_chekout = findViewById(R.id.ll_chekout);


        tv_total.setText("₹ " + sub_total);
        tv_grandtotal.setText("₹ " + grand_total);
        tv_gst.setText("(" + gst_per + ")" + "₹ " + gst_amount);
        tv_discount.setText("₹" + discount);
        CallService();

        ll_addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckOutActivity.this, AddAddressActivity.class);
                intent.putExtra("cart_id", cart_id);
                intent.putExtra("sub_total", sub_total);
                intent.putExtra("discount", discount);
                intent.putExtra("grand_total", grand_total);
                intent.putExtra("gst_amount", gst_amount);
                intent.putExtra("gst_per", gst_per);
                startActivity(intent);
                finish();
            }
        });
        ll_chekout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Selected_id != null){
                    InitializeSelectLensespopup();
                    initializeSelectLensesPopup(grand_total);
                }else {
                    SnackBar.makeText(CheckOutActivity.this, "Please Select Address", SnackBar.LENGTH_SHORT).show();
                }

            }
        });
       /* address_details = getIntent().getStringExtra("address_details");
        addressList_ = new Gson().fromJson(address_details, AddressList_.class);
        if(addressList_ != null){
            ll_add_content.setVisibility(View.VISIBLE);
            tv_name.setText(addressList_.getContactName());
            final String address = addressList_.getLocality()+","+
                    addressList_.getLandmark()+","+addressList_.getCity()+
                    ","+addressList_.getState()+","+addressList_.getPincode()+","+
                    addressList_.getCountry();
            tv_address.setText(address);
            tv_mob.setText(addressList_.getMobileNumber());
        }*/

    }

    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            addressListInputData = new AddressListInputData();
            addressListInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            addressListInputData.setUserId(Prefs.getUserId(this));
            new WebServices(this).Addresslist(WebServices.SK_Services,
                    WebServices.ApiType.addresslist, addressListInputData);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    private void DeleteAddress(String id) {
        if (this.mDetector.isConnectingToInternet()) {
            deleteAddressInputData = new DeleteAddressInputData();
            deleteAddressInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            deleteAddressInputData.setUserId(Prefs.getUserId(this));
            deleteAddressInputData.setAddressId(id);
            new WebServices(this).DeleteAddress(WebServices.SK_Services,
                    WebServices.ApiType.deleteaddress, deleteAddressInputData);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    private void CallCheckoutCODService() {
        if (this.mDetector.isConnectingToInternet()) {
            checkoutInputData = new CheckoutInputData();
            checkoutInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            checkoutInputData.setUserId(Prefs.getUserId(this));
            checkoutInputData.setCartId(cart_id);
            checkoutInputData.setAddressId(Selected_id);
            checkoutInputData.setPaymentId("");
            checkoutInputData.setPaymentMode("COD");
            new WebServices(this).CheckOut(WebServices.SK_Services,
                    WebServices.ApiType.checkout, checkoutInputData);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }
    private void CallCheckoutOnlineService(String order_id) {
        if (this.mDetector.isConnectingToInternet()) {
            checkoutInputData = new CheckoutInputData();
            checkoutInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            checkoutInputData.setUserId(Prefs.getUserId(this));
            checkoutInputData.setCartId(cart_id);
            checkoutInputData.setAddressId(Selected_id);
            checkoutInputData.setPaymentId(order_id);
            checkoutInputData.setPaymentMode("ONLINE");
            new WebServices(this).CheckOut(WebServices.SK_Services,
                    WebServices.ApiType.checkoutonline, checkoutInputData);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    private void GenerateOrder(String amount) {
        if (this.mDetector.isConnectingToInternet()) {
            generateOrderInputData = new GenerateOrderInputData();
            generateOrderInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            generateOrderInputData.setAmount(amount);
            new WebServices(this).GenerateOrder(WebServices.SK_Services,
                    WebServices.ApiType.generateorder, generateOrderInputData);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    public void startPayment(String order_id,int amount) {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();
            options.put("name", getResources().getString(R.string.app_name));
            options.put("description", Prefs.getUserId(this));
            options.put("order_id", order_id);
            options.put("theme.color", "#13015E");
            options.put("currency", "INR");
            options.put("amount", amount);//pass amount in currency subunits
            options.put("prefill.email", " ");
            options.put("prefill.contact"," ");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }
    @Override
    public void onPaymentSuccess(String s) {
        try {
            Toast.makeText(this, "Payment Successful: " + s, Toast.LENGTH_SHORT).show();
            CallCheckoutOnlineService(generateOrder.getRazorpayOrderId());
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {


            Toast.makeText(this, "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.addresslist) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                addressList = (AddressList) response;

                if (!addressList.getSuccess()) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                }
                if (addressList == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                }
                if (addressList.getAddressList().size() == 0) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } else {
                    LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                    address_recyclerView.setLayoutManager(gridLayoutManager);
                    AddressAdapter addressAdapter = new AddressAdapter(this, addressList, this);
                    address_recyclerView.setAdapter(addressAdapter);
                }
            }
        }
        if (apiType == WebServices.ApiType.deleteaddress) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                deleteAddress = (DeleteAddress) response;

                if (!deleteAddress.getSuccess()) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                }
                if (deleteAddress == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } else {
                    SnackBar.makeText(this, deleteAddress.getMessage(), SnackBar.LENGTH_SHORT).show();
                    CallService();
                }
            }
        }if (apiType == WebServices.ApiType.checkout) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                checkout = (CheckOut) response;

                if (!checkout.getSuccess()) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                }
                if (checkout == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(CheckOutActivity.this, OrderSucessActivity.class);
                    intent.putExtra("order_id", checkout.getOrderId());
                    intent.putExtra("order_sl_num", checkout.getOrderSlno().toString());
                    startActivity(intent);
                    finish();
                }
            }
        }if (apiType == WebServices.ApiType.generateorder) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                generateOrder = (GenerateOrder) response;

                if (!generateOrder.getSuccess()) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                }
                if (generateOrder == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                }if (generateOrder.getRazorpayOrderId() == null) {
                    SnackBar.makeText(this, "PaymentID not generated ", SnackBar.LENGTH_SHORT).show();
                }if (generateOrder.getRazorpayOrderId().equals("")) {
                    SnackBar.makeText(this, "PaymentID not generated ", SnackBar.LENGTH_SHORT).show();
                }else {
                    int amount = Integer.parseInt(grand_total)*100;
                    startPayment(generateOrder.getRazorpayOrderId(),amount);
                }
            }
        }if (apiType == WebServices.ApiType.checkoutonline) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                checkout = (CheckOut) response;

                if (!checkout.getSuccess()) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                }
                if (checkout == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(CheckOutActivity.this, OrderSucessActivity.class);
                    intent.putExtra("order_id", checkout.getOrderId());
                    intent.putExtra("order_sl_num", checkout.getOrderSlno().toString());
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CheckOutActivity.this, CartListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void Addresslist(String address_id, String action, String position) {
        address_recyclerView.scrollToPosition(Integer.parseInt(position));
        if (action.equals("delete")) {
            DeleteAddress(address_id);
        } else {
            Selected_id = address_id;
            //SnackBar.makeText(this, "Address ID:::" + Selected_id, SnackBar.LENGTH_SHORT).show();
        }
    }

    private void InitializeSelectLensespopup() {
        viewpopup = new Dialog(this);
        viewpopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        viewpopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        viewpopup.setContentView(R.layout.popup_payment);
        viewpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewpopup.setCancelable(true);
        viewpopup.setCanceledOnTouchOutside(true);
    }

    private void initializeSelectLensesPopup(final String amount) {
        viewpopup.setContentView(R.layout.popup_payment);
        viewpopup.setCancelable(true);
        viewpopup.setCanceledOnTouchOutside(true);
        viewpopup.show();
        Button btnCOD = (Button) viewpopup.findViewById(R.id.btnCOD);
        btnCOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallCheckoutCODService();
            }
        });
        Button btnOnline = (Button)viewpopup.findViewById(R.id.btnOnline);
        btnOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenerateOrder(amount);

            }
        });

        int width = this.getResources().getDisplayMetrics().widthPixels - 100;
        int height = this.getResources().getDisplayMetrics().heightPixels - 250;
//        deletePopup.getWindow().setLayout(width, height);
        viewpopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        viewpopup.getWindow().setGravity(Gravity.BOTTOM);

        viewpopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewpopup.setCancelable(true);
        viewpopup.setCanceledOnTouchOutside(true);
        viewpopup.show();
    }



}