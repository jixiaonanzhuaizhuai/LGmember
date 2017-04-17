package com.lgmember.activity.person;

import android.os.Bundle;
import android.widget.TextView;

import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.business.message.MemberMessageBusiness;
import com.lgmember.model.Member;
import com.lgmember.util.StringUtil;
import com.lgmember.view.TopBarView;
import com.lgmember.view.TopBarView.onTitleBarClickListener;


public class PersonalActivity extends BaseActivity implements onTitleBarClickListener,MemberMessageBusiness.MemberMessageResulHandler{

	private TopBarView topbar;
	private TextView tv_name,tv_idno,tv_mobile,tv_gender,tv_addr,tv_company,tv_job_title,tv_nation, tv_source,tv_create_time,tv_education,tv_month_income,tv_month_outcome,tv_state,tv_level, tv_authorized,tv_point, tv_card_no,tv_point_used,tv_black,tv_num_regist,tv_num_sign;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		initView();
		getData();

	}

	private void getData() {
		MemberMessageBusiness memberMessage = new MemberMessageBusiness(context);
		memberMessage.setHandler(this);
		memberMessage.getMemberMessage();
	}

	private void initView() {
		topbar = (TopBarView)findViewById(R.id.topbar);
		topbar.setClickListener(this);
		tv_name = (TextView)findViewById(R.id.tv_name);
		tv_idno = (TextView)findViewById(R.id.tv_idno);
		tv_mobile = (TextView)findViewById(R.id.tv_mobile);
		tv_gender = (TextView)findViewById(R.id.tv_gender);
		tv_addr = (TextView)findViewById(R.id.tv_addr);
		tv_company = (TextView)findViewById(R.id.tv_company);
		tv_job_title = (TextView)findViewById(R.id.tv_job_title);
		tv_nation = (TextView)findViewById(R.id.tv_nation);
		tv_source = (TextView)findViewById(R.id.tv_source);
		tv_create_time = (TextView)findViewById(R.id.tv_create_time);
		tv_education = (TextView)findViewById(R.id.tv_education);
		tv_month_income = (TextView)findViewById(R.id.tv_month_income);
		tv_month_outcome = (TextView)findViewById(R.id.tv_month_outcome);
		tv_state = (TextView)findViewById(R.id.tv_state);
		tv_level = (TextView)findViewById(R.id.tv_level);
		tv_authorized = (TextView)findViewById(R.id.tv_authorized);
		tv_point = (TextView)findViewById(R.id.tv_point);
		tv_card_no = (TextView)findViewById(R.id.tv_card_no);
		tv_point_used = (TextView)findViewById(R.id.tv_point_used);
		tv_black = (TextView)findViewById(R.id.tv_black);
		tv_num_regist = (TextView)findViewById(R.id.tv_num_regist);
		tv_num_sign = (TextView)findViewById(R.id.tv_num_sign);

	}


	@Override
	public void onSuccess(Member member) {
		tv_name.setText(member.getName()+"");
		tv_idno.setText(member.getIdno()+"");
		tv_mobile.setText(member.getMobile()+"");
		tv_gender.setText(StringUtil.numToGender(member.getGender())+"");
		tv_addr.setText(member.getAddr()+"");
		tv_company.setText(member.getCompany()+"");
		tv_job_title.setText(member.getJob_title()+"");
		tv_nation.setText(StringUtil.numToNation(member.getNation())+"");
		tv_source.setText(StringUtil.numToSoure(member.getSource())+"");
		tv_create_time.setText(member.getCreate_time()+"");
		tv_education.setText(StringUtil.numToEducation(member.getEducation())+"");
		tv_month_income.setText(member.getMonth_income()+"");
		tv_month_outcome.setText(member.getMonth_outcome()+"");
		tv_state.setText(StringUtil.numToState(member.getState())+"");
		tv_level.setText(StringUtil.numToLevels(member.getLevel())+"");
		tv_authorized.setText(StringUtil.boolean2String(member.getAuthorized())+"");
		tv_point.setText(member.getPoint()+"");
		tv_card_no.setText(member.getCard_no()+"");
		tv_point_used.setText(member.getPoint_used()+"");
		tv_black.setText(StringUtil.boolean2String(member.getBlack())+"");
		tv_num_regist.setText(member.getNum_regist()+"");
		tv_num_sign.setText(member.getNum_sign()+"");


	}


	@Override
	public void onBackClick() {
		finish();
	}

	@Override
	public void onRightClick() {
		startIntent(EditPersonalActivity.class);
	}



}
