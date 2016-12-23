package com.lgmember.lgmember;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;


public class SignActivity extends TabActivity implements View.OnClickListener {

	TabHost tabHost;
	private RadioButton rb_tab_activitysign, rb_tab_programsign;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign);
		init();
		initTab();

	}

	/**
	 * 初始化数据
	 */
	private void init() {
		rb_tab_activitysign = (RadioButton) findViewById(R.id.rb_tab_activitysign);
		rb_tab_programsign = (RadioButton) findViewById(R.id.rb_tab_programsign);
		rb_tab_activitysign.setOnClickListener(this);
		rb_tab_programsign.setOnClickListener(this);
	}

	/**
	 * 初始化Tab
	 */
	private void initTab() {
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("all").setIndicator("all")
				.setContent(new Intent(this, ActivitySignActivity.class)));

		tabHost.addTab(tabHost.newTabSpec("select").setIndicator("select")
				.setContent(new Intent(this, ProgramSignActivity.class)));

	}

	/**
	 * 点击事件
	 *
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

			case R.id.rb_tab_activitysign:
				tabHost.setCurrentTabByTag("all");
				break;
			case R.id.rb_tab_programsign:
				tabHost.setCurrentTabByTag("select");
				break;

			default:
				break;
		}

	}

}
