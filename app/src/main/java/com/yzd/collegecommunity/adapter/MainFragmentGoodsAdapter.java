package com.yzd.collegecommunity.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.modeal.GoodsWrapper;
import com.yzd.collegecommunity.util.GlideHelper;

import java.util.List;

/**
 * Created by Laiyin on 2017/4/26.
 */

public class MainFragmentGoodsAdapter extends ArrayAdapter<GoodsWrapper.ListEntity> implements View.OnClickListener {

    private int resouredId;
    private Context context;
    private Activity mContext;
    private List<GoodsWrapper.ListEntity> list;

    public MainFragmentGoodsAdapter(Context context, int testResouredId, List<GoodsWrapper.ListEntity> list) {
        super(context, testResouredId, list);
        this.context = context;
        this.resouredId = testResouredId;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        GoodsWrapper.ListEntity goodsWrapper = getItem(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resouredId, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.list_item_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GlideHelper.loadPaintingImage(viewHolder.imageView, list, position);
        viewHolder.textView.setText(list.get(position).getTitle());
        viewHolder.textView.setTag(position);
        convertView.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View view) {
        if (mOnClickOptionListener != null) {
            mOnClickOptionListener.onClick(view.findViewById(R.id.iv), (int)(view.findViewById(R.id.list_item_title).getTag()));
        }
//        switch (view.getId()) {
//            case R.id.ib_contact:
//                Intent intent = new Intent(mContext, ChatActivity.class);
//                mContext.startActivity(intent);
//                break;
//            default:
//                break;
//        }
    }

    public OnClickOptionListener mOnClickOptionListener;

    public void setOnClickOptionListener(OnClickOptionListener listener) {
        mOnClickOptionListener = listener;
    }

    public interface OnClickOptionListener {
        void onClick(View view, int position);
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
//        View listTouchInterceptor;
//        FrameLayout mDetailsLayout;
//        UnfoldableView mUnfoldableView;
    }
}
