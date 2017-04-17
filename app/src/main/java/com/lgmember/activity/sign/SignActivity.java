package com.lgmember.activity.sign;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;

import com.lgmember.activity.R;
import com.lgmember.activity.message.MemberMessageActivity;
import com.lgmember.activity.message.MyMessageActivity;
import com.lgmember.activity.message.SystemMessageActivity;
import com.lgmember.view.TopBarView;


public class SignActivity extends FragmentActivity  {

	private TopBarView topBar;

	// 定义FragmentTabHost对象
	private FragmentTabHost mTabHost;

	// 定义一个布局
	private LayoutInflater layoutInflater;

	// 定义数组来存放Fragment界面
	private Class fragmentArray[] = {
			ActivitySignActivity.class, SignListActivity.class};

	// 定义数组来存放按钮图片
	private int mImageViewArray[] = {
			R.drawable.manage_tab_item_alreadyjoin,
			R.drawable.manage_tab_item_soonjoin};

	// Tab选项卡的文字
	private String mTextviewArray[] = { "活动签到", "节目签到"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign);
		initView();
	}

	/**
	 * 初始化组件
	 */
	private void initView() {

		// 实例化布局对象
		layoutInflater = LayoutInflater.from(this);

		// 实例化TabHost对象，得到TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(SignActivity.this, getSupportFragmentManager(),
				R.id.realtabcontent);

		// 得到fragment的个数
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
					.setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			// 设置Tab按钮的背景
			mTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(R.drawable.manage_tab_item_bg);
		}
	}

	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.manage_tab_item_view, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextviewArray[index]);

		return view;
	}


	/*TabHost tabHost;
	private RadioButton rb_tab_activitysign, rb_tab_programsign;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign);
		init();
		initTab();
	}

	*//**
	 * 初始化数据
	 *//*
	private void init() {
		rb_tab_activitysign = (RadioButton) findViewById(R.id.rb_tab_activitysign);
		rb_tab_programsign = (RadioButton) findViewById(R.id.rb_tab_programsign);
		rb_tab_activitysign.setOnClickListener(this);
		rb_tab_programsign.setOnClickListener(this);
	}

	*//**
	 * 初始化Tab
	 *//*
	private void initTab() {
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("all").setIndicator("all")
				.setContent(new Intent(this, ActivitySignActivity.class)));

		tabHost.addTab(tabHost.newTabSpec("select").setIndicator("select")
				.setContent(new Intent(this, SignListActivity.class)));
	}

	*//**
	 * 点击事件
	 *
	 * @param v
	 *//*
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

	}*/

}
