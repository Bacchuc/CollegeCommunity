package com.yzd.collegecommunity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzd.collegecommunity.R;
import android.content.Context;
import android.widget.ImageView;

import java.util.List;

import static com.yzd.collegecommunity.R.id.view;

/**
 * Created by Laiyin on 2017/3/11.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{

    private LayoutInflater minflater;
    private List<Integer> mDatas;

    public GalleryAdapter(Context context,List<Integer> datas){
        minflater=LayoutInflater.from(context);
        mDatas=datas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View arg0){
            super(arg0);
        }
        ImageView imageView;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
        View view=minflater.inflate(R.layout.layout_recyclerview_item,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.imageView=(ImageView) view.findViewById(R.id.iv_picture);
        return viewHolder;
    }

    public void onBindViewHolder(final ViewHolder viewHolder,final int i){
        viewHolder.imageView.setImageResource(mDatas.get(i));
    }
}
