package com.lgmember.lgmember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class RegisterActivity extends BaseActivity implements OnClickListener {

	private Button regBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
		showToast("会员俱乐部采用实名会员管理，请确认信息与身份证信息相同，注册成功后，可凭本人身份证领取实体会员卡");
	}

	private void initView() {
		regBtn = (Button)findViewById(R.id.regBtn);
		regBtn.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.regBtn:
				startIntent(MainActivity.class);
				break;
		}

	}
	
	

}
