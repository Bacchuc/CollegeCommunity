package com.yzd.collegecommunity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.activity.ThirdActivity;
import com.yzd.collegecommunity.callback.OkHttpCallback;
import com.yzd.collegecommunity.constants.Constants;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.modeal.Test;
import com.yzd.collegecommunity.util.BlurBehind;
import com.yzd.collegecommunity.util.OnBlurCompleteListener;
import com.zhy.http.okhttp.OkHttpUtils;


/**
 * Created by Laiyin on 2017/3/4.
 */

public class Login_LoginFragment extends Fragment implements View.OnClickListener{

    private Button bt_login;
    private EditText et_username;
    private EditText et_password;
//    private TextView tv_third;

    private String TAG="Login_LoginFragment";

    private String res;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login_fragment_login,container,false);

        initView(view);

        initListener();

        return view;
    }


    private void initView(View view){
        bt_login=(Button)view.findViewById(R.id.bt_login);
        et_username=(EditText)view.findViewById(R.id.et_username);
        et_password=(EditText)view.findViewById(R.id.et_password);
//        tv_third=(TextView)view.findViewById(R.id.tv_third);

    }

    private void initListener(){
        bt_login.setOnClickListener(this);
//        tv_third.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login:
//                OkHttpUtils
//                        .post()
//                        .url(Constants.BASEURL+"test.action")
//                        .addParams("username",et_username.getText().toString())
//                        .addParams("password",et_password.getText().toString())
//                        .build()
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onError(Call call, Exception e, int id) {
//                                Log.e("Login_LoginFragment","onError:"+e.getMessage());
//                            }
//
//                            @Override
//                            public void onResponse(String response, int id) {
//
////                                final String res=response;
//
//                                System.out.print(response+"------------------------------------------------------");
//
//                                if(res.equals("OK")){
//                                    Intent intent = new Intent(getActivity(), MainActivity.class);
//                                    getActivity().startActivity(intent);
//                                }
//                                else {
//                                    Toast.makeText(getActivity(),"用户名或者密码错误",Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });

//                OkHttpUtils
//                        .post()
//                        .url(Constants.BASEURL+"Test.action")
//                        .addParams("username",et_username.getText().toString())
//                        .build()
//                        .execute(new OkHttpCallback<Test>() {
//                            @Override
//                            protected void onFaild(HttpWrapper<Test> response, int id) {
//
////                                et_password.setText("onError:"+response.getInfo());
//
//                                System.out.println("onError:"+response.getInfo()+"------------------------------------");
//                            }
//
//                            @Override
//                            protected void onSuccess(HttpWrapper<Test> response, int id) {
////                                et_password.setText(new Gson().toJson(response));
//
//                                System.out.println("onSuccess:"+new Gson().toJson(response)+"------------------------------------");
//
//                            }
//                        });

//                OkHttpUtils
//                        .post()
//                        .url(Constants.BASEURL+"textPost")
//                        .addParams("username",et_username.getText().toString())
//                        .addParams("password",et_password.getText().toString())
//                        .build()
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onError(Call call, Exception e, int id) {
//
//                            }
//
//                            @Override
//                            public void onResponse(String response, int id) {
//
//                                final String res=response;
//
//                                System.out.println(res);
//
//                                getActivity().runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        et_username.setText(res);
//                                    }
//                                });
//                            }
//                        });

                OkHttpUtils
                        .post()
                        .url(Constants.BASEURL+"textPost")
                        .addParams("username",et_username.getText().toString())
                        .addParams("password",et_password.getText().toString())
                        .build()
                        .execute(new OkHttpCallback<Test>() {
                            @Override
                            protected void onFaild(HttpWrapper<Test> response, int id) {

//                                et_username.setText("onError:"+response.getData());
//                                System.out.println("onError:"+response.getData()+"------------------------------------");
//
//                                Log.e(TAG,"onError:"+response.getData()+"------------------------------------");
                            }

                            @Override
                            protected void onSuccess(HttpWrapper<Test> response, int id) {

                                et_username.setText(new Gson().toJson(response));
                                System.out.println("onSuccess:"+new Gson().toJson(response)+"------------------------------------");

                                Log.e(TAG,"onSuccess:"+new Gson().toJson(response)+"------------------------------------");

                            }

                        });

//                //描述下载
//                String username="ok";
//
//                OkHttpUtils
//                        .post()
//                        .url(Constants.BASEURL+"toTaskList.action")
//                        .addParams("username",username)
//                        .build()
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onError(Call call, Exception e, int id) {
//
//                                Log.e("Login_LoginFragment","onError:"+e.getMessage());
//                            }
//
//                            @Override
//                            public void onResponse(String response, int id) {
//
//                                final String res=response;
//
////                        List<MainTaskListInfo> m = response.fromJson(res, new TypeToken<List<MainTaskListInfo>>(){}.getType());
////                        for(int i =0; i < m.size() ; i++)
////                        {
////                            MainTaskListInfo mt = m.get(i);
////                            System.out.println(mt.toString());
////                        }
//
//                                System.out.println(res);
//                            }
//                        });
                break;
//            case R.id.tv_third:
//                startThirdActivity();
//                break;
            default:
                break;
        }
    }

    public void startThirdActivity(){
        BlurBehind.getInstance().execute(getActivity(), new OnBlurCompleteListener() {
            @Override
            public void onBlurComplete() {
                Intent intent1 = new Intent(getActivity(), ThirdActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent1);
            }
        });
    }
}
