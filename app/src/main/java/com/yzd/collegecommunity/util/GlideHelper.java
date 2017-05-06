package com.yzd.collegecommunity.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yzd.collegecommunity.constants.Constants;
import com.yzd.collegecommunity.modeal.GoodsWrapper;

import java.util.List;

public class GlideHelper {

    private GlideHelper() {}

    public static void loadPaintingImage(ImageView image,List<GoodsWrapper.ListEntity> list,int position) {
        Glide.with(AppCenterUtil.getContextObject())
                .load(Constants.BASEURL+list.get(position).getGoods_photo())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image);
    }

}
