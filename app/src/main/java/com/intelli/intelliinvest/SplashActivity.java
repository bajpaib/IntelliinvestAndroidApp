package com.intelli.intelliinvest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.intelli.utils.ApiConstants;
import com.intelli.utils.CommonActivity;
import com.intelli.utils.Constants;

/**
 * Created by Pooja on 20-04-2016.
 */
public class SplashActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    SharedPreferences sP = getSharedPreferences(Constants.INTELLIINVEST_SHARED_PREF, MODE_PRIVATE);
                    if(sP.contains(ApiConstants.LOGIN_STATUS) && sP.getString(ApiConstants.LOGIN_STATUS, "").equalsIgnoreCase("true")) {
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}
