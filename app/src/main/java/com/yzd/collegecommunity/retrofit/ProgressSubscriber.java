package com.yzd.collegecommunity.retrofit;

import android.content.Context;

import com.yzd.collegecommunity.util.AppCenterUtil;
import com.yzd.collegecommunity.util.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo
 * @date 2016/12/12  14:48
 *
 * 为Subscriber写一个子类，让其实现对话框的退出方法。因为Subscriber比Observer
 * （正常情况下都会被转换为Subscriber）会多一个onStart方法，我们可以在onStart中调用对话框显示，
 * 在onComplete方法中调用对话框的隐藏方法。线程切换其实也是可以封装成一个单独的方法的，
 * 这样又可以降低代码的耦合了
 */

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener{

    private SubscriberOnNextListener<T> mListener;
    private Context mContext;
    private ProgressDialogHandler mHandler;

    public ProgressSubscriber(SubscriberOnNextListener<T> listener, Context context){
        this.mListener = listener;
        this.mContext = context;
        mHandler = new ProgressDialogHandler(context,this,true);
    }

    private void showProgressDialog(){
        if (mHandler != null) {
            mHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mHandler != null) {
            mHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mHandler = null;
        }
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        super.onStart();
        showProgressDialog();
    }

    /**
     * 表示事件队列完结。RxJava 不仅把每个事件单独处理，还会把它们看做一个队列，RxJava 规定，
     * 当不会再有新的 onNext() 发出时，需要触发 onCompleted() 方法作为结束标志。
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
//        Toast.makeText(AppCenterUtil.getContextObject(),"获取数据完成！",Toast.LENGTH_SHORT).show();
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
        if (e instanceof SocketTimeoutException) {
            ToastUtil.showLong(AppCenterUtil.getContextObject(), "Network connection timeout");
        } else if (e instanceof ConnectException) {
            ToastUtil.showLong(AppCenterUtil.getContextObject(), "Network outage,please check your network status");
        } else {
            ToastUtil.showLong(AppCenterUtil.getContextObject(), "error:" + e.getMessage());

        }
        dismissProgressDialog();
    }

    @Override
    public void onNext(T t) {
        if (mListener != null){
            mListener.onNext(t);
        }
    }

    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()){
            this.unsubscribe();
        }
    }
}
