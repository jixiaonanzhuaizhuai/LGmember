package com.lgmember.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class WelcomActivity extends BaseActivity implements OnClickListener {
	
	private Button loginBtn,regBtn,visitorBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcom);
		initView();
	}

	private void initView() {
		loginBtn = (Button) findViewById(R.id.loginBtn);
		regBtn = (Button) findViewById(R.id.regBtn);
		visitorBtn = (Button) findViewById(R.id.visitorBtn);
		loginBtn.setOnClickListener(this);
		regBtn.setOnClickListener(this);
		visitorBtn.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginBtn:
			startIntent(LoginActivity.class);
			break;
		case R.id.regBtn:
			startIntent(RegisterActivity.class);
			break;
		case R.id.visitorBtn:
			startIntent(MainActivity.class);
			break;
		default:
			break;
		}
	}
	
	

}
