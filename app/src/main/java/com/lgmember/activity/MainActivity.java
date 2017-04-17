package com.lgmember.activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.lgmember.activity.card.MyCardActivity;
import com.lgmember.activity.collection.CollectionActivity;
import com.lgmember.activity.feedback.ReportProblemActivity;
import com.lgmember.activity.message.MyMessageActivity;
import com.lgmember.activity.person.EditPersonalActivity;
import com.lgmember.activity.person.PersonalAllActivity;
import com.lgmember.activity.project.ProjectMessageDetailActivity;
import com.lgmember.activity.project.ProjectMessageManageActivity;
import com.lgmember.activity.project.FragmentActivityList;
import com.lgmember.activity.score.ExchangeScoresActivity;
import com.lgmember.activity.score.MyScoresActivity;
import com.lgmember.activity.setting.SettingActivity;
import com.lgmember.activity.sign.SignActivity;
import com.lgmember.activity.sign.SignListActivity;
import com.lgmember.bean.ProjectMessageBean;
import com.lgmember.business.ShowNetworkImgBusiness;
import com.lgmember.business.message.MemberMessageBusiness;
import com.lgmember.business.project.ProjectMessageListBusiness;
import com.lgmember.model.Member;
import com.lgmember.model.ProjectMessage;
import com.lgmember.util.StringUtil;
import com.maiml.library.BaseItemLayout;
import com.maiml.library.config.ConfigAttrs;
import com.maiml.library.config.Mode;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements OnClickListener,
		ProjectMessageListBusiness.ProjectMessageListResultHandler,
		MemberMessageBusiness.MemberMessageResulHandler,ShowNetworkImgBusiness.ShowNetworkImgResulHandler{


	private Button menuBtn,messageBtn,signBtn,moreInfo;
    private TextView sexTxt,ageTxt,nationTxt,birthdayTxt,editInfo,moreactivity,rmoreactivity;
	private TextView tv_name,tv_card_no,tv_point,tv_level,tv_gender,tv_age,tv_nation,tv_birthday;
	private ImageView iv_photo;

	private ConvenientBanner soonJoinBanner,recommendBanner;//轮播控件
	private ArrayList<String> immediatelyImages = new ArrayList<String>();
	private ArrayList<String> recommendImages = new ArrayList<String>();

	private BaseItemLayout layout;
    private boolean isButton = true;

	private String gender,nation;
	private String birthday;
	private int age;

	private int pageNo = 1;
	private int pageSize = 10;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			byte[] photo = msg.getData().getByteArray("photo");
			final Bitmap bitmap = BitmapFactory.decodeByteArray(photo,0,photo.length);
			iv_photo.setImageBitmap(bitmap);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		getMemberMsg();
		getProjectMessage();

	}

	private void getMemberMsg() {

		MemberMessageBusiness memberMessage = new MemberMessageBusiness(context);
		memberMessage.setHandler(this);
		memberMessage.getMemberMessage();
	}

	private void getProjectMessage() {
		int tag = 0;
		ProjectMessageListBusiness projectMessageListBusiness = new ProjectMessageListBusiness(context, pageNo, pageSize,tag);
		projectMessageListBusiness.setHandler(this);
		projectMessageListBusiness.getSoonJoinList();

	}

	private void initView() {

		iv_photo = (ImageView)findViewById(R.id.touxiang);

		tv_name = (TextView)findViewById(R.id.tv_name);
		tv_card_no = (TextView)findViewById(R.id.tv_card_no);
		tv_point = (TextView)findViewById(R.id.tv_point);
		tv_level = (TextView)findViewById(R.id.tv_level);
		tv_gender = (TextView)findViewById(R.id.tv_gender);
		tv_age = (TextView)findViewById(R.id.tv_age);
		tv_nation = (TextView)findViewById(R.id.tv_nation);
		tv_birthday = (TextView)findViewById(R.id.tv_birthday);

		menuBtn = (Button) findViewById(R.id.menuBtn);
		messageBtn = (Button) findViewById(R.id.messageBtn);
		signBtn = (Button) findViewById(R.id.signBtn);
		moreInfo = (Button) findViewById(R.id.moreInfo);
        sexTxt = (TextView)findViewById(R.id.sexTxt);
        ageTxt = (TextView)findViewById(R.id.ageTxt);
        nationTxt = (TextView)findViewById(R.id.nationTxt);
        birthdayTxt = (TextView)findViewById(R.id.birthdayTxt);
		editInfo = (TextView)findViewById(R.id.editInfo);
		moreactivity = (TextView)findViewById(R.id.moreactivity);
		rmoreactivity = (TextView)findViewById(R.id.rmoreactivity);
		soonJoinBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
		recommendBanner = (ConvenientBanner) findViewById(R.id.recommendBanner);
		layout = (BaseItemLayout) findViewById(R.id.baseLayout);
		menuBtn.setOnClickListener(this);
		messageBtn.setOnClickListener(this);
		signBtn.setOnClickListener(this);
		editInfo.setOnClickListener(this);
		moreInfo.setOnClickListener(this);
		moreactivity.setOnClickListener(this);
		rmoreactivity.setOnClickListener(this);

		/*
		listview数据
		 */
		List<String> valueList = new ArrayList<>();
		valueList.add("汽车消费类活动");
		valueList.add("参观类活动");
		valueList.add("节日类活动");
		valueList.add("表演类活动");
		valueList.add("旅游类活动");
		valueList.add("收听类活动");
//每条活动前对应图片,数量和上面保持一致
		List<Integer> resIdList = new ArrayList<>();
		resIdList.add(R.mipmap.qiche);
		resIdList.add(R.mipmap.canguan);
		resIdList.add(R.mipmap.jieri);
		resIdList.add(R.mipmap.biaoyan);
		resIdList.add(R.mipmap.qiche);
		resIdList.add(R.mipmap.canguan);

		ConfigAttrs attrs  =new ConfigAttrs();//把全部参数的配置，委托给 ConfigAttrs 类处理。
		//参数 使用链式方式配置
		attrs.setValueList(valueList)//文字list
				.setItemMarginTop(10)//设置 全部item的间距
					.setResIdList(resIdList)//图片list
						.setIconWidth(24)//图片宽度和高度
							.setIconHeight(24)
								.setItemMode(Mode.NORMAL);
		layout.setConfigAttrs(attrs).create();

	}
	private void initBanner() {

		soonJoinBanner.setPages(
				new CBViewHolderCreator<LocalImageHolderView>() {
					public LocalImageHolderView createHolder() {
						return new LocalImageHolderView();
					}
				}, immediatelyImages);
		soonJoinBanner.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(int position) {

				Intent intent = new
						Intent(MainActivity.this,ProjectMessageDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("projectMessage",
						projectMessageList.get(position));

				intent.putExtras(bundle);
				startActivity(intent);

			}
		});

		//soonJoinBanner.setManualPageable(false);//设置不能手动影响

		recommendBanner.setPages(
				new CBViewHolderCreator<LocalImageHolderView>() {
					public LocalImageHolderView createHolder() {
						return new LocalImageHolderView();
					}
				}, recommendImages);
		recommendBanner.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(int position) {

				Intent intent = new Intent(MainActivity.this,ProjectMessageDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("projectMessage",projectMessageList.get(position));

				intent.putExtras(bundle);
				startActivity(intent);

			}
		});
		//BaseItemLayout点击事件
		layout.setOnBaseItemClick(new BaseItemLayout.OnBaseItemClick() {
			@Override
			public void onItemClick(int position) {
				switch (position){
					case 0:
						Intent intent = new
								Intent(MainActivity.this,ProjectMessageManageActivity.class);
						intent.putExtra("tab_id",3);
						startActivity(intent);
						break;
					case 1:
						Intent intent1 = new
								Intent(MainActivity.this,ProjectMessageManageActivity.class);
						intent1.putExtra("tab_id",3);
						startActivity(intent1);
						break;
					case 2:
						startIntent(FragmentActivityList.class);
						break;
					case 3:
						Intent intent2 = new
								Intent(MainActivity.this,ProjectMessageManageActivity.class);
						intent2.putExtra("tab_id",3);
						startActivity(intent2);
						break;
					case 4:
						Intent intent3 = new
								Intent(MainActivity.this,ProjectMessageManageActivity.class);
						intent3.putExtra("tab_id",3);
						startActivity(intent3);
						break;
					case 5:
						Intent intent4 = new
								Intent(MainActivity.this,ProjectMessageManageActivity.class);
						intent4.putExtra("tab_id",3);
						startActivity(intent4);
						break;

				}
			}
		});
	}
	//这个是菜单、信息、签到、编辑、更多信息点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menuBtn:
			showPopupMenu(v);
			break;
		case R.id.messageBtn:
			startIntent(MyMessageActivity.class);
			break;
		case R.id.signBtn:
			startIntent(SignActivity.class);
			break;
		case R.id.editInfo:
			startIntent(EditPersonalActivity.class);
			break;
		case R.id.moreactivity:
			Intent intent = new
					Intent(MainActivity.this,ProjectMessageManageActivity.class);
			intent.putExtra("tab_id",1);
			startActivity(intent);
			break;
		case R.id.rmoreactivity:
			Intent intent1 = new
					Intent(MainActivity.this,ProjectMessageManageActivity.class);
			intent1.putExtra("tab_id",2);
			startActivity(intent1);
			break;
	case R.id.moreInfo:
            if(isButton){
                sexTxt.setVisibility(View.VISIBLE);
				tv_gender.setVisibility(View.VISIBLE);
				tv_gender.setText(gender);
                ageTxt.setVisibility(View.VISIBLE);
				tv_age.setVisibility(View.VISIBLE);
				tv_age.setText(age+"");
                nationTxt.setVisibility(View.VISIBLE);
				tv_nation.setVisibility(View.VISIBLE);
				tv_nation.setText(nation);
                birthdayTxt.setVisibility(View.VISIBLE);
				tv_birthday.setVisibility(View.VISIBLE);
				tv_birthday.setText(birthday+"");
                moreInfo.setText("隐藏信息");
                isButton = false;
            }else {
                sexTxt.setVisibility(View.GONE);
				tv_gender.setVisibility(View.GONE);
                ageTxt.setVisibility(View.GONE);
				tv_age.setVisibility(View.GONE);
                nationTxt.setVisibility(View.GONE);
				tv_nation.setVisibility(View.GONE);
                birthdayTxt.setVisibility(View.GONE);
				tv_birthday.setVisibility(View.GONE);
                moreInfo.setText("更多信息");
                isButton = true;
            }
			break;
		default:
			break;
		}
	}
	//菜单按钮中的各个子页面点击事件
	private void showPopupMenu(View view) {
		  
        PopupMenu popup = new PopupMenu(MainActivity.this, view, Gravity.CENTER_HORIZONTAL);  
  
        popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());  
  
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
            @Override  
            public boolean onMenuItemClick(MenuItem menuItem) {  
                switch (menuItem.getItemId()) {  
                    case R.id.personal:  
                        startIntent(PersonalAllActivity.class);
                        return true;  
                    case R.id.activity:  
                    	startIntent(ProjectMessageManageActivity.class);
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
					case R.id.collection:
						startIntent(CollectionActivity.class);
						return true;
					case R.id.question:
						startIntent(ReportProblemActivity.class);
						return true;
					case R.id.setting:
						startIntent(SettingActivity.class);
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

		soonJoinBanner.startTurning(3000);
		recommendBanner.startTurning(3500);
	}
	// 停止自动翻页
	@Override
	protected void onPause() {

		super.onPause();
		//停止翻页
		soonJoinBanner.stopTurning();
		recommendBanner.stopTurning();
	}

	private List<ProjectMessage> projectMessageList = new ArrayList<ProjectMessage>();

	@Override
	public void onSuccess(ProjectMessageBean bean) {


		projectMessageList = bean.getList();

		for (int i=0;i<projectMessageList.size();i++){
			String picture = projectMessageList.get(i).getPicture();
			final String result = picture.substring(picture.lastIndexOf(","),picture.length());
			immediatelyImages.add(result);
			recommendImages.add(result);

		}
		initBanner();
	}


	//会员个人信息
	@Override
	public void onSuccess(Member member) {

		//后台传过来的图片为空，设置为默认的，否则，就用后台传过来的
		if (member.getAvatar() == null && member.getAvatar().isEmpty()){
			iv_photo.setImageResource(R.drawable.touxiang);
		}else {
			showNetworkImg(member.getAvatar());
		}
		tv_name.setText(member.getName()+"");
		tv_card_no.setText(member.getCard_no()+"");
		tv_point.setText(member.getPoint()+"");
		tv_level.setText(StringUtil.numToLevels(member.getLevel())+"");

		String IDcard = member.getIdno();

		birthday = IDcard.substring(6,10)+"-"+IDcard.substring(10,12)+"-"+IDcard.substring(12,14);
		age = StringUtil.IDcard2Age(IDcard);

		gender = StringUtil.numToGender(member.getGender());
		nation = StringUtil.numToNation(member.getNation());
	}

	private void showNetworkImg(String photoName) {

		ShowNetworkImgBusiness showNetworkImgBusiness = new ShowNetworkImgBusiness(context,photoName);
		showNetworkImgBusiness.setHandler(this);
		showNetworkImgBusiness.showNetworkImg();

	}

	@Override
	public void onSuccess(final byte[] bytes) {

		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					Message message = Message.obtain();

					Bundle b = new Bundle();
					b.putByteArray("photo",bytes);
					message.setData(b);
					handler.sendMessage(message);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}

	@Override
	public void onShowImgFailed(String s) {
		showToast("网络图片加载失败");

	}
}
