package com.yzd.collegecommunity.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzd.collegecommunity.R;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Laiyin on 2017/3/6.
 */

public class MainFragmentTaskAdapter extends BaseAdapter implements View.OnClickListener{

    private Activity mContext;
    private List<Map<String, Object>> dates;

    private CircleImageView ib_head_picture;
    private ImageButton bt_like;
    private ImageButton ib_contact;
    private ImageView iv_task_picture;
    private TextView tv_describe;
    private TextView tv_username;

    public MainFragmentTaskAdapter(Activity context, List<Map<String, Object>> dates){
        this.mContext = context;
        this.dates = dates;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int i) {
        return dates.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final View view1 = LayoutInflater.from(mContext).inflate(R.layout.main_fragment_task_item,null,false);

        view1.setTag(false);        //默认取消true，收藏为true

        initView(view1);

        initListener();

        return view1;

    }

    private void initView(View view) {

        ib_head_picture= (CircleImageView) view.findViewById(R.id.ib_head_picture);
        bt_like= (ImageButton) view.findViewById(R.id.bt_like);
        ib_contact= (ImageButton) view.findViewById(R.id.ib_contact);
        iv_task_picture= (ImageView) view.findViewById(R.id.iv_task_picture);
        tv_describe= (TextView) view.findViewById(R.id.tv_describe);
        tv_username= (TextView) view.findViewById(R.id.tv_username);

        bt_like.setImageResource(R.drawable.heart_gray);
    }

    private void initListener() {
        bt_like.setOnClickListener(this);
        ib_contact.setOnClickListener(this);
        ib_head_picture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_like:
                if ((boolean)(view.getTag())) {
                    bt_like.setImageResource(R.drawable.heart_gray);
                    view.setTag(false);
                }
                else {
                    bt_like.setImageResource(R.drawable.heart_orange);
                    view.setTag(true);
                }
                break;
            case R.id.ib_contact:
//                Intent intent;
//                intent = new Intent(mContext, secondhand_market.class);
//                mContext.startActivity(intent);
                break;
            case R.id.ib_head_picture:
//                Intent intent1;
//                intent1 = new Intent1(mContext, secondhand_market.class);
//                mContext.startActivity(intent1);
                break;
            default:
                break;
        }

    }
}
