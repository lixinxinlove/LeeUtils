package com.love.leeutils.base;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.love.leeutils.exception.AppManager;

/**
 *
 */
public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {

	public abstract int findContentView();

	public abstract void findView();

	public abstract void setListener4View();

	protected TextView headTitle;

	private ImageView back;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
		setContentView(findContentView());
		findView();
		setListener4View();
		setHeadTitle();
		findBack();
	}

	private void findBack() {

	}

	private void setHeadTitle() {
	}

	public String getHeadTitle() {
		return "";
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 1:
			finish();
			break;

		default:
			break;
		}
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
