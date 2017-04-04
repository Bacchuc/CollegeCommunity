package com.yzd.collegecommunity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.activity.MainActivity;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.retrofit.ProgressSubscriber;
import com.yzd.collegecommunity.retrofit.SubscriberOnNextListener;
import com.yzd.collegecommunity.util.RetrofitUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private SubscriberOnNextListener mListener;

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
            case R.id.bt_register:
                mListener = new SubscriberOnNextListener<HttpWrapper<String>>() {
                    @Override
                    public void onNext(HttpWrapper<String> httpWrapperResponse) {
                        if(httpWrapperResponse.getCode()==200){
                            Intent intent=new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }else {
                            Toast.makeText(getActivity(), httpWrapperResponse.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                RetrofitUtil.getInstance().register(etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), etCode.getText().toString(), new ProgressSubscriber<HttpWrapper<String>>(mListener, getActivity()));

                break;
            case tv_sendCode:
                mListener = new SubscriberOnNextListener<HttpWrapper<String>>() {
                    @Override
                    public void onNext(HttpWrapper<String> httpWrapperResponse) {
                        if(httpWrapperResponse.getCode()==200){
                            Toast.makeText(getActivity(), "Send success", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), httpWrapperResponse.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                RetrofitUtil.getInstance().sendCode(etEmail.getText().toString(), new ProgressSubscriber<HttpWrapper<String>>(mListener, getActivity()));

                break;
            default:
                break;
        }
    }
}
