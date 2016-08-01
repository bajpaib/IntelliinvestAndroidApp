package com.intelli.utils;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pooja on 17-02-2016.
 */

public class ConnectionHelper extends AsyncTask<String, Integer, Long> {

    public JSONObject object;
    private String method = "POST";
    String response = "";
    private ProgressDialog dialog = null;
    private CommonActivity activity_main;
    private String loading_message;
    private String request_api = "No API selected";
    JSONObject reqObject;

    public ConnectionHelper(String api, CommonActivity activity, JSONObject reqObj) {
        this.request_api = api;
        activity_main = activity;
        loading_message = Constants.NETWORK_LOADING_MSG;
        dialog = new ProgressDialog(activity_main);
        this.reqObject = reqObj;
    }

    public ConnectionHelper(String api, CommonActivity activity, JSONObject reqObj, String methodType) {
        this.request_api = api;
        activity_main = activity;
        loading_message = Constants.NETWORK_LOADING_MSG;
        dialog = new ProgressDialog(activity_main);
        this.reqObject = reqObj;
        method = methodType;
    }

    protected void onPreExecute() {
        if (dialog != null && !loading_message.equalsIgnoreCase("")) {
            dialog.setMessage(loading_message);
            dialog.show();
        }
    }

    @Override
    protected Long doInBackground(String... values) {
        // TODO Auto-generated method stub
//        if (request_number == Constants.checkLastModified) {
        String url = values[0];
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        Log.d("requestURL", url);

        try {

            HttpParams httpParameters = new BasicHttpParams();
            int timeoutConnection = 20000;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            int timeoutSocket = 20000;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

            response = null;

            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;
            Logger.logD("Connection", "request type - " + method);
            if (method == Constants.API_TYPE_POST) {
                HttpPost httpPost = new HttpPost(url);
                if (nameValuePairs != null) {

                    Logger.logD("Connection", "request: " + reqObject.toString());

                    StringEntity entity = new StringEntity(reqObject.toString(), HTTP.UTF_8);
                    entity.setContentType("application/json");
                    httpPost.setEntity(entity);
                }

                httpResponse = httpClient.execute(httpPost);

            } else if (method == Constants.API_TYPE_GET) {
                if (nameValuePairs != null) {
                    String paramString = URLEncodedUtils
                            .format(nameValuePairs, "utf-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);
                httpResponse = httpClient.execute(httpGet);

            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);

            Logger.logD("Connection", "response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            object = new JSONObject(response);
        } catch (Exception e) {

        }

        return null;
    }

    public void onPostExecute(Long result) {

        activity_main.setBackApiResponse(request_api, object);

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
