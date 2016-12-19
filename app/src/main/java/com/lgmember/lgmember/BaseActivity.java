package com.lgmember.lgmember;


import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends Activity {

	protected Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
	}
	
	public void showToast(String string ) {
		Toast.makeText(BaseActivity.this, string, Toast.LENGTH_SHORT).show();
	}
	public void startIntent(Class activity) {
		Intent intent = new Intent(BaseActivity.this,activity);
        startActivity(intent);	
	}
	public String getText(TextView v) {
		return v.getText().toString().trim();
	}
	
}
