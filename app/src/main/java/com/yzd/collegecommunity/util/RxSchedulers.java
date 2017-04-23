package com.yzd.collegecommunity.util;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Laiyin on 2017/4/16.
 */

public class RxSchedulers {

    private RxSchedulers() {
    }

    /**
     * RxJava切换线程
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> switchThread() {
        return tObservable -> tObservable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * RxJava切换线程
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> switchHeaderThread() {
        return tObservable -> tObservable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
