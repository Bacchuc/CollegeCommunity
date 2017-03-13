package com.yzd.collegecommunity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.activity.MainActivity;
import com.yzd.collegecommunity.constants.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Laiyin on 2017/3/4.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener{

    private EditText et_username;
    private EditText et_email;
    private EditText et_password;
    private EditText et_code;
    private TextView tv_sendCode;
    private Button bt_register;

    private String resCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_layout_register,container,false);

        initView(view);

        initListener();

        return view;
    }

    private void initView(View view){
        bt_register=(Button)view.findViewById(R.id.bt_register);
        et_username=(EditText)view.findViewById(R.id.et_username);
        et_password=(EditText)view.findViewById(R.id.et_password);
        et_email=(EditText)view.findViewById(R.id.et_email);
        et_code=(EditText)view.findViewById(R.id.et_code);
        tv_sendCode=(TextView)view.findViewById(R.id.tv_sendCode);

    }

    private void initListener(){
        bt_register.setOnClickListener(this);
        tv_sendCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login:
                OkHttpUtils
                        .post()
                        .url(Constants.BASEURL+"textPost.action")
                        .addParams("et_email", et_email.getText().toString())
                        .addParams("et_code", et_code.getText().toString())
                        .addParams("username", et_username.getText().toString())
                        .addParams("password",et_password.getText().toString())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("LoginFragment","onError:"+e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {

                                final String res=response;

                                if(res=="OK"){
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    getActivity().startActivity(intent);
                                }
                                else if(res=="et_username"){
                                    Toast.makeText(getActivity(),"用户名已注册",Toast.LENGTH_LONG).show();
                                    et_username.setText("");
                                }
                                else if(res=="et_email"){
                                    Toast.makeText(getActivity(),"邮箱已注册",Toast.LENGTH_LONG).show();
                                    et_email.setText("");
                                }
                                else if(res=="et_code"){
                                    Toast.makeText(getActivity(),"验证码错误",Toast.LENGTH_LONG).show();
                                    et_code.setText("");
                                }
                            }
                        });
                break;
            case R.id.tv_sendCode:

                OkHttpUtils
                        .post()
                        .url(Constants.BASEURL+"textPost.action")
                        .addParams("et_email", et_email.getText().toString())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("RegisterFragment","onError:"+e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {

                                resCode=response;
                                if (resCode=="OK"){
                                    Toast.makeText(getActivity(),"已将验证码发送至您的邮箱，请验证！",Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                break;
            default:
                break;
        }
    }
}
