package com.lgmember.lgmember;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.Toast;


	public class MyScoresActivity extends TabActivity implements View.OnClickListener {

		TabHost tabHost;
		private RadioButton rb_tab_historyscores, rb_tab_scoresrule;

		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_myscores);
			init();
			initTab();

		}
		/**
		 * 初始化数据
		 */
		private void init() {
			rb_tab_historyscores = (RadioButton) findViewById(R.id.rb_tab_historyscores);
			rb_tab_scoresrule = (RadioButton) findViewById(R.id.rb_tab_scoresrule);
			rb_tab_historyscores.setOnClickListener(this);
			rb_tab_scoresrule.setOnClickListener(this);
		}
		/**
		 * 初始化Tab
		 */
		private void initTab() {
			tabHost = getTabHost();
			tabHost.addTab(tabHost.newTabSpec("history").setIndicator("history")
					.setContent(new Intent(this, HistoryScores.class)));

			tabHost.addTab(tabHost.newTabSpec("rule").setIndicator("rule")
					.setContent(new Intent(this, ScoresRuleActicity.class)));
		}

		/**
		 * 点击事件
		 *
		 * @param v
		 */
		@Override
		public void onClick(View v) {
			switch (v.getId()) {

				case R.id.rb_tab_historyscores:
					tabHost.setCurrentTabByTag("history");
					break;
				case R.id.rb_tab_scoresrule:
					tabHost.setCurrentTabByTag("rule");
					break;

				default:
					break;
			}

		}
	}