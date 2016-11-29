package com.love.leeutils.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.love.leeutils.R;
import com.love.leeutils.base.BaseActivity;
import com.love.leeutils.modle.MeizhiData;
import com.love.leeutils.network.ApiServiceManager;
import com.love.leeutils.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.image)
    ImageView imageView;

    @Override
    public int layoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        Call<MeizhiData> call = ApiServiceManager.getInstance().getMeizhiApi().getMeizhi(1);
        call.enqueue(callback);

    }

    @Override
    public void setListener4View() {

    }

    private Callback<MeizhiData> callback = new Callback<MeizhiData>() {
        @Override
        public void onResponse(Call<MeizhiData> call, Response<MeizhiData> response) {
            MeizhiData meizhiData = response.body();
            if (!meizhiData.error) {
                tv.setText(meizhiData.results.get(0).getUrl());
            } else {
                tv.setText("没有数据");
            }

            ImageUtils.imageLoader(HomeActivity.this, imageView, meizhiData.results.get(0).getUrl());

        }

        @Override
        public void onFailure(Call<MeizhiData> call, Throwable t) {
            tv.setText("网路失败");
        }
    };


}


