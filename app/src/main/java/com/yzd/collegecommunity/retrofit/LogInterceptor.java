package com.yzd.collegecommunity.retrofit;

import android.content.Intent;
import android.util.Log;

import com.yzd.collegecommunity.activity.LoginActivity;
import com.yzd.collegecommunity.util.ActivityCollectorUtil;
import com.yzd.collegecommunity.util.AppCenterUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


/**
 * Created by Laiyin on 2017/4/16.
 * <p>
 * 登陆拦截器，拦截所有请求，判断token是否过期
 */

public class LogInterceptor implements Interceptor {

    private static final String TAG = LogInterceptor.class.getSimpleName();
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();

        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();

        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                return response;
            }
        }

        if (!isPlaintext(buffer)) {
            return response;
        }

        if (contentLength != 0) {
            //获取到response的body的string字符串
            String result = buffer.clone().readString(charset);

            try {
                JSONObject jsonObject = new JSONObject(result);

                int code = jsonObject.getInt("code");
                String userId = jsonObject.getString("user_id");
                String sessionId = jsonObject.getString("session_id");

                //SPUtil.refresh(userId, sessionId);

                //code==300,既是token过期，发送广播重新登陆
                if (code == 300) {
                    Intent intent =new Intent(AppCenterUtil.getContextObject(), LoginActivity.class);
                    AppCenterUtil.getContextObject().startActivity(intent);
                    ActivityCollectorUtil.finishAll();
                }

            } catch (JSONException e) {
                Log.e(TAG, "JSONException: " + e.getMessage());
                e.printStackTrace();
            }

            Log.e(TAG, result);
        }

        return response;
    }

    /**
     * 判断是否是明文
     *
     * @param buffer Buffer
     * @return 结果
     * @throws EOFException
     */
    private static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false;
        }
    }
}
