package com.yzd.collegecommunity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzd.collegecommunity.R;

/**
 * Created by Laiyin on 2017/3/5.
 */

public class GoodsMainFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_task_main,container,false);

        initView(view);

        initListener();

        return view;
    }

    private void initView(View view) {

    }

    private void initListener() {

    }
}
