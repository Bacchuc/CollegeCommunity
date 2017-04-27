package com.yzd.collegecommunity.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.constants.Constants;

import java.util.List;

/**
 * Created by Laiyin on 2017/3/16.
 */

public class MainFragmentRankingListImageAdapter extends RecyclerView.Adapter<MainFragmentRankingListImageAdapter.MyImageHolder> {
    private Context context;
    private List<String> list;
    private DisplayMetrics dm;

    public MainFragmentRankingListImageAdapter(Context context, List<String> list){
        this.context=context;
        this.list=list;
        dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
    }

    public MyImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_fragment_ranking_item_image,null);
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((dm.widthPixels - dip2px(20)) / 4, (dm.widthPixels - dip2px(20)) / 4);
//        view.setLayoutParams(lp);
        MyImageHolder holder=new MyImageHolder(view);
        return holder;
    }

    public void onBindViewHolder(MyImageHolder holder, int position) {
        Glide.with(context)
                .load(Constants.BASEURL+list.get(position))
                .centerCrop()
                .crossFade()
                .into(holder.imageView);
        holder.itemView.setTag(list.get(position));
    }

    public int getItemCount() {
        return list.size();
    }
    class MyImageHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public MyImageHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_picture);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
