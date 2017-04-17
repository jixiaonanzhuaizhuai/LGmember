package com.lgmember.activity.person;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.business.person.EditMemberMsgBusiness;
import com.lgmember.business.message.MemberMessageBusiness;
import com.lgmember.model.Member;
import com.lgmember.util.StringUtil;
import com.lgmember.view.TopBarView;

public class EditPersonalActivity extends BaseActivity implements
		MemberMessageBusiness.MemberMessageResulHandler,
		EditMemberMsgBusiness.EditMemberMessageResulHandler,
		TopBarView.onTitleBarClickListener {

	private TopBarView topBar;
	private EditText edt_mobile,edt_addr,edt_company,edt_job_title,
					edt_month_income,edt_month_outcome;
	private Spinner sp_nation,sp_education;
	private int nation,education;


	private ArrayAdapter<String> nationAdapt,educationAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editpersonal);
		init();
		getData();

	}
	private void getData() {
		MemberMessageBusiness memberMessage = new MemberMessageBusiness(context);
		memberMessage.setHandler(this);
		memberMessage.getMemberMessage();

	}

	public void init(){
		showToast("根据完善资料的程度，会赠送您相应的积分");

		topBar = (TopBarView)findViewById(R.id.topbar);
		topBar.setClickListener(this);
		sp_nation = (Spinner)findViewById(R.id.sp_nation);
		nationAdapt = new
				ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,StringUtil.NATIONS);
		nationAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_nation.setAdapter(nationAdapt);

		sp_nation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				nation = position+1;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		sp_education = (Spinner)findViewById(R.id.sp_education);
		educationAdapter = new
				ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,StringUtil.EDUCATIONS);
		educationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_education.setAdapter(educationAdapter);

		sp_education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				education = position+1;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});


		edt_mobile = (EditText)findViewById(R.id.edt_mobile);
		edt_addr = (EditText)findViewById(R.id.edt_addr);
		edt_company = (EditText)findViewById(R.id.edt_company);
		edt_job_title = (EditText)findViewById(R.id.edt_job_title);
		edt_month_income = (EditText)findViewById(R.id.edt_month_income);
		edt_month_outcome = (EditText)findViewById(R.id.edt_month_outcome);

	}

	private void submitData() {
		String addr = getText(edt_addr);
		String mobile = getText(edt_mobile);
		String company = getText(edt_company);
		String job_title = getText(edt_job_title);
		int month_income = Integer.parseInt(getText(edt_month_income));
		int month_outcome = Integer.parseInt(getText(edt_month_outcome));

		Member member = new Member();
		member.setMobile(mobile);
		member.setAddr(addr);
		member.setCompany(company);
		member.setJob_title(job_title);
		member.setNation(nation);
		member.setEducation(education);
		member.setMonth_income(month_income);
		member.setMonth_outcome(month_outcome);

		EditMemberMsgBusiness editMemberMsgBusiness = new EditMemberMsgBusiness(context,member);
		editMemberMsgBusiness.setHandler(this);
		editMemberMsgBusiness.editMemberMessage();

	}

	@Override
	public void onEditMemberMsgSuccess() {
		showToast("操作成功");
		startIntent(PersonalActivity.class);
	}

	@Override
	public void onSuccess(Member member) {
		edt_mobile.setText(member.getMobile());
		edt_mobile.setSelection(member.getMobile().length());
		edt_addr.setText(member.getAddr());
		edt_company.setText(member.getCompany());
		edt_job_title.setText(member.getJob_title());
		sp_nation.setSelection(member.getNation()-1);
		sp_education.setSelection(member.getEducation()-1);
		edt_month_income.setText(member.getMonth_income()+"");
		edt_month_outcome.setText(member.getMonth_outcome()+"");
	}

	@Override
	public void onBackClick() {
		finish();
	}

	@Override
	public void onRightClick() {
		submitData();
	}
}
