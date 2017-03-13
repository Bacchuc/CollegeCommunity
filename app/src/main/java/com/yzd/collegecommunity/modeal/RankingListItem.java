package com.yzd.collegecommunity.modeal;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Laiyin on 2017/3/11.
 */

public class RankingListItem<T>{

    /**
     * 学校名称
     */
    public String schoolName;

    /**
     * 头像url
     */
    public String headImgUrl;

    /**
     * 用户所有图片url
     */
    public List<String> ImgUrl;

    public RankingListItem(String schoolName, String headImgUrl, List<String> ImgUrl){
        this.schoolName=schoolName;
        this.headImgUrl=headImgUrl;
        this.ImgUrl=ImgUrl;
    }
}
