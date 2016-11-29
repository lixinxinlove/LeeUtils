package com.love.leeutils.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.love.leeutils.R;
import com.love.leeutils.appwidget.LeeRecyclerView;
import com.love.leeutils.base.BaseActivity;
import com.love.leeutils.modle.MeizhiData;
import com.love.leeutils.modle.entity.MeizhiEntity;
import com.love.leeutils.network.ApiServiceManager;
import com.love.leeutils.ui.adapter.LeeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanImageActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.lee_recycler_view)
    LeeRecyclerView leeRecyclerView;

    private List<MeizhiEntity> mData;
    private LeeAdapter adapter;

    @Override

    public int layoutId() {
        return R.layout.activity_scan_image;
    }

    @Override
    public void setListener4View() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL);
        mData = new ArrayList<>();
        adapter = new LeeAdapter(this, mData);
        leeRecyclerView.setLayoutManager(gridLayoutManager);
        leeRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanSize = layoutParams.getSpanSize();
                int spanIndex = layoutParams.getSpanIndex();
                outRect.top = 20;
                if (spanSize != gridLayoutManager.getSpanCount()) {
                    if (spanIndex == 1) {
                        outRect.left = 10;
                    } else {
                        outRect.right = 10;
                    }
                }
            }
        });
        leeRecyclerView.setAdapter(adapter);


        Call<MeizhiData> call = ApiServiceManager.getInstance().getMeizhiApi().getMeizhi(1);
        call.enqueue(callback);
    }

    Callback<MeizhiData> callback = new Callback<MeizhiData>() {
        @Override
        public void onResponse(Call<MeizhiData> call, Response<MeizhiData> response) {

            mData = response.body().results;
            adapter.mData = mData;
            adapter.notifyDataSetChanged();
            Log.e("Lee", mData.size() + "====");
        }

        @Override
        public void onFailure(Call<MeizhiData> call, Throwable t) {

        }
    };

}
