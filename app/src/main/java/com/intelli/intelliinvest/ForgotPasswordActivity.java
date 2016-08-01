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

import com.intelli.utils.ApiConstants;
import com.intelli.utils.CommonActivity;
import com.intelli.utils.ConnectionHelper;
import com.intelli.utils.Constants;
import com.intelli.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPasswordActivity extends CommonActivity {

    EditText emailId, username;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        context = this;

        emailId = (EditText) findViewById(R.id.edtEmail);
        username = (EditText) findViewById(R.id.edtUsername);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(this);
    }

    public void setBackApiResponse(String requestName, Object object) {
        if (requestName == Constants.API_FORGOT_PASSWORD) {
            try {
                JSONObject jObj = (JSONObject) object;
                if (jObj.getString(Constants.API_STATUS).equalsIgnoreCase(Constants.API_STATUS_SUCCESS)) {

                    createToast(jObj.getString(Constants.API_MESSAGE));
                } else {
                    createToast(jObj.getString(Constants.API_ERROR));
                }
            } catch (Exception e) {
                Logger.logE(Constants.ACTIVITY_FORGOT_PASSWORD, "error while processing response");
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnReset) {
            if (emailId.getText().toString().equalsIgnoreCase("")) {
                createToast("Enter email address");
            } else if (username.getText().toString().equalsIgnoreCase("")) {
                createToast("Enter username");
            } else {
                JSONObject reqObj= new JSONObject();
                try {
//                    reqObj.put(ApiConstants.USER_OBJECT_USER_ID, username.getText().toString());
                    reqObj.put(ApiConstants.USER_OBJECT_MAIL, emailId.getText().toString());
                }catch (JSONException e) {
                    Logger.logE(Constants.ACTIVITY_FORGOT_PASSWORD, "Forgot password object error: "+e);
                }

                ConnectionHelper helper = new ConnectionHelper(Constants.API_FORGOT_PASSWORD, ForgotPasswordActivity.this, reqObj);
                helper.execute(Constants.URL_FORGOT_PASSWORD);
            }
        }
    }

}
