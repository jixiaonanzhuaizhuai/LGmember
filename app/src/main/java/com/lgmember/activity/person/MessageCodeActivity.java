package com.lgmember.activity.person;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.activity.person.PwdCodeActivity;
import com.lgmember.business.SmsCodeBusiness;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yanan_Wu on 2016/12/29.
 */

//通过验证码找回密码

public class MessageCodeActivity extends BaseActivity implements OnClickListener,SmsCodeBusiness.GetCodeResultHandler {

    private Button nextBtn;
    private EditText phoneEdt;
    private String phoneTxt;
    private static String TAG ="------MessageCodeActivity------";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagecode);
        init();
    }
    public void init(){
        phoneEdt = (EditText)findViewById(R.id.phoneEdt);
        nextBtn = (Button)findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nextBtn:
                getCode();
        }
    }

    public void getCode(){
        phoneTxt = getText(phoneEdt);
        SmsCodeBusiness getCodeBusiness = new SmsCodeBusiness(context,phoneTxt);
        //处理结果
        getCodeBusiness.setHandler(this);
        getCodeBusiness.getCode();
    }

    // 如果嫌方法太多，可以使用适配器模式，减少方法的覆写

    @Override
    public void onRequestCodeSuccess(JSONObject jsob) {
        int code = 99;
        try {
            code = jsob.getInt("code");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (code == 0){
            try {

                String sms_capt_tokenTxt = jsob.getString("token");

                Intent intent = new Intent(MessageCodeActivity.this,PwdCodeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("sms_capt_tokenTxt",sms_capt_tokenTxt);
                bundle.putString("phone",phoneTxt);
                intent.putExtras(bundle);
                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {

            int codeArray[] = {R.string.code_0, R.string.code_1, R.string.code_2, R.string.code_3, R.string.code_4, R.string.code_5, R.string.code_6,
                    R.string.code_7, R.string.code_8, R.string.code_9, R.string.code_10, R.string.code_11};
            for (int i = 0; i <= codeArray.length; i++) {
                if (i == code) {
                    showToast(context.getString(codeArray[i]));
                }
            }
        }

    }
    //请求验证码失败时
    @Override
    public void onRequestCodeFail(String msg) {
        showToast(TAG+msg);

    }
}
