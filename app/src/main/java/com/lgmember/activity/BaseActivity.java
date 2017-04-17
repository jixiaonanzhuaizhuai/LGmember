package com.lgmember.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.lgmember.util.StringUtil;

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

	public  void startIntent(Class clazz) {
		Intent intent = new Intent(BaseActivity.this,clazz);
		startActivity(intent);
	}
	public String getText(TextView v) {
		return v.getText().toString().trim();
	}


	public void onArgumentEmpty(String string) {
		showToast(string);

	}

	public void onArgumentFormatError(String string) {
		showToast(string);
	}

	public void onError(int code) {

		showToast(context.getString(StringUtil.codeTomsg(code)));

	}

	public void onNetworkDisconnect() {
		showToast(context.getString(R.string.http_network_disconnect));
	}

	public void onFailed(int code, String msg) {
		showToast(context.getString(R.string.server_error));
	}
	
}
