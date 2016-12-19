package com.lgmember.lgmember;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;

public class MainActivity extends BaseActivity implements OnClickListener{

	//private EditText etLoginName;
	//private EditText etLoginPass;
	private Button menuBtn,messageBtn,moreInfo,loginBtn,regBtn,visitorBtn;
	//private String loginName;
	//private String loginPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}

	private void initView() {
		//etLoginName = (EditText) findViewById(R.id.et_loginName);
		//etLoginPass = (EditText) findViewById(R.id.et_loginPass);
		menuBtn = (Button) findViewById(R.id.menuBtn);
		messageBtn = (Button) findViewById(R.id.messageBtn);
		moreInfo = (Button) findViewById(R.id.moreInfo);
		menuBtn.setOnClickListener(this);
		messageBtn.setOnClickListener(this);
		moreInfo.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menuBtn:
			showPopupMenu(v);
			break;
		case R.id.messageBtn:
			showToast("������Ϣ");
			break;
		case R.id.moreInfo:
			Intent intent = new Intent(MainActivity.this,PersonalActivity.class);
            startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	private void showPopupMenu(View view) {  
		  
        PopupMenu popup = new PopupMenu(MainActivity.this, view, Gravity.CENTER_HORIZONTAL);  
  
        popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());  
  
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
            @Override  
            public boolean onMenuItemClick(MenuItem menuItem) {  
                switch (menuItem.getItemId()) {  
                    case R.id.personal:  
                        startIntent(PersonalActivity.class);
                        return true;  
                    case R.id.activity:  
                    	startIntent(ActivityManage.class);
                        return true;  
                    case R.id.scores:  
                    	startIntent(ActivityManage.class);
                        return true; 
                    
                }  
                return false;  
            }  
        });  
  
        popup.show();  
    } 

	
}
