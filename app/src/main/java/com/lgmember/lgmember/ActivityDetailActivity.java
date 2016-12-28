package com.lgmember.lgmember;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class ActivityDetailActivity extends BaseActivity {

	private TextView title,content;
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activitydetail);

		title = (TextView) findViewById(R.id.title);
		content = (TextView) findViewById(R.id.content);
		imageView = (ImageView)findViewById(R.id.imageView1);

		//使用Intent对象得到FirstActivity传递来的参数
		Intent intent = getIntent();

		String position = intent.getStringExtra("position");
		int picture =  Integer.valueOf(position);

		int a[] = {R.mipmap.image0,R.mipmap.image1,R.mipmap.image2,R.mipmap.image3,
				R.mipmap.image4,R.mipmap.image5,R.mipmap.image6,R.mipmap.image7};

		for(int i=0;i<a.length;i++){
			if (i == picture){
				imageView.setImageResource(a[i]);
			}
		}
			title.setText("标题：这是活动详情页面！！");
			content.setText("活动内容：我是一次盛大的活动，盛大的活动，我是一次盛大的活动，" +
					"盛大的活动，我是一次盛大的活动，盛大的活动，我是一次盛大的活动，盛大的活动");

	}

}
