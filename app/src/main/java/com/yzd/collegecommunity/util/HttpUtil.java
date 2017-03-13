package com.yzd.collegecommunity.util;


import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

import android.os.Handler;

import android.os.Looper;

import android.os.Message;

import android.util.Log;



import org.json.JSONException;

import org.json.JSONObject;



import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.net.HttpURLConnection;

import java.net.URL;

import java.net.URLEncoder;

import java.security.SecureRandom;

import java.security.cert.X509Certificate;

import java.util.HashMap;

import java.util.List;

import java.util.Map;



import javax.net.ssl.HostnameVerifier;

import javax.net.ssl.HttpsURLConnection;

import javax.net.ssl.SSLContext;

import javax.net.ssl.SSLSession;

import javax.net.ssl.TrustManager;

import javax.net.ssl.X509TrustManager;

/**
 * Created by Laiyin on 2017/3/8.
 */

public class HttpUtil<T> {

        private static final String TAG = "HttpUtil";

        private static HttpUtil mHttpUtil = null;

        private Class<T> type;



        private String url;

        private String method;  //post,get

        private Map<String, String> requestProperty;

        private Map<String, String> params;

        private String Charset;



        public static HttpUtil getInstence(Builder builder) {

            if (mHttpUtil == null) {

                synchronized (Builder.class) {

                    if (mHttpUtil == null) {

                        mHttpUtil = new HttpUtil();

                    }

                }

            }

            mHttpUtil.url = builder.url;

            mHttpUtil.method = builder.method;

            mHttpUtil.type = builder.type;

            mHttpUtil.requestProperty = builder.requestProperty;

            mHttpUtil.params = builder.params;

            mHttpUtil.Charset = builder.Charset;

            return mHttpUtil;

        }



        public static class Builder<T> {



            private String url;

            private String method;

            private Map<String, String> requestProperty;

            private Map<String, String> params;

            private String Charset;

            private Class<T> type;



            public Builder() {

                method = "GET";

                requestProperty = new HashMap<String, String>();

            }



            public Builder url(String url) {

                if (url == null) throw new IllegalArgumentException("url == null");

                this.url = url;

                return this;

            }



            public Builder get() {

                method = "GET";

                return this;

            }



            public Builder get(Map<String, String> params) {

                method = "GET";

                this.params = params;

                return this;

            }



            public Builder post() {

                method = "POST";

                return this;

            }



            public Builder post(Map<String, String> params) {

                method = "POST";

                this.params = params;

                return this;

            }



            public Builder type(Class<T> type) {

                this.type = type;

                return this;

            }



            public Builder setRequestProperty(String key, String value) {

                if (key == null) throw new IllegalArgumentException("key == null");

                if (value == null) throw new IllegalArgumentException("value == null");

                this.requestProperty.put(key, value);

                return this;

            }



            public Builder addRequsetProperty(String key, String value) {

                if (key == null) throw new IllegalArgumentException("key == null");

                if (value == null) throw new IllegalArgumentException("value == null");

                value = requestProperty.get(key) + value;

                this.requestProperty.put(key, value);

                return this;

            }



            public Builder addParams(String key, String value) {

                if (key == null) throw new IllegalArgumentException("key == null");

                if (value == null) throw new IllegalArgumentException("value == null");

                if (this.params == null) this.params = new HashMap<String, String>();

                this.params.put(key, value);

                return this;

            }



            public Builder setEncoding(String Charset) {

                if (Charset == null) throw new IllegalArgumentException("Charset == null");

                this.Charset = Charset;

                return this;

            }



            public HttpUtil builder() {

                return HttpUtil.getInstence(this);

            }

        }



        private class UIhandler extends Handler {



            HttpCallback callback;



            private UIhandler(Looper looper, HttpCallback callback) {

                super(looper);

                this.callback = callback;

            }



            @Override

            public void handleMessage(Message msg) {

                switch (msg.what) {

                    case 0x00:

                        callback.onFailure((IOException) msg.obj);

                        break;

                    case 0x01:

                        callback.onResponse(msg.obj);

                        break;

                }

            }

        }



        public void execute() {

            execute(null);

        }



        public void execute(final HttpCallback<T> callback) {

            execute(Looper.getMainLooper(), callback);

        }



