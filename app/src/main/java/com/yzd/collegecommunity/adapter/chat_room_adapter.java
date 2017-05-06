package com.yzd.collegecommunity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzd.collegecommunity.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class chat_room_adapter extends BaseAdapter {
    private List<chat_room_message> mdata;
    private Context mcontext;
    private int resourceId;

    public chat_room_adapter(Context context, int resource, List<chat_room_message> objects) {
        this.mcontext = context;
        this.mdata = objects;
        this.resourceId = resource;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int i) {
        return mdata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public Context getContext() {
        return mcontext;
    }

    class Holder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        chat_room_imagview myImageViewleft;
        chat_room_imagview myImageViewright;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = null;
        Holder holder=null;
        if (view != null) {
            v = view;
            holder=(Holder)v.getTag();
        } else {
            v = LayoutInflater.from(getContext()).inflate(resourceId, null);
            holder=new Holder();
            holder.leftLayout = (LinearLayout) v.findViewById(R.id.left_layout);
            holder.rightLayout = (LinearLayout) v.findViewById(R.id.right_layout);
            holder.leftMsg = (TextView) v.findViewById(R.id.left_msg);
            holder.rightMsg = (TextView) v.findViewById(R.id.right_msg);
            holder.myImageViewleft= (chat_room_imagview) v.findViewById(R.id.myImageViewleft);
            holder.myImageViewright= (chat_room_imagview) v.findViewById(R.id.myImageViewright);

            v.setTag(holder);
        }
        chat_room_message msg=mdata.get(i);
        if (msg.getType()== chat_room_message.TYPE_RECEIVED) {
            //如果是收到消息，就显示左边的布局，隐藏右边的消息布局
            holder.myImageViewleft.setVisibility(View.VISIBLE);
            holder.myImageViewright.setVisibility(View.GONE);
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());

        } else if(msg.getType()== chat_room_message.TYPE_SENT){
            //如果是发送消息，就显示右边的布局，隐藏左边的消息布局
            holder.myImageViewleft.setVisibility(View.GONE);
            holder.myImageViewright.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(msg.getContent());
        }
        return v;
    }

}