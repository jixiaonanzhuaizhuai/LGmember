package com.lgmember.lgmember;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TabHost;


public class ExchangeScoresActivity extends TabActivity implements View.OnClickListener {

	TabHost tabHost;
	private RadioButton rb_tab_exchangeall, rb_tab_exchangeselect;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exchangescores);
		init();
		initTab();

	}

	/**
	 * 初始化数据
	 */
	private void init() {
		rb_tab_exchangeall = (RadioButton) findViewById(R.id.rb_tab_exchangeall);
		rb_tab_exchangeselect = (RadioButton) findViewById(R.id.rb_tab_exchangeselect);
		rb_tab_exchangeall.setOnClickListener(this);
		rb_tab_exchangeselect.setOnClickListener(this);
	}

	/**
	 * 初始化Tab
	 */
	private void initTab() {
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("all").setIndicator("all")
				.setContent(new Intent(this, ExchangeAllActivity.class)));

		tabHost.addTab(tabHost.newTabSpec("select").setIndicator("select")
				.setContent(new Intent(this, ExchangeSelectActivity.class)));

	}

	/**
	 * 点击事件
	 *
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

			case R.id.rb_tab_exchangeall:
				tabHost.setCurrentTabByTag("all");
				break;
			case R.id.rb_tab_exchangeselect:
				tabHost.setCurrentTabByTag("select");
				break;

			default:
				break;
		}

	}
	
	

}