        public void execute(final Looper looper, final HttpCallback<T> callback) {

            new Thread() {

                @Override

                public void run() {

                    Message msg = Message.obtain();

                    if (type == null || type.equals(String.class)) {          //返回字符串

                        try {

                            String result = getString();

                            msg.what = 0x01;

                            msg.obj = result;

                        } catch (IOException e) {

                            msg.what = 0x00;

                            msg.obj = e;

                            e.printStackTrace();

                        }

                    } else if (type.equals(JSONObject.class)) {

                        try {

                            JSONObject result = getJsonObject();

                            msg.what = 0x01;

                            msg.obj = result;

                        } catch (IOException e) {

                            msg.what = 0x00;

                            msg.obj = e;

                            e.printStackTrace();

                        } catch (JSONException e) {

                            e.printStackTrace();

                        }

                    } else if (type.equals(Bitmap.class)) {

                        try {

                            Bitmap result = getBitmap();

                            msg.what = 0x01;

                            msg.obj = result;

                        } catch (IOException e) {

                            msg.what = 0x00;

                            msg.obj = e;

                            e.printStackTrace();

                        }

                    }

                    if (callback != null) {

                        UIhandler handler = new UIhandler(looper, callback);

                        handler.sendMessage(msg);

                    }

                }

            }.start();

        }



        private String getString() throws IOException {

            String result = "";

            InputStream is = getInputS();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;

            while ((line = br.readLine()) != null) {

                result += line;

            }

            br.close();

            is.close();

            Log.i(TAG, "http返回---"+result);

            return result;

        }



        private JSONObject getJsonObject() throws IOException, JSONException {

            String result = getString();

            return new JSONObject(result);

        }



        private Bitmap getBitmap() throws IOException {

            InputStream is = getInputS();

            return BitmapFactory.decodeStream(is);

        }



        private InputStream getInputS() throws IOException {

            if ("GET" == method) {

                if (params != null) {

                    url += "?";

                    for (Map.Entry<String, String> entry : params.entrySet()) {

                        url += entry.getKey() + "=" + URLEncoder.encode(entry.getValue(),"UTF-8") + "&";

                    }

                    url = url.substring(0, url.length() - 1);

                }

            }

            URL Url = new URL(url);

            Log.i(TAG, "url---" + Url);

            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();

            conn.setRequestProperty("Accept-Charset", "UTF-8");

            if (requestProperty != null) {                       //请求属性

                for (Map.Entry<String, String> entry : requestProperty.entrySet()) {

                    conn.setRequestProperty(entry.getKey(), entry.getValue());

                }

            }



            //https安全证书处理

            if (Url.getProtocol().toLowerCase().equals("https")) {

                // Create a trust manager that does not validate certificate chains

                TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

                    public X509Certificate[] getAcceptedIssuers() {

                        return null;

                    }



                    public void checkClientTrusted(X509Certificate[] certs, String authType) {

                    }



                    public void checkServerTrusted(X509Certificate[] certs, String authType) {

                    }

                }};

                // Install the all-trusting trust manager  // 注意这部分一定要

                try {

                    SSLContext sc = SSLContext.getInstance("TLS");

                    sc.init(null, trustAllCerts, new SecureRandom());

//                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());



                    HttpsURLConnection https = (HttpsURLConnection) Url.openConnection();

                    https.setHostnameVerifier(DO_NOT_VERIFY);

                    https.setSSLSocketFactory(sc.getSocketFactory());

                    conn = https;

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }



            conn.setRequestMethod(method);

            conn.setUseCaches(false);

            Map<String, List<String>> RequestProperties = conn.getRequestProperties();

            for (String key : RequestProperties.keySet()) {

                Log.i(TAG, "RequestProperties---" + key + "---" + RequestProperties.get(key));

            }

            //POST发送请求参数

            if ("POST" == method) {

                if (params != null) {

                    JSONObject jb = new JSONObject();

                    String requsetStr = "";

                    for (Map.Entry<String, String> entry : params.entrySet()) {

                        requsetStr += entry.getKey() + "=" + entry.getValue() + "&";

                    }

                    requsetStr = requsetStr.substring(0, requsetStr.length() - 1);

                    Log.i(TAG, "http发送---" + requsetStr);

                    conn.setDoInput(true);

                    conn.setDoOutput(true);

                    PrintWriter pw = new PrintWriter(conn.getOutputStream());

                    pw.write(requsetStr);

                    pw.flush();

                }

            }

            return conn.getInputStream();

        }



        private final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

            @Override

            public boolean verify(String s, SSLSession sslSession) {

                return true;

            }

        };



        public interface HttpCallback<T> {



            void onResponse(T response);



            void onFailure(IOException e);

        }

}
