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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.yzd.collegecommunity.R.id.bt_register;
import static com.yzd.collegecommunity.R.id.et_code;
import static com.yzd.collegecommunity.R.id.et_email;
import static com.yzd.collegecommunity.R.id.et_password;
import static com.yzd.collegecommunity.R.id.et_username;
import static com.yzd.collegecommunity.R.id.tv_sendCode;

/**
 * Created by Laiyin on 2017/3/4.
 */

public class Login_RegisterFragment extends Fragment implements View.OnClickListener {

    @BindView(et_username)
    EditText etUsername;
    @BindView(et_email)
    EditText etEmail;
    @BindView(et_password)
    EditText etPassword;
    @BindView(tv_sendCode)
    TextView tvSendCode;
    @BindView(et_code)
    EditText etCode;
    @BindView(bt_register)
    Button btRegister;

    private String resCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.tv_sendCode, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                OkHttpUtils
                        .post()
                        .url(Constants.BASEURL + "textPost.action")
                        .addParams("et_email", etEmail.getText().toString())
                        .addParams("et_code", etCode.getText().toString())
                        .addParams("username", etUsername.getText().toString())
                        .addParams("password", etPassword.getText().toString())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("Login_LoginFragment", "onError:" + e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {

                                final String res = response;

                                if (res == "OK") {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    getActivity().startActivity(intent);
                                } else if (res == "et_username") {
                                    Toast.makeText(getActivity(), "用户名已注册", Toast.LENGTH_LONG).show();
                                    etUsername.setText("");
                                } else if (res == "et_email") {
                                    Toast.makeText(getActivity(), "邮箱已注册", Toast.LENGTH_LONG).show();
                                    etEmail.setText("");
                                } else if (res == "et_code") {
                                    Toast.makeText(getActivity(), "验证码错误", Toast.LENGTH_LONG).show();
                                    etCode.setText("");
                                }
                            }
                        });
                break;
            case tv_sendCode:

                OkHttpUtils
                        .post()
                        .url(Constants.BASEURL + "textPost.action")
                        .addParams("et_email", etEmail.getText().toString())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("Login_RegisterFragment", "onError:" + e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {

                                resCode = response;
                                if (resCode == "OK") {
                                    Toast.makeText(getActivity(), "已将验证码发送至您的邮箱，请验证！", Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                break;
            default:
                break;
        }
    }
}
