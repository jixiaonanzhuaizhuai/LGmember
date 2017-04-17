package com.lgmember.activity.sign;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lgmember.activity.MainActivity;
import com.lgmember.activity.R;
import com.lgmember.business.project.ActivityCodeBusiness;
import com.lgmember.util.StringUtil;
import com.lgmember.view.TopBarView;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Yanan_Wu on 2016/12/19.
 */

public class ActivitySignActivity extends Fragment implements View.OnClickListener,ActivityCodeBusiness.ActivityCodeResulHandler,TopBarView.onTitleBarClickListener {

    private AlertDialog dialog;
    private Button mBtnScanner,btn_code_sign;
    private String activityCode;

    private TopBarView topBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_activitysign, container, false);
        initview(view);
        return view;
    }
    private void initview(View view) {
        topBar = (TopBarView)view.findViewById(R.id.topbar);
        topBar.setClickListener(this);
        mBtnScanner = (Button) view.findViewById(R.id.btn_scan);
        mBtnScanner.setOnClickListener(this);
        btn_code_sign = (Button)view.findViewById(R.id.btn_code_sign);
        btn_code_sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_scan:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class) , 0);
                break;
            case R.id.btn_code_sign:
                codeSign();
                break;
        }
    }

    private void getCodeSign() {
        ActivityCodeBusiness activityCodeBusiness = new ActivityCodeBusiness(getActivity(),activityCode);
        activityCodeBusiness.setHandler(this);
        activityCodeBusiness.getActivityCode();
    }

    public void codeSign() {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        dialog = adb.create();
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_avtivitycode, null);
        final EditText codeEt = (EditText)view.findViewById(R.id.activityCodeEt);
        Button btnOk = (Button)view.findViewById(R.id.okBtn);
        Button btnCancle = (Button)view.findViewById(R.id.cancleBtn);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                activityCode = codeEt.getText().toString();
                //活动码符合要求的话
                getCodeSign();
                /*Toast.makeText(ctx,"签到成功",Toast.LENGTH_SHORT).show();*/
                dialog.dismiss();
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setView(view,0,0,0,0);
        dialog.show();
    }

    //二维码扫描处理结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            Uri uri = Uri.parse(result);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
    }

    @Override
    public void onArgumentEmpty(String s) {
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onArgumentFormatError(String s) {
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(String  s) {

        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackClick() {
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onError(int code) {

        Toast.makeText(getActivity(),getActivity().getText(StringUtil.codeTomsg(code)),Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNetworkDisconnect() {

        Toast.makeText(getActivity(),getActivity().getText(R.string.http_network_disconnect),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailed(int code, String msg) {
        Toast.makeText(getActivity(),"服务器出错了",Toast.LENGTH_LONG).show();
    }
}

