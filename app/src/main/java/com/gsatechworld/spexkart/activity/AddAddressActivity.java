package com.gsatechworld.spexkart.activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.gson.Gson;
import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.beans.addaddress.AddAddress;
import com.gsatechworld.spexkart.beans.addaddress.AddAddressInputData;
import com.gsatechworld.spexkart.beans.addresslist.AddressListInputData;
import com.gsatechworld.spexkart.beans.addresslist.AddressList_;
import com.gsatechworld.spexkart.beans.addressupdate.UpdateAddress;
import com.gsatechworld.spexkart.beans.addressupdate.UpdateAddressInputData;
import com.gsatechworld.spexkart.beans.lensestype.Lenses;
import com.gsatechworld.spexkart.utils.ConnectionDetector;
import com.gsatechworld.spexkart.utils.OnResponseListner;
import com.gsatechworld.spexkart.utils.Prefs;
import com.gsatechworld.spexkart.utils.SnackBar;
import com.gsatechworld.spexkart.utils.WebServices;

public class AddAddressActivity extends AppCompatActivity implements OnResponseListner {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    ImageView iv_back;
    LinearLayout ll_addaddress;
    String currentAdd;
    EditText et_address, et_locality, et_pincode, land_mark, et_city, et_state, ed_country, ed_contactname, con_number;
    Button btnNext;
    ConnectionDetector mDetector;
    AddAddressInputData addAddressInputData;
    AddAddress addAddress;
    String locality, pincode, landmark, city, state, country, contact_name, mobile;
    private FusedLocationProviderClient fusedLocationClient;
    private TextView currentAddTv;
    private Location currentLocation;
    private LocationCallback locationCallback;
    AddressList_ addressList_ = new AddressList_();
    String address_details;
    UpdateAddressInputData updateAddressInputData;
    UpdateAddress updateAddress;
    String cart_id,sub_total,discount,grand_total,gst_amount,gst_per;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);
        setValues();

        cart_id = getIntent().getStringExtra("cart_id");
        sub_total = getIntent().getStringExtra("sub_total");
        discount = getIntent().getStringExtra("discount");
        grand_total = getIntent().getStringExtra("grand_total");
        gst_amount = getIntent().getStringExtra("gst_amount");
        gst_per = getIntent().getStringExtra("gst_per");

       /* addressResultReceiver = new LocationAddressResultReceiver(new Handler());
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                currentLocation = locationResult.getLocations().get(0);
                getAddress();
            }
        };
        startLocationUpdates();*/

        et_address = findViewById(R.id.et_address);
        et_locality = findViewById(R.id.et_locality);
        et_pincode = findViewById(R.id.pincode);
        land_mark = findViewById(R.id.land_mark);
        et_city = findViewById(R.id.et_city);
        et_state = findViewById(R.id.et_state);
        ed_country = findViewById(R.id.ed_country);
        ed_contactname = findViewById(R.id.ed_contactname);
        con_number = findViewById(R.id.con_number);
        btnNext = findViewById(R.id.btnNext);
        iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAddressActivity.this, CheckOutActivity.class);
                intent.putExtra("cart_id", cart_id);
                intent.putExtra("sub_total",sub_total);
                intent.putExtra("discount",discount);
                intent.putExtra("grand_total",grand_total);
                intent.putExtra("gst_amount",gst_amount);
                intent.putExtra("gst_per",gst_per);
                startActivity(intent);
                finish();
            }
        });


        address_details = getIntent().getStringExtra("address_details");
        addressList_ = new Gson().fromJson(address_details, AddressList_.class);
        if(addressList_ != null){
            String area = addressList_.getLocality();
            String[] separated = area.split(",");
            String address = separated[0];
            String loaclity = separated[1];
            et_address.setText(address);
            et_locality.setText(loaclity);
            et_pincode.setText(addressList_.getPincode());
            land_mark.setText(addressList_.getLandmark());
            et_city.setText(addressList_.getCity());
            et_state.setText(addressList_.getState());
            ed_country.setText(addressList_.getCountry());
            ed_contactname.setText(addressList_.getContactName());
            con_number.setText(addressList_.getMobileNumber());
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addressList_ != null){
                    CallUpdateService();
                }else {
                    CallService();
                }

            }
        });


    }


    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            if (!et_address.getText().toString().isEmpty() || !et_address.getText().toString().equals("")) {
                if (!et_locality.getText().toString().isEmpty() || !et_locality.getText().toString().equals("")) {
                    if (!et_pincode.getText().toString().isEmpty() || !et_pincode.getText().toString().equals("")) {
                        if (!land_mark.getText().toString().isEmpty() || !land_mark.getText().toString().equals("")) {
                            if (!et_city.getText().toString().isEmpty() || !et_city.getText().toString().equals("")) {
                                if (!et_state.getText().toString().isEmpty() || !et_state.getText().toString().equals("")) {
                                    if (!ed_country.getText().toString().isEmpty() || !ed_country.getText().toString().equals("")) {
                                        if (!ed_contactname.getText().toString().isEmpty() || !ed_contactname.getText().toString().equals("")) {
                                            if (!con_number.getText().toString().isEmpty() || !con_number.getText().toString().equals("")) {

                                                addAddressInputData = new AddAddressInputData();
                                                addAddressInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
                                                addAddressInputData.setUserId(Prefs.getUserId(this));
                                                addAddressInputData.setLocality(et_address.getText().toString() +","+ et_locality.getText().toString());
                                                addAddressInputData.setPincode(et_pincode.getText().toString());
                                                addAddressInputData.setLandmark(land_mark.getText().toString());
                                                addAddressInputData.setCity(et_city.getText().toString());
                                                addAddressInputData.setState(et_state.getText().toString());
                                                addAddressInputData.setCountry(ed_country.getText().toString());
                                                addAddressInputData.setContactName(ed_contactname.getText().toString());
                                                addAddressInputData.setMobileNumber(con_number.getText().toString());

                                                new WebServices(this).AddAddress(WebServices.SK_Services,
                                                        WebServices.ApiType.addaddress, addAddressInputData);

                                            } else {
                                                SnackBar.makeText(this, "Enter Your Number", SnackBar.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            SnackBar.makeText(this, "Enter Contact Name", SnackBar.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        SnackBar.makeText(this, "Enter Country Name", SnackBar.LENGTH_SHORT).show();
                                    }
                                } else {
                                    SnackBar.makeText(this, "Enter State", SnackBar.LENGTH_SHORT).show();
                                }
                            } else {
                                SnackBar.makeText(this, "Enter  City", SnackBar.LENGTH_SHORT).show();
                            }

                        } else {
                            SnackBar.makeText(this, "Enter  LandMarker", SnackBar.LENGTH_SHORT).show();
                        }

                    } else {
                        SnackBar.makeText(this, "Enter  Pincode", SnackBar.LENGTH_SHORT).show();
                    }

                } else {
                    SnackBar.makeText(this, "Enter  Locality", SnackBar.LENGTH_SHORT).show();
                }

            } else {
                SnackBar.makeText(this, "Enter  Area and Building Details", SnackBar.LENGTH_SHORT).show();
            }

        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }
    private void CallUpdateService() {
        if (this.mDetector.isConnectingToInternet()) {
            updateAddressInputData = new UpdateAddressInputData();
            updateAddressInputData.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            updateAddressInputData.setUserId(Prefs.getUserId(this));
            updateAddressInputData.setAddressId(addressList_.getId().toString());
            updateAddressInputData.setLocality(et_address.getText().toString() +","+ et_locality.getText().toString());
            updateAddressInputData.setPincode(et_pincode.getText().toString());
            updateAddressInputData.setLandmark(land_mark.getText().toString());
            updateAddressInputData.setCity(et_city.getText().toString());
            updateAddressInputData.setState(et_state.getText().toString());
            updateAddressInputData.setCountry(ed_country.getText().toString());
            updateAddressInputData.setContactName(ed_contactname.getText().toString());
            updateAddressInputData.setMobileNumber(con_number.getText().toString());
            new WebServices(this).UpdateAddress(WebServices.SK_Services,
                    WebServices.ApiType.updateaddress, updateAddressInputData);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.addaddress) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                addAddress = (AddAddress) response;

                if (!addAddress.getSuccess()) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                }
                if (addAddress == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(AddAddressActivity.this, CheckOutActivity.class);
                    intent.putExtra("cart_id", cart_id);
                    intent.putExtra("sub_total",sub_total);
                    intent.putExtra("discount",discount);
                    intent.putExtra("grand_total",grand_total);
                    intent.putExtra("gst_amount",gst_amount);
                    intent.putExtra("gst_per",gst_per);
                    startActivity(intent);
                    finish();
                }
            }
        }if (apiType == WebServices.ApiType.updateaddress) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                updateAddress = (UpdateAddress) response;

                if (!updateAddress.getSuccess()) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                }
                if (updateAddress == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(AddAddressActivity.this, CheckOutActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddAddressActivity.this, AddressActivity.class);
        startActivity(intent);
        finish();
    }

}