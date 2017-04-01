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
import android.widget.Toast;

import com.google.gson.Gson;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.activity.MainActivity;
import com.yzd.collegecommunity.activity.ThirdActivity;
import com.yzd.collegecommunity.constants.Constants;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.retrofit.LoginImpl;
import com.yzd.collegecommunity.util.BlurBehind;
import com.yzd.collegecommunity.util.OnBlurCompleteListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASEURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                LoginImpl loginService = retrofit.create(LoginImpl.class);

                loginService.login(etUsername.getText().toString(), etPassword.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<HttpWrapper<String>>() {

                            /**
                             * 表示事件队列完结。RxJava 不仅把每个事件单独处理，还会把它们看做一个队列，RxJava 规定，
                             * 当不会再有新的 onNext() 发出时，需要触发 onCompleted() 方法作为结束标志。
                             */
                            @Override
                            public void onCompleted() {
//                                Toast.makeText(getActivity(), "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
                                //nothing
                            }


                            /**
                             * 事件队列异常。在事件处理过程中出异常时， onError() 会被触发，同时队列自动终止，不允许再有事件发出。
                             * 在一个正确运行的事件序列中， onCompleted() 和 onError() 有且只有一个，并且是事件序列中的最后一个。
                             * 需要注意的是 onCompleted() 和 onError() 二者是互斥的，即在队列中调用了其中一个，就不再调用另一个。
                             *
                             * 服务端无响应（请求不到 地址错误或者超时）
                             * @param e 异常信息
                             */
                            @Override
                            public void onError(Throwable e) {
//                                email.setText(e.getMessage());
                                Log.e(TAG,e.getMessage());
                                Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                            }

                            /**
                             * 接受发送的事件，即接受数据
                             * @param loginService 实现用户登陆的网络请求接口
                             *
                             */
                            @Override
                            public void onNext(HttpWrapper<String> loginService) {
                                //200请求成功 400为有响应但是请求失败，服务器端有错误（数据库读写错误，如参数传错了）
//                                if (new Gson().toJson(loginService.getCode()).equals("200")){
//                                    if(new Gson().toJson(loginService.getInfo()).equals("\"success\"")){  //登陆成功
//                                        Intent intent=new Intent(getActivity(), MainActivity.class);
//                                        startActivity(intent);
//                                    }else if(new Gson().toJson(loginService.getInfo()).equals("\"error\"")){  //登陆失败但是属于响应请求成功
//                                        Log.e(TAG,new Gson().toJson(loginService.getInfo()));
//                                        Toast.makeText(getActivity(), "User name or password error", Toast.LENGTH_SHORT).show();
//                                    }
//                                }else if(new Gson().toJson(loginService.getCode()).equals("400")){
//                                    Log.e(TAG,new Gson().toJson(loginService.getInfo()));
//                                    Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
//                                }

                                if (new Gson().toJson(loginService.getCode()).equals("200")){

                                    if(new Gson().toJson(loginService.getInfo()).equals("\"success\"")){

                                        Intent intent=new Intent(getActivity(), MainActivity.class);
                                        startActivity(intent);

                                    }else{
                                        Toast.makeText(getActivity(), loginService.getInfo(), Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                break;
//            case R.id.tv_third:
//                startThirdActivity();
//                break;
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
