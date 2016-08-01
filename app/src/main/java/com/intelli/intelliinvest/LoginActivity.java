package com.intelli.intelliinvest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.intelli.utils.CommonActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import com.intelli.utils.ConnectionHelper;
import com.intelli.utils.Constants;
import com.intelli.utils.ApiConstants;
import com.intelli.utils.Logger;
import com.intelli.utils.Utility;

public class LoginActivity extends CommonActivity {

    EditText username, password;
    Button btLogin;
    TextView sign_up, forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        username = (EditText) findViewById(R.id.edtUsername);
        password = (EditText) findViewById(R.id.edtPassword);
        sign_up = (TextView) findViewById(R.id.txtSignUp);
        sign_up.setOnClickListener(this);
        btLogin = (Button) findViewById(R.id.btnLogin);
        btLogin.setOnClickListener(this);
        forgot_password = (TextView) findViewById(R.id.txtForgotPwd);
        forgot_password.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "Resend activation code?", Snackbar.LENGTH_LONG)
                        .setAction("Send", sendOnClickListener);

                // Changing message text color
                snackbar.setActionTextColor(getResources().getColor(R.color.theme_light));

//                Changing action button text color
//                View sbView = snackbar.getView();
//                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//                textView.setTextColor(getResources().getColor(R.color.theme_light));
                snackbar.show();
            }
        });
    }

    View.OnClickListener sendOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

//            JSONObject reqObj= new JSONObject();
//            try {
//                reqObj.put(ApiConstants.LOGIN_USERNAME, username.getText().toString());
//            }catch (JSONException e) {
//                Logger.logE(Constants.ACTIVITY_LOGIN, "Resend activation object error: "+e);
//            }
//
//            ConnectionHelper helper = new ConnectionHelper(Constants.API_RESEND_ACTIVATION_CODE, LoginActivity.this, reqObj);
//            helper.execute(Constants.URL_RESEND_ACTIVATION_CODE);

            Snackbar.make(view, "Activation code sent to registered mail id", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    };

    public void setBackApiResponse(String requestName, Object object) {
        if (requestName == Constants.API_LOGIN) {
            try {
                JSONObject jObj = (JSONObject) object;
                if (jObj.getString(Constants.API_STATUS).equalsIgnoreCase(Constants.API_STATUS_SUCCESS)) {

                    SharedPreferences.Editor editPref = getSPreferences();
                    editPref.putString(ApiConstants.LOGIN_STATUS, "true");

                    if(jObj.has(ApiConstants.USER_OBJECT_MAIL))
                        editPref.putString(ApiConstants.USER_OBJECT_MAIL, jObj.getString(ApiConstants.USER_OBJECT_MAIL));
                    if(jObj.has(ApiConstants.USER_OBJECT_PHONE))
                        editPref.putString(ApiConstants.USER_OBJECT_PHONE, jObj.getString(ApiConstants.USER_OBJECT_PHONE));
                    if(jObj.has(ApiConstants.USER_OBJECT_USER_ID))
                        editPref.putString(ApiConstants.USER_OBJECT_USER_ID, jObj.getString(ApiConstants.USER_OBJECT_USER_ID));
                    if(jObj.has(ApiConstants.USER_OBJECT_USER_TYPE))
                        editPref.putString(ApiConstants.USER_OBJECT_USER_TYPE, jObj.getString(ApiConstants.USER_OBJECT_USER_TYPE));

                    editPref.putBoolean(ApiConstants.IS_NEW_USER, false);

                    closePreferences();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    createToast(jObj.getString(Constants.API_MESSAGE));
                }
            } catch (Exception e) {
                Logger.logE(Constants.ACTIVITY_LOGIN, "error while processing login response");
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v == sign_up) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
//            finish();
        } else if (v == btLogin) {
//            SharedPreferences.Editor editPref = getSPreferences();
//            editPref.putBoolean(ApiConstants.IS_NEW_USER, false);
//            closePreferences();
//            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//            startActivity(intent);
//            finish();
            if (username.getText().toString().equalsIgnoreCase("")) {
                createToast("Enter username");
            } else if (password.getText().toString().equalsIgnoreCase("")) {
                createToast("Enter password");
            } else {
                JSONObject reqObj= new JSONObject();
                try {
                    reqObj.put(ApiConstants.LOGIN_USERNAME, username.getText().toString());
                    reqObj.put(ApiConstants.LOGIN_PASSWORD, password.getText().toString());
                }catch (JSONException e) {
                    Logger.logE(Constants.ACTIVITY_LOGIN, "Login object error: "+e);
                }

                ConnectionHelper helper = new ConnectionHelper(Constants.API_LOGIN, LoginActivity.this, reqObj);
                helper.execute(Constants.URL_LOGIN);
            }
        } else if (v == forgot_password) {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        }
    }

}
