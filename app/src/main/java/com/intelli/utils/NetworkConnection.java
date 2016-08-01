package com.intelli.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by Pooja on 17-02-2016.
 */
public class NetworkConnection {

    public String performJson(String url, List<NameValuePair> nameValuePair) {
        String doc = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            HttpResponse response;

            post.setEntity(new UrlEncodedFormEntity(nameValuePair));
            response = client.execute(post);
            InputStream data = response.getEntity().getContent();
            if (data != null) {
                StringBuilder sb = new StringBuilder();
                int b;
                while ((b = data.read()) != -1) {
                    sb.append((char) b);
                }
                data.close();
                return sb.toString();
            } else return "";

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return doc;
    }

}
