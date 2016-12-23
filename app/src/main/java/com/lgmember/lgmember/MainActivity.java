package com.lgmember.lgmember;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.maiml.library.BaseItemLayout;
import com.maiml.library.config.ConfigAttrs;
import com.maiml.library.config.Mode;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements OnClickListener, OnItemClickListener {


	private Button menuBtn,messageBtn,signBtn,moreInfo;
    private TextView sexTxt,ageTxt,nationTxt,birthdayTxt,editInfo;

	private ConvenientBanner convenientBanner,recommendBanner;//轮播控件
	private ArrayList<Integer> immediatelyImages = new ArrayList<Integer>();
	private ArrayList<Integer> recommendImages = new ArrayList<Integer>();

	private BaseItemLayout layout;
    private boolean isButton = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		init();

	}
	private void initView() {
		menuBtn = (Button) findViewById(R.id.menuBtn);
		messageBtn = (Button) findViewById(R.id.messageBtn);
		signBtn = (Button) findViewById(R.id.signBtn);
		moreInfo = (Button) findViewById(R.id.moreInfo);
        sexTxt = (TextView)findViewById(R.id.sexTxt);
        ageTxt = (TextView)findViewById(R.id.ageTxt);
        nationTxt = (TextView)findViewById(R.id.nationTxt);
        birthdayTxt = (TextView)findViewById(R.id.birthdayTxt);
		editInfo = (TextView)findViewById(R.id.editInfo);
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
		recommendBanner = (ConvenientBanner) findViewById(R.id.recommendBanner);
		layout = (BaseItemLayout) findViewById(R.id.baseLayout);
		menuBtn.setOnClickListener(this);
		messageBtn.setOnClickListener(this);
		signBtn.setOnClickListener(this);
		editInfo.setOnClickListener(this);
		moreInfo.setOnClickListener(this);
		immediatelyImages.add(R.mipmap.image0);
		immediatelyImages.add(R.mipmap.image1);
		immediatelyImages.add(R.mipmap.image2);
		immediatelyImages.add(R.mipmap.image3);
		recommendImages.add(R.mipmap.recommend0);
		recommendImages.add(R.mipmap.recommend1);
		recommendImages.add(R.mipmap.recommend2);
		recommendImages.add(R.mipmap.recommend3);
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
		.setResIdList(resIdList)//图片list
		.setIconWidth(24)//图片宽度和高度
		.setIconHeight(24)
				.setItemMode(Mode.NORMAL);
		layout.setConfigAttrs(attrs).create();

	}
	private void init() {
		convenientBanner.setPages(
				new CBViewHolderCreator<LocalImageHolderView>() {
					public LocalImageHolderView createHolder() {
						return new LocalImageHolderView();
					}
				}, immediatelyImages)
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)//设置指示器的方向
//                .setOnPageChangeListener(this)//监听翻页事件
				  .setOnItemClickListener(this);
//      convenientBanner.setManualPageable(false);//设置不能手动影响
		recommendBanner.setPages(
				new CBViewHolderCreator<LocalImageHolderView>() {
					public LocalImageHolderView createHolder() {
						return new LocalImageHolderView();
					}
				}, recommendImages).setOnItemClickListener(this);

		//BaseItemLayout点击事件
		layout.setOnBaseItemClick(new BaseItemLayout.OnBaseItemClick() {
			@Override
			public void onItemClick(int position) {
				Toast.makeText(MainActivity.this,"----- position = " + position,Toast.LENGTH_SHORT).show();
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
			startIntent(PersonalActivity.class);
			break;
		case R.id.moreInfo:
            if(isButton){
                sexTxt.setVisibility(View.VISIBLE);
                ageTxt.setVisibility(View.VISIBLE);
                nationTxt.setVisibility(View.VISIBLE);
                birthdayTxt.setVisibility(View.VISIBLE);
                moreInfo.setText("隐藏信息");
                isButton = false;
            }else {
                sexTxt.setVisibility(View.GONE);
                ageTxt.setVisibility(View.GONE);
                nationTxt.setVisibility(View.GONE);
                birthdayTxt.setVisibility(View.GONE);
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
		recommendBanner.startTurning(3000);
	}
	// 停止自动翻页
	@Override
	protected void onPause() {
		super.onPause();
		//停止翻页
		convenientBanner.stopTurning();
		recommendBanner.stopTurning();
	}
	public void onItemClick(int position) {
		switch (position) {
			case 0:
				Intent intent0 = new Intent();
				//Intent传递参数
				intent0.putExtra("position",  String.valueOf(position));
				intent0.setClass(MainActivity.this, ActivityDetailActivity.class);
				startActivity(intent0);
				break;
			case 1:
				Intent intent1 = new Intent();
				intent1.putExtra("position",String.valueOf(position));
				intent1.setClass(MainActivity.this, ActivityDetailActivity.class);
				startActivity(intent1);
				break;
			case 2:
				Intent intent2 = new Intent();
				intent2.putExtra("position", String.valueOf(position));
				intent2.setClass(MainActivity.this, ActivityDetailActivity.class);
				startActivity(intent2);
				break;
			case 3:
				Intent intent3 = new Intent();
				intent3.putExtra("position", String.valueOf(position));
				intent3.setClass(MainActivity.this, ActivityDetailActivity.class);
				startActivity(intent3);
				break;
			default:
				break;
		}
	}

}
