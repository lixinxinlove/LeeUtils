package com.love.leeutils.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.love.leeutils.R;

/**
 * Created by lixinxin on 2016/11/27.
 * 邮箱 895330766@qq.com
 */

public class ImageUtils {

    public static void imageLoader(Context context, ImageView view, String url){
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .dontAnimate()
                .into(view);
    }
}
