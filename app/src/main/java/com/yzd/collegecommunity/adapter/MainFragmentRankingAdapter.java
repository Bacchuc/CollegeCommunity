package com.yzd.collegecommunity.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import com.yzd.collegecommunity.R;
import com.yzd.collegecommunity.activity.ChatActivity;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.yzd.collegecommunity.R.id.bt_contact;
import static com.yzd.collegecommunity.R.id.ib_head_picture;

/**
 * Created by Laiyin on 2017/3/16.
 */

public class MainFragmentRankingAdapter extends BaseAdapter implements View.OnClickListener {

    private Activity mContext;
    private List<Map<String, Object>> dates;

    public MainFragmentRankingAdapter(Activity context, List<Map<String, Object>> dates) {
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

        final View view1 = LayoutInflater.from(mContext).inflate(R.layout.main_fragment_ranking_item, null, false);

        return view1;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_contact:
                Intent intent = new Intent(mContext, ChatActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ib_head_picture:
//                Intent intent1 = new Intent1(mContext, secondhand_market.class);
//                mContext.startActivity(intent1);
                break;
            default:
                break;
        }

    }

    static class ViewHolder {
        @BindView(ib_head_picture)
        CircleImageView ibHeadPicture;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_school)
        TextView tvSchool;
        @BindView(bt_contact)
        Button btContact;
        @BindView(R.id.rv_image)
        RecyclerView rvImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
