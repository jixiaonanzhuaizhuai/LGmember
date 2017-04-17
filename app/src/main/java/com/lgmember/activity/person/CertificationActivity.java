package com.lgmember.activity.person;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.business.person.CertificationBusiness;
import com.lgmember.business.UploadImgBusiness;
import com.lgmember.model.Certification;
import com.lgmember.util.StringUtil;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.io.File;
import java.util.List;

/**
 * Created by Yanan_Wu on 2017/1/5.
 */

public class CertificationActivity extends BaseActivity implements OnClickListener,
        CertificationBusiness.CertificationResulHandler ,UploadImgBusiness.UploadImgResulHandler{

    private int REQUEST_CODE = 0;
    private Button uploadImgBtn, commitBtn;
    private ImageView imgShow;
    private EditText nameEdt,IDcardEdt;
    private Spinner sp_nation,sp_gender;
    private Context context;
    private int nation;
    private boolean gender;
    private String session_id;
    private String TAG = "-CertificationActivity-";

    private ArrayAdapter<String> nationAdapt,genderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        context = this;
        init();
    }

    private void init() {
        uploadImgBtn = (Button) findViewById(R.id.uploadImgBtn);
        commitBtn = (Button) findViewById(R.id.commitBtn);
        imgShow = (ImageView) findViewById(R.id.imgShow);
        nameEdt = (EditText)findViewById(R.id.nameEdt);
        IDcardEdt = (EditText)findViewById(R.id.IDcardEdt);

        sp_nation = (Spinner) findViewById(R.id.sp_nation);
        nationAdapt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,StringUtil.NATIONS);
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

        sp_gender = (Spinner) findViewById(R.id.sp_gender);
        genderAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,StringUtil.GENDER);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(genderAdapter);
        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    gender = true;
                }else {
                    gender = false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        uploadImgBtn.setOnClickListener(this);
        commitBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uploadImgBtn:
                uploadImg();
                break;
            case R.id.commitBtn:
                commit();
                break;
        }
    }
    public void commit(){
        Certification certification = new Certification();
        certification.setName(getText(nameEdt));
        certification.setIdno(getText(IDcardEdt));
        certification.setNation(nation);
        certification.setGender(gender);
        certification.setUpload_session_id(session_id);

        CertificationBusiness certificationBusiness = new CertificationBusiness(context,certification);
        certificationBusiness.setHandler(this);
        certificationBusiness.certificationMsg();
    }

    //上传图片
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };
    public void uploadImg(){
        ImgSelConfig config = new ImgSelConfig.Builder(this, loader)
                // 是否多选
                .multiSelect(false)
                .btnText("Confirm")
                // 确定按钮背景色
                //.btnBgColor(Color.parseColor(""))
                // 确定按钮文字颜色
                .btnTextColor(Color.WHITE)
                // 使用沉浸式状态栏
                .statusBarColor(Color.parseColor("#3F51B5"))
                // 返回图标ResId
                .backResId(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material)
                .title("Images")
                .titleColor(Color.WHITE)
                .titleBgColor(Color.parseColor("#3F51B5"))
                .allImagesText("All Images")
                .cropSize(1, 1, 200, 200)
                .needCrop(false)
                // 第一个是否显示相机
                .needCamera(true)
                // 最大选择图片数量
                .maxNum(9)
                .build();
        ImgSelActivity.startActivity(this, config, REQUEST_CODE);
    }

    //上传图片后的处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);

            for (String path : pathList) {
                File file = new File(path);
                if (file.exists()) {

                    uploadIDImg(file);
                    Bitmap bm = BitmapFactory.decodeFile(path);
                    imgShow.setVisibility(View.VISIBLE);
                    imgShow.setImageBitmap(bm);

                }
            }
        }
    }

    private void uploadIDImg(File file) {
        session_id = java.util.UUID.randomUUID().toString();
        UploadImgBusiness uploadImgBusiness = new UploadImgBusiness(context,session_id,file);
        uploadImgBusiness.setHandler(this);
        uploadImgBusiness.uploadImg();
    }

    //弹出对话框
    public void showDialog(String s){
        //注册成功后的业务逻辑
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("实名认证通知");
        builder.setMessage("未通过实名认证，原因是"+s+"，您需要重新申请实名认证");
        builder.setPositiveButton("重新申请", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                //完成业务逻辑
                nameEdt.setText("");
                IDcardEdt.setText("");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                //业务逻辑
            }
        });
        builder.show();

    }


    @Override
    public void onSuccess() {
        showToast("认证成功");
        finish();
    }

    public void onError(int code) {

        showDialog(context.getString(StringUtil.codeTomsg(code)));


    }

    @Override
    public void onUploadImgSuccess() {
        showToast("上传成功");
    }
}