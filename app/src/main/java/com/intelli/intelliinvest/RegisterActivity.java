package com.intelli.intelliinvest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.intelli.utils.ApiConstants;
import com.intelli.utils.CommonActivity;
import com.intelli.utils.ConnectionHelper;
import com.intelli.utils.Constants;
import com.intelli.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends CommonActivity {

    EditText phone, emailId, username, password, confirmPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = this;

        phone = (EditText) findViewById(R.id.edtPhone);
        emailId = (EditText) findViewById(R.id.edtEmail);
        username = (EditText) findViewById(R.id.edtUsername);
        password = (EditText) findViewById(R.id.edtPassword);
        confirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }

    public void setBackApiResponse(String requestName, Object object) {
        if (requestName == Constants.API_REGISTER) {
            try {
                JSONObject jObj = (JSONObject) object;
                if (jObj.getString(Constants.API_STATUS).equalsIgnoreCase(Constants.API_STATUS_SUCCESS)) {

                    SharedPreferences.Editor editPref = getSPreferences();
                    editPref.putBoolean(ApiConstants.LOGIN_STATUS, true);

                    if(jObj.has(ApiConstants.USER_OBJECT_MAIL))
                        editPref.putString(ApiConstants.USER_OBJECT_MAIL, jObj.getString(ApiConstants.USER_OBJECT_MAIL));
                    if(jObj.has(ApiConstants.USER_OBJECT_PHONE))
                        editPref.putString(ApiConstants.USER_OBJECT_PHONE, jObj.getString(ApiConstants.USER_OBJECT_PHONE));
                    if(jObj.has(ApiConstants.USER_OBJECT_USER_ID))
                        editPref.putString(ApiConstants.USER_OBJECT_USER_ID, jObj.getString(ApiConstants.USER_OBJECT_USER_ID));
                    if(jObj.has(ApiConstants.USER_OBJECT_USER_TYPE))
                        editPref.putString(ApiConstants.USER_OBJECT_USER_TYPE, jObj.getString(ApiConstants.USER_OBJECT_USER_TYPE));
                    editPref.putBoolean(ApiConstants.IS_NEW_USER, true);
                    closePreferences();
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    createToast(jObj.getString(Constants.API_ERROR));
                }
            } catch (Exception e) {
                Logger.logE(Constants.ACTIVITY_REGISTER, "error while processing register response");
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btnRegister) {
//            SharedPreferences.Editor editPref = getSPreferences();
//            editPref.putBoolean(ApiConstants.IS_NEW_USER, true);
//            closePreferences();
//            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
//            startActivity(intent);
//            finish();
            if (emailId.getText().toString().equalsIgnoreCase("")) {
                createToast("Enter email address");
            } else if (phone.getText().toString().equalsIgnoreCase("")) {
                createToast("Enter phone number");
            } else if (username.getText().toString().equalsIgnoreCase("")) {
                createToast("Enter username");
            } else if (password.getText().toString().equalsIgnoreCase("")) {
                createToast("Enter password");
            } else if (confirmPassword.getText().toString().equalsIgnoreCase("")) {
                createToast("Re-enter password");
            } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                createToast("Passwords do not match");
            } else {
                JSONObject reqObj= new JSONObject();
                try {
                    reqObj.put(ApiConstants.REGISTER_USERNAME, username.getText().toString());
                    reqObj.put(ApiConstants.REGISTER_PASSWORD, password.getText().toString());
                    reqObj.put(ApiConstants.REGISTER_PHONE, phone.getText().toString());
                    reqObj.put(ApiConstants.REGISTER_EMAIL, emailId.getText().toString());
                    reqObj.put(ApiConstants.REGISTER_SEND_NOTIFICATION, "true");
//                    reqObj.put(ApiConstants.REGISTER_USER_ID, "yogeshgulati");
//                    reqObj.put(ApiConstants.REGISTER_ACTIVATION_CODE, "ACT41008");
                }catch (JSONException e) {
                    Logger.logE(Constants.ACTIVITY_REGISTER, "Register object error: "+e);
                }

                ConnectionHelper helper = new ConnectionHelper(Constants.API_REGISTER, RegisterActivity.this, reqObj);
                helper.execute(Constants.URL_REGISTER);
            }
        }
    }

}
