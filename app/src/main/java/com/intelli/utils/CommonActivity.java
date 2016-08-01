package com.intelli.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.intelli.intelliinvest.R;

/**
 * Created by Pooja on 17-02-2016.
 */
public class CommonActivity extends ActionBarActivity implements View.OnClickListener {

    /*------------------------------- start --------------------------------*/
    /******************************** end *********************************/

    /*------------------------------- Global variables --------------------------------*/
    public SharedPreferences pref = null;
    public SharedPreferences.Editor editPref = null;

    public Context context = this;
    /******************************** Global Variables *********************************/

    /*------------------------------- Default Functions --------------------------------*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            SharedPreferences pref = getSharedPreferences(Constants.INTELLIINVEST_SHARED_PREF, MODE_PRIVATE);
//            SharedPreferences.Editor editorPref = pref.edit();
//            editorPref.clear();
//            editorPref.commit();
////            Intent intent = new Intent(this, Login.class);
////            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////            startActivity(intent);
////            finish();
//        }

        return super.onOptionsItemSelected(item);
    }
    /******************************** Default Functions *********************************/

    /*------------------------------- Global Functions --------------------------------*/
    public void setBackApiResponse(String request) {

    }

    public void setBackApiResponse(String request, Object object) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {

    }

    public SharedPreferences.Editor getSPreferences()
    {
        pref = getSharedPreferences(Constants.INTELLIINVEST_SHARED_PREF, MODE_PRIVATE);
        editPref = pref.edit();

        return editPref;
    }

    public void closePreferences()
    {
        if(editPref != null) {
            editPref.commit();
        }
    }

    public void createToast(String msg)
    {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public final boolean isInternetOn() {

        // check network connection
        ConnectivityManager conn = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = conn.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
//            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
//                // connected to wifi
//                Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
//            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
//                // connected to the mobile provider's data plan
//                Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
//            }

            return true;
        } else {
            // not connected to the internet
            Toast.makeText(this, "Please check your Internet Connectivity... ", Toast.LENGTH_LONG).show();
            return false;
        }
    }
    /******************************** Global Functions *********************************/
}
