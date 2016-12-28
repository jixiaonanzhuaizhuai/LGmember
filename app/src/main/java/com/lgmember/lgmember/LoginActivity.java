package com.lgmember.lgmember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends BaseActivity implements OnClickListener {
	
	private Button loginBtn;
	private TextView registerTxt,forgetPassTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		loginBtn = (Button) findViewById(R.id.loginBtn);
		registerTxt = (TextView)findViewById(R.id.registerTxt);
		forgetPassTxt = (TextView)findViewById(R.id.forgetPassTxt);
		loginBtn.setOnClickListener(this);
		registerTxt.setOnClickListener(this);
		forgetPassTxt.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginBtn:
			startIntent(MainActivity.class);
			break;
		case R.id.registerTxt:
			startIntent(RegisterActivity.class);
			break;
			case R.id.forgetPassTxt:
				showToast("我忘记密码了了了！！！");
		default:
			break;
		}
	}
	
	

}
