package com.yzd.collegecommunity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzd.collegecommunity.R;

import java.util.ArrayList;

/**
 * Created by Laiyin on 2017/3/16.
 */

public class MainFragmentRankingListAdapter extends RecyclerView.Adapter<MainFragmentRankingListAdapter.MyViewHolder> {

    private ArrayList list;
    private Context context;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList list_image;

    public MainFragmentRankingListAdapter(Context context,ArrayList list) {
        this.list=list;
        this.context = context;
        linearLayoutManager =new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        list_image=new ArrayList();
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/7aec54e736d12f2ec564664446c2d562853568b5.jpg");
        list_image.add("http://c.hiphotos.baidu.com/movie/pic/item/e4dde71190ef76c6e52d8fe29516fdfaae51677f.jpg");
        list_image.add("http://c.hiphotos.baidu.com/movie/pic/item/fd039245d688d43f5d3648ec741ed21b0ef43b15.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");
        list_image.add("http://e.hiphotos.baidu.com/movie/pic/item/1ad5ad6eddc451da65ded2f5bffd5266d0163280.jpg");

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_fragment_ranking_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(list_image.size()>0) {
            holder.recyclerImage.setLayoutManager(linearLayoutManager);
            MainFragmentRankingListImageAdapter adapter = new MainFragmentRankingListImageAdapter(context, list_image);
            holder.recyclerImage.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            holder.recyclerImage.setAdapter(adapter);

        }else{
            holder.recyclerImage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerImage;
        public MyViewHolder(View itemView) {
            super(itemView);
            recyclerImage= (RecyclerView) itemView.findViewById(R.id.rv_image);
        }
    }

}
