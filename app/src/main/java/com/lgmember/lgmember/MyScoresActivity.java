package com.lgmember.lgmember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MyScoresActivity extends BaseActivity implements OnClickListener {
	
	private Button historyScoresBtn,scoresRuleBtn;
	//private String loginName;
	//private String loginPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myscores);
		initView();
	}

	private void initView() {
		//etLoginName = (EditText) findViewById(R.id.et_loginName);
		//etLoginPass = (EditText) findViewById(R.id.et_loginPass);
		
		historyScoresBtn = (Button) findViewById(R.id.historyScoresBtn);
		scoresRuleBtn = (Button) findViewById(R.id.regBtn);
		
		historyScoresBtn.setOnClickListener(this);
		historyScoresBtn.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.historyScoresBtn:
			showToast("历史积分");
			break;
		case R.id.regBtn:
			showToast("按钮");
			break;
		default:
			break;
		}
	}
	
	

}
