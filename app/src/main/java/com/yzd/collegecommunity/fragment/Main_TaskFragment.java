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

/**
 * Created by Laiyin on 2017/3/5.
 */

public class Main_TaskFragment extends Fragment {

    @BindView(R.id.lv_task)
    ListView lvTask;
    private MainFragmentTaskListAdapter mainFragmentTaskListAdapter;
    private SubscriberOnNextListener mListener;
    private List<TaskWrapper.ListEntity> mainTaskListInfoList = new ArrayList<TaskWrapper.ListEntity>();

//    private Thread thread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_task, container, false);
        ButterKnife.bind(this, view);
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
                    //登陆成功后得到data中的token
//                    SPUtil.refreshToken(httpWrapperResponse.getInfo());

                    System.out.println(httpWrapperResponse.getData().toString()+"----------------------------------------------------");
                    System.out.println(httpWrapperResponse.getData()+"----------------------------------------------------");

                    mainTaskListInfoList.clear();
                    mainTaskListInfoList.addAll(httpWrapperResponse.getData().getList());
                    mainFragmentTaskListAdapter.notifyDataSetChanged();  //更新数据
                } else {
                    Toast.makeText(getActivity(), httpWrapperResponse.getInfo(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        RetrofitUtil.getInstance().getMainTaskInfo(
                new ProgressSubscriber<HttpWrapper<TaskWrapper>>(mListener, getActivity()));

    }
}
