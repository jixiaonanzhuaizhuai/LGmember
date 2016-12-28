package com.lgmember.lgmember;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonalActivity extends BaseActivity implements View.OnClickListener {

	private Button editbtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		initView();
		
	}

	private void initView() {
		editbtn = (Button) findViewById(R.id.editbtn);
		editbtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.editbtn:
				startIntent(EditPersonalActivity.class);
				break;
			default:
				break;
		}
	}

}
