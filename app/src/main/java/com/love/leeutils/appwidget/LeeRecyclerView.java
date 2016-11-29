package com.love.leeutils.appwidget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.bumptech.glide.Glide;

/**
 * Created by lixinxin on 2016/11/24.
 * 邮箱 895330766@qq.com
 * <p>
 * 带有加载更多的 RecyclerView
 */

public class LeeRecyclerView extends RecyclerView {

    private onLoadMoreListener loadMoreListener;    //加载更多回调
    private boolean isLoadingMore;                  //是否加载更多
    private Context context;

    public LeeRecyclerView(Context context) {
        this(context, null);
    }

    public LeeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        isLoadingMore = false;
        addOnScrollListener(new AutoLoadScrollListener());
        this.context = context;
    }

    public void setLoadMoreListener(onLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public void setIsLoadingMore(boolean b) {
        this.isLoadingMore = b;

    }

    //加载更多的回调接口
    public interface onLoadMoreListener {
        void loadMore();
    }


    /**
     * 监听滑动
     */
    private class AutoLoadScrollListener extends OnScrollListener {

        public AutoLoadScrollListener() {
            super();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == 0) {   //0闲置    1拖动  2
                Glide.with(context).resumeRequests();
            } else {
                Glide.with(context).pauseRequests();
            }
            Log.e("Auto", newState + "");
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //有回调接口，且不是加载状态，且计算后剩下2个item，且处于向下滑动，则自动加载
            //由于GridLayoutManager是LinearLayoutManager子类，所以也适用
            if (getLayoutManager() instanceof LinearLayoutManager) {
                int lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = LeeRecyclerView.this.getAdapter().getItemCount();

                //有回调接口，且不是加载状态，且计算后剩下2个item，且处于向下滑动，则自动加载
                if (loadMoreListener != null && !isLoadingMore && lastVisibleItem >= totalItemCount -
                        2 && dy > 0) {
                    isLoadingMore = true;
                    loadMoreListener.loadMore();
                }
            }
        }
    }


}
