package com.love.leeutils.exception;

import android.content.Context;
import android.content.Intent;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

	private static CrashHandler instance;

	private Context mContext;

	public static CrashHandler getInstance() {
		if (instance == null) {
			instance = new CrashHandler();
		}
		return instance;
	}

	public void init(Context ctx) {
		this.mContext = ctx;
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread arg0, Throwable arg1) {
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(startMain);
		AppManager.getAppManager().AppExit(mContext);
	}

}
