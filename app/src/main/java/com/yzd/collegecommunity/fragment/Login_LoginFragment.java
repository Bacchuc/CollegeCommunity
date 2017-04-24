package com.yzd.collegecommunity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.activity.MainActivity;
import com.yzd.collegecommunity.activity.ThirdActivity;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.retrofit.ProgressSubscriber;
import com.yzd.collegecommunity.retrofit.SubscriberOnNextListener;
import com.yzd.collegecommunity.util.BlurBehind;
import com.yzd.collegecommunity.util.OnBlurCompleteListener;
import com.yzd.collegecommunity.util.RetrofitUtil;
import com.yzd.collegecommunity.util.SPUtil;
import com.yzd.collegecommunity.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yzd.collegecommunity.R.id.bt_login;

/**
 * Created by Laiyin on 2017/3/4.
 */

public class Login_LoginFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;

    private String TAG = "Login_LoginFragment";

    private SubscriberOnNextListener mListener;

    private String res;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment_login, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.bt_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case bt_login:
                mListener = new SubscriberOnNextListener<HttpWrapper<String>>() {
                    @Override
                    public void onNext(HttpWrapper<String> httpWrapperResponse) {
                        if (httpWrapperResponse.getCode() == 200) {
                            //登陆成功后得到data中的token
                            SPUtil.refreshToken(httpWrapperResponse.getData());
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            ToastUtil.showShort(getActivity(), httpWrapperResponse.getInfo());
                        }
                    }
                };
                RetrofitUtil.getInstance().login(etUsername.getText().toString(),
                        etPassword.getText().toString(),
                        new ProgressSubscriber<HttpWrapper<String>>(mListener, getActivity()));
                break;
            default:
                break;
        }
    }

    /**
     * 打开第三方登陆页面
     */
    public void startThirdActivity() {
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
