package com.love.leeutils.appwidget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.love.leeutils.R;

/**
 * Created by lixinxin on 2016/11/13.
 * 进度框
 */

public class LeeProgressLoading extends Dialog {

    private Context mContext;

    private static LeeProgressLoading dialog;

    public LeeProgressLoading(Context context) {
        super(context);
        this.mContext = context;
    }

    public LeeProgressLoading(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    /**
     * 给Dialog设置提示信息
     *
     * @param message
     */
    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.message).setVisibility(View.VISIBLE);
            TextView txt = (TextView) findViewById(R.id.message);
            txt.setText(message);
            txt.invalidate();
        }
    }

    /**
     * 弹出自定义ProgressDialog
     *
     * @param context        上下文
     * @param message        提示
     * @param cancelable     是否按返回键取消
     * @param cancelListener 按下返回键监听
     * @return
     */
    public static LeeProgressLoading show(Activity context, CharSequence message, boolean cancelable, OnCancelListener cancelListener) {
        dialog = new LeeProgressLoading(context, R.style.LeeProgress);
        dialog.setTitle("");
        dialog.setContentView(R.layout.progress_loading);
        if (message == null || message.length() == 0) {
            dialog.findViewById(R.id.message).setVisibility(View.GONE);
        } else {
            TextView txt = (TextView) dialog.findViewById(R.id.message);
            txt.setText(message);
        }
        // 按返回键是否取消
        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(false);
        // 监听返回键处理
        dialog.setOnCancelListener(cancelListener);
        // 设置居中
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        // 设置背景层透明度
        lp.dimAmount = 0.2f;
        dialog.getWindow().setAttributes(lp);
        if (!context.isFinishing())
            dialog.show();
        return dialog;
    }

    public static void hideDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

}
