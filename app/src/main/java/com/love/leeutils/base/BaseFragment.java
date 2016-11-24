package com.love.leeutils.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lixinxin on 2016/11/24.
 * 邮箱 895330766@qq.com
 */

public abstract class BaseFragment extends Fragment {

    public abstract int findContentView();

    public abstract void findView();

    public abstract void setListener4View();

    public abstract void _onCreateView();

    public View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Fragment有一个非常强大的功能——就是可以在Activity重新创建时可以不完全销毁Fragment，以便Fragment可以恢复。
         * 在onCreate()方法中调用setRetainInstance(true/false)方法是最佳位置。 当在onCreate()
         * 方法中调用了setRetainInstance(true)后，Fragment恢复时会跳过onCreate()和onDestroy()方法
         * ，因此不能在onCreate()中放置一些初始化逻辑，切忌！
         */
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(findContentView(), null);

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        findView();
        setListener4View();
        _onCreateView();

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
