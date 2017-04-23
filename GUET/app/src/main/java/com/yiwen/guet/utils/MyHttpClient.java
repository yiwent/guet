package com.yiwen.guet.utils;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;

/**
 * User: Yiwen(https://github.com/yiwent)
 * Date: 2017-04-21
 * Time: 21:31
 * FIXME
 */
public class MyHttpClient extends AsyncHttpClient {
    @Override
    public void setEnableRedirects(final boolean enableRedirects) {
        ((DefaultHttpClient) getHttpClient()).
                setRedirectHandler(new DefaultRedirectHandler() {
                    @Override
                    public boolean isRedirectRequested(HttpResponse response, HttpContext context) {
                        int statusCode = response.getStatusLine().getStatusCode();
                        Log.i("setEnableRedirects", "code:" + statusCode);
                        if (statusCode == 301 || statusCode == 302) {
                            Log.i("setEnableRedirects", "enableRedirects: true");
                            return enableRedirects;
                        }
                        return false;
                    }
                });
    }

}
