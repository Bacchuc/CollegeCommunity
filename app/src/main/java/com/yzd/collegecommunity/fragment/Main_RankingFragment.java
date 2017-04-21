package com.yzd.collegecommunity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.adapter.MainFragmentRankingListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yzd.collegecommunity.R.id.lv_ranking;

/**
 * Created by Laiyin on 2017/3/5.
 */

public class Main_RankingFragment extends Fragment {

    @BindView(R.id.lv_ranking)
    RecyclerView lvRanking;
    private ArrayList list;
    private MainFragmentRankingListAdapter listAdapter;
    private LinearLayoutManager linearLayoutManager;

    private Activity mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_ranking, container, false);

        initView(view);

        ButterKnife.bind(this, view);
        return view;
    }

    private void initView(View view) {
        lvRanking= (RecyclerView) view.findViewById(lv_ranking);

        //new布局管理器
        linearLayoutManager = new LinearLayoutManager(mContext);

        //设置为垂直布局，这也是默认的
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //设置布局管理器
        lvRanking.setLayoutManager(linearLayoutManager);

        initDate();

        //new适配器
        listAdapter = new MainFragmentRankingListAdapter(getActivity(),list);

        //设置适配器
        lvRanking.setAdapter(listAdapter);

        //设置增加或删除条目的动画  
//        recyclerview.setItemAnimator(new DefaultItemAnimator());
    }

    private void initDate() {
        list=new ArrayList();
        for(int i=0;i<15;i++){
            list.add("this is "+i);
        }
    }

}
