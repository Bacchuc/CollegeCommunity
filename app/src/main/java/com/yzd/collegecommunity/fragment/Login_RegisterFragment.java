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

import com.google.gson.Gson;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.activity.MainActivity;
import com.yzd.collegecommunity.constants.Constants;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.retrofit.Registerlmpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    private String TAG = "Login_RegisterFragment";

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

                Retrofit registerRetrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASEURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                Registerlmpl registerService = registerRetrofit.create(Registerlmpl.class);

                registerService.register(etUsername.getText().toString(),etEmail.getText().toString(),etPassword.getText().toString(),etCode.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<HttpWrapper<String>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG,e.getMessage());
                                Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(HttpWrapper<String> loginService) {

                                if (new Gson().toJson(loginService.getCode()).equals("200")){

                                    if(new Gson().toJson(loginService.getInfo()).equals("\"success\"")){

                                        Intent intent=new Intent(getActivity(), MainActivity.class);
                                        startActivity(intent);

                                    }else if(new Gson().toJson(loginService.getInfo()).equals("\"email\"")){

                                        Toast.makeText(getActivity(), "The mailbox is already registered.", Toast.LENGTH_SHORT).show();

                                    }else if(new Gson().toJson(loginService.getInfo()).equals("\"username\"")){

                                        Toast.makeText(getActivity(), "The username is already registered.", Toast.LENGTH_SHORT).show();

                                    }else if(new Gson().toJson(loginService.getInfo()).equals("\"verCode\"")){

                                        Toast.makeText(getActivity(), "verification code error.", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                break;
            case tv_sendCode:
                Retrofit sendCodeRetrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASEURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                Registerlmpl sendCodeService = sendCodeRetrofit.create(Registerlmpl.class);

                sendCodeService.sendCode(etEmail.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<HttpWrapper<String>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG,e.getMessage());
                                Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(HttpWrapper<String> loginService) {

                                if (new Gson().toJson(loginService.getCode()).equals("200")){

                                    if(new Gson().toJson(loginService.getInfo()).equals("\"success\"")){

                                        Toast.makeText(getActivity(), "The verification code has been sent to yout mailbox,please verify.", Toast.LENGTH_SHORT).show();

                                    }else{
                                        Toast.makeText(getActivity(), "verification code failed to send.", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                break;
            default:
                break;
        }
    }
}
