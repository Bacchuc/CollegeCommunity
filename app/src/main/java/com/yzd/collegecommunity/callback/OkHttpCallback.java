package com.yzd.collegecommunity.callback;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Laiyin on 2017/3/10.
 */

public abstract class OkHttpCallback<T> extends Callback<HttpWrapper<T>> {

    public String TAG="OkHttpCallback";

    @Override
    public HttpWrapper<T> parseNetworkResponse(Response response, int id) throws Exception {

//        Log.e(TAG,response.body().string()+"-------------------------------------------------------------------------------------");

        return new Gson().fromJson(response.body().string(),HttpWrapper.class);
    }

    @Override
    public void onError(Call call, Exception e, int id) {

        /**
         * 异常
         */
//        Toast.makeText(AppCenter.mContext,"网络错误",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onResponse(HttpWrapper<T> response, int id) {

        switch (response.getCode()){
            case 200:
                onSuccess(response,id);
                break;
            case 201:
                /**
                 * 201失败情况下
                 */
//                Toast.makeText(AppCenter.mContext,response.getInfo(),Toast.LENGTH_LONG).show();
                onFaild(response,id);
                break;
            default:
                break;
        }

    }

    protected abstract void onFaild(HttpWrapper<T> response, int id);

    protected abstract void onSuccess(HttpWrapper<T> response, int id) ;

}
