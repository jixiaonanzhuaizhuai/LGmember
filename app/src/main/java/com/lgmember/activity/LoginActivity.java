package com.lgmember.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.lgmember.activity.person.MessageCodeActivity;
import com.lgmember.business.LoginBusiness;
import com.lgmember.util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends BaseActivity implements OnClickListener ,LoginBusiness.LoginResultHandler{

	private Button loginBtn;
	private TextView userEdt,pwdEdt,registerTxt,forgetPassTxt;
	private String loginName;
	private String loginPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		showToast("为了保护您的账户安全，请您及时修改密码！");
		initView();
	}

	private void initView() {
		loginBtn = (Button) findViewById(R.id.loginBtn);
		userEdt = (TextView)findViewById(R.id.userEdt);
		pwdEdt = (TextView)findViewById(R.id.pwdEdt);
		registerTxt = (TextView)findViewById(R.id.registerTxt);
		forgetPassTxt = (TextView)findViewById(R.id.forgetPassTxt);
		loginBtn.setOnClickListener(this);
		registerTxt.setOnClickListener(this);
		forgetPassTxt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginBtn:
			login();
			break;
		case R.id.registerTxt:
			startIntent(RegisterActivity.class);
			break;
		case R.id.forgetPassTxt:
			startIntent(MessageCodeActivity.class);
			break;
		default:
			break;
		}
	}
	// Activity只用来1.获取参数、2.处理结果，中间具体业务放到business中去
	//activity只用来1.获取参数2.处理结果，中间业务放到business中去
	private void login(){
		loginName = getText(userEdt);
		loginPass = getText(pwdEdt);

		LoginBusiness loginBusiness = new LoginBusiness(context,loginName,loginPass);
		//处理结果
		loginBusiness.setHandler(this);
		loginBusiness.Login();
	}

	// 如果嫌方法太多，可以使用适配器模式，减少方法的覆写

	@Override
	public void onSuccess() {

		startIntent(MainActivity.class);

	}

}
