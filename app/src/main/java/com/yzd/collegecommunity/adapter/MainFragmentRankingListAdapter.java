package com.yzd.collegecommunity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.activity.MeContactActivity;
import com.yzd.collegecommunity.constants.Constants;
import com.yzd.collegecommunity.modeal.RankingWrapper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Laiyin on 2017/3/16.
 */

public class MainFragmentRankingListAdapter extends RecyclerView.Adapter<MainFragmentRankingListAdapter.MyViewHolder> {

    private List<RankingWrapper.ListEntity> list;
    private List<String> listImage;
    private Context context;
    private LinearLayoutManager linearLayoutManager;

    public MainFragmentRankingListAdapter(Context context, List<RankingWrapper.ListEntity> list) {
        this.list = list;
        this.context = context;
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if(viewType==0) {
//            View view = LayoutInflater.from(context).inflate(R.layout.main_fragment_ranking_item, parent, null);
//            MyViewHolder holder = new MyViewHolder(view);
            MyViewHolder holder = new MyViewHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.main_fragment_ranking_item, parent, false));
            holder.bt_contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MeContactActivity.class);
                    context.startActivity(intent);
                }
            });
            return holder;
//        }
//        else{
//            MyUserViewHolder holderUser=new MyUserViewHolder(
//                    LayoutInflater.from(context).inflate(R.layout.main_fragment_ranking_item,parent,false));
//            holderUser.bt_contact.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent=new Intent(context, MeContactActivity.class);
//                    context.startActivity(intent);
//                }
//            });
//            return holderUser;
//        }}
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

//        if(holder instanceof MyViewHolder){
        listImage = list.get(position).getImages();
        if (listImage.size() > 0) {
            holder.recyclerImage.setLayoutManager(linearLayoutManager);
            MainFragmentRankingListImageAdapter adapter = new MainFragmentRankingListImageAdapter(context, listImage);
            holder.recyclerImage.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.recyclerImage.setAdapter(adapter);
        } else {
            holder.recyclerImage.setLayoutManager(linearLayoutManager);
            holder.recyclerImage.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            holder.recyclerImage.setVisibility(View.INVISIBLE);
        }
        holder.tv_username.setText(list.get(position).getUsername());
        Glide.with(context)
                .load(Constants.BASEURL + list.get(position).getPath())
                .into(holder.ib_head_picture);
//        }
//        else if (holder instanceof MyUserViewHolder){
//            ((MyUserViewHolder)holder).tv_username.setText(list.get(position).getUsername());
//            Glide.with(context)
//                    .load(Constants.BASEURL+list.get(position).getPath())
//                    .into(((MyUserViewHolder)holder).ib_head_picture);
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //图片加载
    class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerImage;
        CircleImageView ib_head_picture;
        Button bt_contact;
        TextView tv_username;

        public MyViewHolder(View itemView) {
            super(itemView);
            recyclerImage = (RecyclerView) itemView.findViewById(R.id.rv_image);
            ib_head_picture = (CircleImageView) itemView.findViewById(R.id.ib_head_picture);
            bt_contact = (Button) itemView.findViewById(R.id.bt_contact);
            tv_username = (TextView) itemView.findViewById(R.id.tv_username);
        }
    }

//    //用户信息加载
//    class MyUserViewHolder extends RecyclerView.ViewHolder{
//        CircleImageView ib_head_picture;
//        Button bt_contact;
//        TextView tv_username;
//        public MyUserViewHolder(View itemView) {
//            super(itemView);
//            ib_head_picture= (CircleImageView) itemView.findViewById(R.id.ib_head_picture);
//            bt_contact= (Button) itemView.findViewById(R.id.bt_contact);
//            tv_username= (TextView) itemView.findViewById(R.id.tv_username);
//        }
//    }

}
