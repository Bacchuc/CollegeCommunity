package com.yzd.collegecommunity.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.fragment.TaskMainFragment;
import com.yzd.collegecommunity.modeal.MainTaskListInfo;

import java.util.List;

import at.markushi.ui.CircleButton;

/**
 * Created by Laiyin on 2017/3/6.
 */

public class MainFragmentTaskListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<MainTaskListInfo> lsit;
    private int layoutId;

    public MainFragmentTaskListAdapter(Context context, int layoutId, List<MainTaskListInfo> lsit) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(mContext);
        this.lsit = lsit;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return lsit.size();
    }

    @Override
    public Object getItem(int i) {
        return lsit.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(layoutId, null);
            viewHolder.ib_head_picture = (CircleButton) view.findViewById(R.id.ib_head_picture);
            viewHolder.iv_task_picture = (ImageView) view.findViewById(R.id.iv_task_picture);
            viewHolder.tv_username = (TextView) view.findViewById(R.id.tv_username);
            viewHolder.tv_describe = (TextView) view.findViewById(R.id.tv_describe);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.ib_head_picture.setTag(i);
        viewHolder.tv_describe.setText(lsit.get(i).getIntroduce());
//        BmobFile file=new BmobFile(System.currentTimeMillis()+".png","",lsit.get(i).getPicUrl());
//        if (lsit.get(i).getPicUrl()!=null){
//            Bitmap bitmap=downloadFile(file,i);
//        }else {
//            viewHolder.mm_v1_Image.setImageResource(R.drawable.my_listview);
//        }
        return view;
    }

    class ViewHolder {

        CircleButton ib_head_picture;  //头像
        ImageView iv_task_picture;     //任务列表头像
        TextView tv_describe;          //任务描述
        TextView tv_username;          //用户名
    }
}
