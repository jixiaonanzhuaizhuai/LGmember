package com.lgmember.activity.project;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.activity.sign.SignListActivity;
import com.lgmember.business.collection.CollectionAddBusiness;
import com.lgmember.business.project.JoinActivityBusiness;
import com.lgmember.business.project.ProjectMessageDetailBusiness;
import com.lgmember.model.ProjectMessage;
import com.lgmember.util.StringUtil;
import com.lgmember.view.RightPopupMenu;
import com.lgmember.view.TopBarView;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import org.json.JSONObject;


public class ProjectMessageDetailActivity extends BaseActivity implements View.OnClickListener,
		JoinActivityBusiness.JoinActivityResulHandler,CollectionAddBusiness.CollectionResulHandler,TopBarView.onTitleBarClickListener {

	private TextView tv_title,tv_content,tv_hyperlink,tv_project_id,
			tv_create_time,tv_count,tv_checkin_end_time,popup_collection;
	private ImageView imageView;
	private Button shareBtn,joinBtn;
	private int projectMessage_id;
	private ProjectMessage projectMessage;
	private TopBarView topBar;
	private RightPopupMenu rightPopupMenu;
	private boolean is_collection ;
	private View popView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activitydetail);

		Bundle bundle = this.getIntent().getExtras();
		 projectMessage = (ProjectMessage)bundle.getSerializable("projectMessage");

		init();

	}

	private void addCollection() {
		CollectionAddBusiness collectionAddBusiness = new CollectionAddBusiness(context,projectMessage_id);
		collectionAddBusiness.setHandler(this);
		collectionAddBusiness.addCollection();

	}
	private void deleteCollection() {
		CollectionAddBusiness collectionAddBusiness = new CollectionAddBusiness(context,projectMessage_id);
		collectionAddBusiness.setHandler(this);
		collectionAddBusiness.deleteCollection();
	}


	private void init(){
		topBar = (TopBarView)findViewById(R.id.topbar);
		topBar.setClickListener(this);


		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_content = (TextView) findViewById(R.id.tv_content);
		tv_hyperlink = (TextView)findViewById(R.id.tv_hyperlink);
		//tv_project_id = (TextView)findViewById(R.id.tv_project_id);
		tv_create_time = (TextView)findViewById(R.id.tv_create_time);
		tv_count = (TextView)findViewById(R.id.tv_count);
		tv_checkin_end_time = (TextView)findViewById(R.id.tv_checkin_end_time);

		imageView = (ImageView)findViewById(R.id.imageView);
		joinBtn = (Button)findViewById(R.id.joinBtn);
		joinBtn.setOnClickListener(this);

		projectMessage_id = projectMessage.getId();

		joinBtn.setText(StringUtil.numToJoinState(projectMessage.getState()));

		tv_title.setText(projectMessage.getTitle());
		tv_content.setText(Html.fromHtml(projectMessage.getContent()));
		tv_hyperlink.setText(projectMessage.getHyperlink());
		tv_hyperlink.setOnClickListener(this);
		tv_create_time.setText(projectMessage.getStart_time());
		tv_count.setText(""+projectMessage.getCount());
		tv_checkin_end_time.setText(projectMessage.getEnd_time());
		is_collection = projectMessage.getSaved();
		rightPopupMenu = new
				RightPopupMenu(this,R.layout.popup_menu_projecr_message_detail_more,is_collection);
		imageView.setBackground(new BitmapDrawable(context.getResources(),StringUtil.base64ToBitmap(projectMessage.getPicture())));

		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		popView = inflater.inflate(R.layout.popup_menu_projecr_message_detail_more, null);// 加载菜单布局文件

		popup_collection = (TextView)popView.findViewById(R.id.txt_collection);

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.tv_hyperlink:
				Uri uri = Uri.parse("https://www.baidu.com/");
				Intent intent = new Intent(Intent.ACTION_VIEW,uri);
				startActivity(intent);
				break;
			case R.id.joinBtn:
				joinActivity();
				break;
		}
	}

	public void joinActivity(){
		JoinActivityBusiness joinActivityBusiness = new JoinActivityBusiness(context,projectMessage_id);
		joinActivityBusiness.setHandler(this);
		joinActivityBusiness.join();
	}

	//一键分享
	public void shareText(String packageName, String className, String content, String title, String subject){
		Intent intent =new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("text/plain");
		if(stringCheck(className) && stringCheck(packageName)){
			ComponentName componentName = new ComponentName(packageName, className);
			intent.setComponent(componentName);
		}else if(stringCheck(packageName)){
			intent.setPackage(packageName);
		}

		intent.putExtra(Intent.EXTRA_TEXT, content);
		if(null != title && !TextUtils.isEmpty(title)){
			intent.putExtra(Intent.EXTRA_TITLE, title);
		}
		if(null != subject && !TextUtils.isEmpty(subject)){
			intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		}
		intent.putExtra(Intent.EXTRA_TITLE, title);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Intent chooserIntent = Intent.createChooser(intent, "分享到：");
		startActivity(chooserIntent);
	}

	public static boolean stringCheck(String str){
		if(null != str && !TextUtils.isEmpty(str)){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void onSuccess(JSONObject jsob) {
		showToast(jsob.toString());
	}



	@Override
	public void onCollectionSuccess(String str) {
		showToast(str);
	}

	@Override
	public void onBackClick() {
		finish();
	}

	@Override
	public void onRightClick() {

		rightPopupMenu.showLocation(R.id.right_txt);//设置弹出菜单弹出的位置

		rightPopupMenu.setOnItemClickListener(new RightPopupMenu.OnItemClickListener() {
			@Override
			public void onClick(RightPopupMenu.MENUITEM item) {
				String ITEM = item.toString();
				if (ITEM.equals("ITEM1")){
					//收藏
					if (is_collection){
						deleteCollection();
						is_collection = false;
						popup_collection.setText("点击收藏");
					}else {
						addCollection();
						is_collection = true;
						popup_collection.setText("取消收藏");
					}
				}else{
					//一键分享
					shareText(null, null, "这是一个有趣的活动：https://www.baidu.com/", "分享标题", "分享主题");
				}
			}
		});


	}
}
