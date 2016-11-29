package com.love.leeutils.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.love.leeutils.R;
import com.love.leeutils.modle.entity.MeizhiEntity;
import com.love.leeutils.utils.ImageUtils;

import java.util.List;

/**
 * Created by lixinxin on 2016/11/29.
 * 邮箱 895330766@qq.com
 */

public class LeeAdapter extends RecyclerView.Adapter<LeeAdapter.ViewHolder> {

    private Context context;
    public List<MeizhiEntity> mData;

    public LeeAdapter(Context context, List<MeizhiEntity> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_image, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageUtils.imageLoader(context, holder.imageView, mData.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.lee_image);
        }
    }

}
