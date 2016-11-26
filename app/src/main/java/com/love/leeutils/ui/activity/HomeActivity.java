package com.love.leeutils.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.love.leeutils.R;
import com.love.leeutils.base.BaseActivity;
import com.love.leeutils.modle.MeizhiData;
import com.love.leeutils.network.ApiServiceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    TextView tv;

    @Override
    public int findContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Call<MeizhiData> call = ApiServiceManager.getInstance().getMeizhiApi().getMeizhi(1);
        call.enqueue(callback);
    }

    @Override
    public void findView() {
        tv = (TextView) findViewById(R.id.tv);
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
        }

        @Override
        public void onFailure(Call<MeizhiData> call, Throwable t) {
            tv.setText("网路失败");
        }
    };


}


