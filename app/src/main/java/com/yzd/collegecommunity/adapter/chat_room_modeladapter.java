package com.yzd.collegecommunity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yzd.collegecommunity.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/29 0029.
 */
public class chat_room_modeladapter extends BaseAdapter {
    private List<chat_room_model> mdata;
    public chat_room_modeladapter(Context context, List data){
        this.mdata=data;
        this.mcontext=context;
    }
    private Context mcontext;
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
    private static class Holder{
        public chat_room_imagview iv_avatar;
        public TextView tv3;
        public TextView tv4;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=null;
        Holder holder=null;
        if(view!=null){v=view;holder= (Holder) v.getTag();}
        else{v= View.inflate(mcontext, R.layout.chat_room_item02,null);
        holder=new Holder();
        holder.iv_avatar= (chat_room_imagview) v.findViewById(R.id.myImageView);
        holder.tv3= (TextView) v.findViewById(R.id.textView3);
        holder.tv4= (TextView) v.findViewById(R.id.textView4);
        v.setTag(holder);
        }
        chat_room_model model=mdata.get(i);
        holder.iv_avatar.setImageResource(model.getAvatar());
        holder.tv3.setText(model.getTv3());
        holder.tv4.setText(model.getTv4());
        return v;
    }
}
