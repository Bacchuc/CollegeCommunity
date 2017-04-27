package com.yzd.collegecommunity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.adapter.MainFragmentRankingListAdapter;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.modeal.RankingWrapper;
import com.yzd.collegecommunity.modeal.TaskWrapper;
import com.yzd.collegecommunity.retrofit.ProgressSubscriber;
import com.yzd.collegecommunity.retrofit.SubscriberOnNextListener;
import com.yzd.collegecommunity.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Laiyin on 2017/3/5.
 */

public class Main_RankingFragment extends Fragment {

    @BindView(R.id.lv_ranking)
    RecyclerView lvRanking;
    private MainFragmentRankingListAdapter mainFragmentRankingListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private SubscriberOnNextListener mListener;
    private List<RankingWrapper.ListEntity> mainRankingListInfoList = new ArrayList<RankingWrapper.ListEntity>();
    private Activity mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_ranking, container, false);
        ButterKnife.bind(this, view);
        getData();
        initView();
        return view;
    }

    private void initView() {
        //new布局管理器
        linearLayoutManager = new LinearLayoutManager(mContext);

        //设置为垂直布局，这也是默认的
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //设置布局管理器
        lvRanking.setLayoutManager(linearLayoutManager);

        //new适配器
        mainFragmentRankingListAdapter = new MainFragmentRankingListAdapter(getActivity(),mainRankingListInfoList);

        //设置适配器
        lvRanking.setAdapter(mainFragmentRankingListAdapter);

        //设置增加或删除条目的动画  
//        recyclerview.setItemAnimator(new DefaultItemAnimator());
    }

    private void getData() {
        mListener = new SubscriberOnNextListener<HttpWrapper<RankingWrapper>>() {
            @Override
            public void onNext(HttpWrapper<RankingWrapper> httpWrapperResponse) {
                if (httpWrapperResponse.getCode() == 200) {
                    mainRankingListInfoList.clear();
                    mainRankingListInfoList.addAll(httpWrapperResponse.getData().getList());
                    mainFragmentRankingListAdapter.notifyDataSetChanged();  //更新数据
                } else {
                    Toast.makeText(getActivity(), httpWrapperResponse.getInfo(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        RetrofitUtil.getInstance().getMainRankingInfo(
                new ProgressSubscriber<HttpWrapper<RankingWrapper>>(mListener, getActivity()));

    }
}
