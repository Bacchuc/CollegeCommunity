package com.yzd.collegecommunity.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yzd.collegecommunity.R;

/**
 * Created by Laiyin on 2017/4/4.
 */

public class PopupWindowSelectUtil extends PopupWindow {

    private TextView tvTake;
    private TextView tvSelect;
    private ImageView mImage;
    private int viewLayout;
    private View view;
    private Context mContext;
    private Activity activity;
    private PopupWindow popupWindow;
    private LayoutInflater inflater;

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    SelectImageUtil selectImageUtil;
    SelectImageUtil selectImageUtilResult;

    public PopupWindowSelectUtil(Context context, Activity activity, int viewLayout) {
        this.mContext = context;
        this.activity = activity;
        this.viewLayout = viewLayout;
        //this.mImage=mImage;
        initPopupWindow();
    }

    private void initPopupWindow() {
        inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.demand_popup_select, null);
        popupWindow = new PopupWindow(view, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.popup_window);
        popupWindow.setContentView(view);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);

        WindowManager.LayoutParams params = activity.getWindow().getAttributes();//创建当前界面的一个参数对象
        params.alpha = 0.8f;
        activity.getWindow().setAttributes(params);//把该参数对象设置进当前界面中

        selectImageUtil = new SelectImageUtil(activity, mImage);

        show();

        myDismiss();

        tvTake = (TextView) view.findViewById(R.id.tv_take);
        tvTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selectImageUtil.takePicture();
                if (mOnPopWindowOptionListener != null) {
                    mOnPopWindowOptionListener.onTakePhoto();
                }
                popupWindow.dismiss();
            }
        });

        tvSelect = (TextView) view.findViewById(R.id.tv_select);
        tvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selectImageUtil.choosePicture();
                if (mOnPopWindowOptionListener != null) {
                    mOnPopWindowOptionListener.onChoosePhoto();
                }
                popupWindow.dismiss();
            }
        });
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        selectImageUtilResult=new SelectImageUtil(activity, mImage);
//        selectImageUtilResult.onActivityResult(requestCode, resultCode, data);
//    }

    /**
     * popupwindow弹出时设置原Activity背景透明
     */
    private void myDismiss() {
        popupWindow.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = activity.getWindow().getAttributes();
                params.alpha = 1.0f;//设置为不透明，即恢复原来的界面
                activity.getWindow().setAttributes(params);
            }
        });
    }

    /**
     * Activity调用show()弹出popupwindow
     */
    public void show() {
        //第一个参数为父View对象，即PopupWindow所在的父控件对象，第二个参数为它的重心，后面两个分别为x轴和y轴的偏移量
        popupWindow.showAtLocation(inflater.inflate(viewLayout, null), Gravity.CENTER, 0, 0);
        //设置显示PopupWindow的位置位于View的左下方，x,y表示坐标偏移量
        //popupWindow.showAsDropDown(view,100,100);
    }

    public OnPopWindowOptionListener mOnPopWindowOptionListener;

    public void setOnPopWindowOptionListener(OnPopWindowOptionListener listener) {
        mOnPopWindowOptionListener = listener;
    }

    public interface OnPopWindowOptionListener {
        void onTakePhoto();

        void onChoosePhoto();
    }
}
