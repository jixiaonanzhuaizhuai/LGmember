package com.lgmember.lgmember;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements OnClickListener, OnItemClickListener {

	//private EditText etLoginName;
	//private EditText etLoginPass;
	private Button menuBtn,messageBtn,moreInfo,loginBtn,regBtn,visitorBtn;
	//private String loginName;
	//private String loginPass;
	private ConvenientBanner convenientBanner;//顶部广告栏控件
	private ArrayList<Integer> localImages = new ArrayList<Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		init();
	}

	private void init() {
		convenientBanner.setPages(
				new CBViewHolderCreator<LocalImageHolderView>() {

					public LocalImageHolderView createHolder() {
						return new LocalImageHolderView();
					}
				}, localImages)
				//设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
				.setOnItemClickListener(this);

//        convenientBanner.setManualPageable(false);//设置不能手动影响

	}

	private void initView() {
		//etLoginName = (EditText) findViewById(R.id.et_loginName);
		//etLoginPass = (EditText) findViewById(R.id.et_loginPass);
		menuBtn = (Button) findViewById(R.id.menuBtn);
		messageBtn = (Button) findViewById(R.id.messageBtn);
		moreInfo = (Button) findViewById(R.id.moreInfo);convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
		menuBtn.setOnClickListener(this);
		messageBtn.setOnClickListener(this);
		moreInfo.setOnClickListener(this);
		localImages.add(R.mipmap.image1);
		localImages.add(R.mipmap.image2);
		localImages.add(R.mipmap.image3);
		localImages.add(R.mipmap.image4);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menuBtn:
			showPopupMenu(v);
			break;
		case R.id.messageBtn:
			showToast("消息");
			break;
		case R.id.moreInfo:
            startIntent(PersonalActivity.class);
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
						startIntent(MyScoresActivity.class);
						return true;
					case R.id.exchange:
						startIntent(ExchangeScoresActivity.class);
						return true;
					case R.id.card:
						startIntent(MyCardActivity.class);
						return true;
					case R.id.sign:
						startIntent(SignActivity.class);
						return true;
					case R.id.messages:
						startIntent(MyMessageActivity.class);
						return true;
                    
                }  
                return false;  
            }  
        });  
  
        popup.show();  
    }
	// 开始自动翻页
	@Override
	protected void onResume() {
		super.onResume();
		//开始自动翻页
		convenientBanner.startTurning(3000);
	}

	// 停止自动翻页
	@Override
	protected void onPause() {
		super.onPause();
		//停止翻页
		convenientBanner.stopTurning();
	}

	@Override
	public void onItemClick(int position) {
		Toast.makeText(this,"点击了第"+position+"个", Toast.LENGTH_SHORT).show();
	}
}
