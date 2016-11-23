package com.love.leeutils.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.love.leeutils.R;

/**
 * 底部弹窗基类
 */
public abstract class BaseBottomSheet {

    protected BottomSheetDialog mSheetDialog;
    protected Context mContext;

    protected BaseBottomSheet(Context context) {
        mContext  =context;
        View view = rootView();
        Binding(view);
        mSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetDialogStyle);
        mSheetDialog.setContentView(view);
        mSheetDialog.setCanceledOnTouchOutside(true);
        mSheetDialog.setCancelable(true);
    }

    abstract protected @LayoutRes
    int LayoutResId();

    protected View rootView(){
        return LayoutInflater.from(mContext).inflate(LayoutResId(),null);
    }

    abstract public void Binding(View view);

     public void show(){
         mSheetDialog.show();
     }

    public void dismiss(){
        mSheetDialog.dismiss();
    }

}
