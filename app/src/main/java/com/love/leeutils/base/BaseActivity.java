package com.love.leeutils.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.love.leeutils.exception.AppManager;

import butterknife.ButterKnife;

/**
 *
 */
public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {

    @LayoutRes
    public abstract int layoutId();

    public abstract void setListener4View();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setContentView(layoutId());
        findView();
        setListener4View();

    }


    public void findView() {
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {

    }

    protected void cleanCache() {
        AppManager.getAppManager().finishAllActivity();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
