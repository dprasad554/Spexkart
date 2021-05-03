package com.gsatechworld.spexkart.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gsatechworld.spexkart.R;
import com.gsatechworld.spexkart.beans.editprofile.EditProfile;
import com.gsatechworld.spexkart.beans.editprofile.EditProfileInputdata;
import com.gsatechworld.spexkart.beans.getuserprofile.GetUserProfile;
import com.gsatechworld.spexkart.beans.getuserprofile.UserProfileInputData;
import com.gsatechworld.spexkart.utils.ConnectionDetector;
import com.gsatechworld.spexkart.utils.OnResponseListner;
import com.gsatechworld.spexkart.utils.Prefs;
import com.gsatechworld.spexkart.utils.SnackBar;
import com.gsatechworld.spexkart.utils.WebServices;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

public class EditProfileActivity extends AppCompatActivity implements OnResponseListner {

    ConnectionDetector mDetector;
    TextView tv_email;
    ImageView iv_back;
    String name,mobile,email,address,image,url;
    EditText et_address,et_mobile,et_name;
    CircleImageView iv_profile;
    EditProfileInputdata editProfileInputdata;
    EditProfile editProfile;
    Bitmap selectedImage;
    String profile_image;
    Button ll_selecttype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setValues();

        name = getIntent().getStringExtra("profile_name");
        mobile = getIntent().getStringExtra("profile_mobile");
        email = getIntent().getStringExtra("profile_email");
        address = getIntent().getStringExtra("profile_address");
        image = getIntent().getStringExtra("profile_image");


        tv_email = findViewById(R.id.tv_email);
        et_address = findViewById(R.id.et_address);
        et_mobile = findViewById(R.id.et_mobile);
        et_name = findViewById(R.id.et_name);
        iv_profile = findViewById(R.id.iv_profile);
        ll_selecttype = findViewById(R.id.ll_selecttype);
        iv_back = findViewById(R.id.iv_back);

        tv_email.setText(email);
        et_address.setText(address);
        et_mobile.setText(mobile);
        et_name.setText(name);
        url = image;
        if (!url.equals("")) {
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.profile_background)//if failed
                    .into(iv_profile);
        }
        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this, ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                startActivityForResult(intent, 1213);
            }
        });
        ll_selecttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallService();
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
/*
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
    byte[] byteArray = byteArrayOutputStream .toByteArray();
            iv_profile.setImageBitmap(selectedImage);
    profile_image = Base64.encodeToString(byteArray, Base64.DEFAULT);*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);



            ExifInterface ei = null;
            try {
                ei = new ExifInterface(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);

            Bitmap rotatedBitmap = null;
            switch(orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(selectedImage, 90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(selectedImage, 180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(selectedImage, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotatedBitmap = selectedImage;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            iv_profile.setImageBitmap(rotatedBitmap);
            profile_image = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }


    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }

    private void CallService() {
        if (this.mDetector.isConnectingToInternet()) {
            editProfileInputdata = new EditProfileInputdata();
            editProfileInputdata.setApiToken("SPECXf84e9f66e5294fbc9b7cbfc7e464cb07KART");
            editProfileInputdata.setUserId(Integer.valueOf(Prefs.getUserId(this)));
            editProfileInputdata.setName(et_name.getText().toString());
            editProfileInputdata.setEmail(tv_email.getText().toString());
            editProfileInputdata.setMobileNumber(et_mobile.getText().toString());
            editProfileInputdata.setAddress(et_address.getText().toString());
            editProfileInputdata.setProfileImage(profile_image);
            new WebServices(this).EditProfile(WebServices.SK_Services,
                    WebServices.ApiType.edituserprofile, editProfileInputdata);
        } else {
            SnackBar.makeText(this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
        return;
    }


    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.edituserprofile) {
            if (!isSucces) {
                SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
            } else {
                editProfile = (EditProfile) response;

                if (editProfile.getSuccess() == null) {
                    SnackBar.makeText(this, getResources().getString(R.string.error_message), SnackBar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}