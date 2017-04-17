package com.lgmember.activity.person;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.business.person.ModifyPwdBusiness;
import com.lgmember.view.TopBarView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yanan_Wu on 2017/3/1.
 */

public class ModifyPwdActivity extends BaseActivity implements ModifyPwdBusiness.ModifyResultHandler,TopBarView.onTitleBarClickListener {

    private EditText oldPwdEdt,newPwdEdt,confirmNewPwdEdt;
    private String oldPwdTxt,newPwdTxt,confirmNewPwdTxt;
    private TopBarView topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifypwd);
        initView();
    }
    public void initView(){
        topBar = (TopBarView)findViewById(R.id.topbar);
        topBar.setClickListener(this);
        oldPwdEdt = (EditText)findViewById(R.id.oldPwdEdt);
        newPwdEdt =(EditText)findViewById(R.id.newPwdEdt);
        confirmNewPwdEdt = (EditText)findViewById(R.id.confirmNewPwdEdt);


    }
    public void modifyPwd(){

        oldPwdTxt = getText(oldPwdEdt);
        newPwdTxt = getText(newPwdEdt);
        confirmNewPwdTxt = getText(confirmNewPwdEdt);

        ModifyPwdBusiness modifyPwdBusiness = new ModifyPwdBusiness(context,oldPwdTxt,newPwdTxt,confirmNewPwdTxt);
        //处理结果
        modifyPwdBusiness.setHandler(this);
        modifyPwdBusiness.Login();
    }

    @Override
    public void onSuccess(JSONObject jsob) {
        int code = 99;
        try {
            code = jsob.getInt("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (code == 0){
            showToast("操作成功");
        }else {
            int codeArray[] = {R.string.code_1,R.string.code_2,R.string.code_3,R.string.code_4,R.string.code_5,R.string.code_6,
                    R.string.code_7,R.string.code_8,R.string.code_9,R.string.code_10,R.string.code_11,};
            for (int i=1;i<=codeArray.length;i++){
                if (i == code){
                    showToast(context.getString(codeArray[i]));
                }
            }
        }

    }
    @Override
    public void onPwdNoEqual(String string) {
        showToast(string);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {
        modifyPwd();
    }
}
