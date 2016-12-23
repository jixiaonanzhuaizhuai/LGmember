package com.lgmember.lgmember;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ActivityDetailActivity extends BaseActivity {

	private TextView myTextView;
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activitydetail);

		myTextView = (TextView) findViewById(R.id.textView1);
		imageView = (ImageView)findViewById(R.id.imageView1);

		//使用Intent对象得到FirstActivity传递来的参数
		Intent intent = getIntent();



		String position = intent.getStringExtra("position");
		int picture =  Integer.valueOf(position);


		int a[] = {R.mipmap.image0,R.mipmap.image1,R.mipmap.image2,R.mipmap.image3};

		for(int i=0;i<a.length;i++){

			if (i == picture){
				imageView.setImageResource(a[i]);
			}

		}

		myTextView.setText("这是第"+position+"图片");

	}

}
