package com.yzd.collegecommunity.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.alexvasilkov.foldablelayout.shading.GlanceFoldShading;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.activity.ChatActivity;
import com.yzd.collegecommunity.activity.MainActivity;
import com.yzd.collegecommunity.adapter.MainFragmentGoodsAdapter;
import com.yzd.collegecommunity.modeal.GoodsWrapper;
import com.yzd.collegecommunity.modeal.HttpWrapper;
import com.yzd.collegecommunity.retrofit.ProgressSubscriber;
import com.yzd.collegecommunity.retrofit.SubscriberOnNextListener;
import com.yzd.collegecommunity.util.GlideHelper;
import com.yzd.collegecommunity.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Laiyin on 2017/3/20.
 */

public class MeGoodsMyPublishFragmentSellingFragment extends Fragment {
    private static final String TAG = "Main_GoodsFragment";

    @BindView(R.id.details_image)
    ImageView detailsImage;
    @BindView(R.id.details_title)
    TextView detailsTitle;
    @BindView(R.id.details_text)
    TextView detailsText;
    @BindView(R.id.bt_contact)
    Button btContact;
    private ListView listView;
    private View listTouchInterceptor;
    private FrameLayout mDetailsLayout;
    private UnfoldableView mUnfoldableView;
    private MainFragmentGoodsAdapter mainFragmentGoodsAdapter;
    private SubscriberOnNextListener mListener;
    private List<GoodsWrapper.ListEntity> mainGoodsListInfoList = new ArrayList<GoodsWrapper.ListEntity>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment_goods_mypublish_selling, container, false);
        initData();
        initView(view);
        initSet();
        ButterKnife.bind(this, view);
        return view;
    }

    private void initSet() {
        listView.setAdapter(mainFragmentGoodsAdapter);
        listTouchInterceptor.setClickable(false);
        mDetailsLayout.setVisibility(View.INVISIBLE);

        //图片渲染效果：反光
        Bitmap glance = BitmapFactory.decodeResource(getResources(), R.drawable.unfold_glance);
        mUnfoldableView.setFoldShading(new GlanceFoldShading(glance));

        mUnfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(true);
                mDetailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(false);
                mDetailsLayout.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list_view);
        listTouchInterceptor = view.findViewById(R.id.touch_interceptor_view);
        mDetailsLayout = (FrameLayout) view.findViewById(R.id.details_layout);
        mUnfoldableView = (UnfoldableView) view.findViewById(R.id.unfoldable_view);

        mainFragmentGoodsAdapter = new MainFragmentGoodsAdapter(getActivity(), R.layout.main_fragment_goods_item, mainGoodsListInfoList);
        mainFragmentGoodsAdapter.setOnClickOptionListener((view1, position) -> {
            if (view1.getContext() instanceof MainActivity) {
                Log.i(TAG, "initView: ---" + position);
                detailsTitle.setText(mainGoodsListInfoList.get(position).getTitle());
                detailsText.setText(mainGoodsListInfoList.get(position).getDescription());
                GlideHelper.loadPaintingImage(detailsImage,mainGoodsListInfoList,position);
                openDetails(view1);
            }
        });
    }

//    public void onBackPressed() {
//        if (mUnfoldableView != null && (mUnfoldableView.isUnfolded() || mUnfoldableView.isUnfolding())) {
//            mUnfoldableView.foldBack();
//        } else {
//            super.getActivity().onBackPressed();
//        }
//    }

    public void openDetails(View coverView) {
        mUnfoldableView.unfold(coverView, mDetailsLayout);
    }

    private void initData() {

        mListener = new SubscriberOnNextListener<HttpWrapper<GoodsWrapper>>() {
            @Override
            public void onNext(HttpWrapper<GoodsWrapper> httpWrapperResponse) {
                if (httpWrapperResponse.getCode() == 200) {
                    mainGoodsListInfoList.clear();
                    mainGoodsListInfoList.addAll(httpWrapperResponse.getData().getList());
                    mainFragmentGoodsAdapter.notifyDataSetChanged();  //更新数据
                } else {
                    Toast.makeText(getActivity(), httpWrapperResponse.getInfo(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        RetrofitUtil.getInstance().getMeGoodsMyPublicSellingInfo(
                new ProgressSubscriber<HttpWrapper<GoodsWrapper>>(mListener, getActivity()));
    }

    @OnClick(R.id.bt_contact)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_contact:
//                mainGoodsListInfoList.get()
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }
}
