package com.yzd.collegecommunity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.adapter.MainFragmentTaskListAdapter;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.modeal.TaskWrapper;
import com.yzd.collegecommunity.retrofit.ProgressSubscriber;
import com.yzd.collegecommunity.retrofit.SubscriberOnNextListener;
import com.yzd.collegecommunity.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Laiyin on 2017/3/20.
 */

public class MeTaskOtherPublishFragmentUnderWayFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.lv_task)
    ListView lvTask;
    private MainFragmentTaskListAdapter mainFragmentTaskListAdapter;
    private SubscriberOnNextListener mListener;
    private List<TaskWrapper.ListEntity> mainTaskListInfoList = new ArrayList<TaskWrapper.ListEntity>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment_task_otherpublish_underway, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        initListener();
        initView();
        return view;
    }

    private void initView() {
        mainFragmentTaskListAdapter = new MainFragmentTaskListAdapter(this.getActivity(),
                R.layout.main_fragment_task_item, mainTaskListInfoList);
        lvTask.setAdapter(mainFragmentTaskListAdapter);
    }

    private void initListener() {
        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TaskWrapper.ListEntity mainTaskListInfo = mainTaskListInfoList.get(i);
//                Intent intent=new Intent(mContext,mission.class);
//                intent.putExtras();
//                startActivity(intent);
            }
        });
    }

    private void getData() {
        mListener = new SubscriberOnNextListener<HttpWrapper<TaskWrapper>>() {
            @Override
            public void onNext(HttpWrapper<TaskWrapper> httpWrapperResponse) {
                if (httpWrapperResponse.getCode() == 200) {
                    mainTaskListInfoList.clear();
                    mainTaskListInfoList.addAll(httpWrapperResponse.getData().getList());
                    mainFragmentTaskListAdapter.notifyDataSetChanged();  //更新数据
                } else {
                    Toast.makeText(getActivity(), httpWrapperResponse.getInfo(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        RetrofitUtil.getInstance().getMeTaskOtherPublicUnderWayInfo(
                new ProgressSubscriber<HttpWrapper<TaskWrapper>>(mListener, getActivity()));

    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
