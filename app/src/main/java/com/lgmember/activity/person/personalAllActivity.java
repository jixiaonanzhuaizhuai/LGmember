package com.lgmember.activity.person;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.MainActivity;
import com.lgmember.activity.R;
import com.lgmember.business.ShowNetworkImgBusiness;
import com.lgmember.business.UpdatePhotoBusiness;
import com.lgmember.business.message.MemberMessageBusiness;
import com.lgmember.model.Certification;
import com.lgmember.model.Member;
import com.lgmember.view.TopBarView;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.io.File;
import java.util.List;

/**
 * Created by Yanan_Wu on 2017/4/5.
 */

public class PersonalAllActivity extends BaseActivity implements TopBarView.onTitleBarClickListener,View.OnClickListener ,UpdatePhotoBusiness.UpdatePhotoResulHandler,MemberMessageBusiness.MemberMessageResulHandler,ShowNetworkImgBusiness.ShowNetworkImgResulHandler{

    private TopBarView topBar;
    private RelativeLayout rl_photo,rl_personal,rl_certification,rl_modifyPwd;
    private ImageView iv_photo;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            byte[] photo = msg.getData().getByteArray("photo");
            final Bitmap bitmap = BitmapFactory.decodeByteArray(photo,0,photo.length);

            iv_photo.setBackground(new BitmapDrawable(context.getResources(),bitmap));
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_all);

        init();
        getData();
    }

    private void init() {
        iv_photo = (ImageView)findViewById(R.id.iv_photo);
        topBar = (TopBarView)findViewById(R.id.topbar);
        topBar.setClickListener(this);
        rl_photo = (RelativeLayout)findViewById(R.id.rl_photo);
        rl_personal = (RelativeLayout)findViewById(R.id.rl_personal);
        rl_certification = (RelativeLayout)findViewById(R.id.rl_certification);
        rl_modifyPwd = (RelativeLayout)findViewById(R.id.rl_modifyPwd);
        rl_certification.setOnClickListener(this);
        rl_modifyPwd.setOnClickListener(this);
        rl_photo.setOnClickListener(this);
        rl_personal.setOnClickListener(this);
    }

    private void getData() {
        MemberMessageBusiness memberMessage = new MemberMessageBusiness(context);
        memberMessage.setHandler(this);
        memberMessage.getMemberMessage();
    }

    @Override
    public void onBackClick() {
        startIntent(MainActivity.class);
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_photo:
                //更新头像
                uploadImg();
                break;
            case R.id.rl_personal:
                //更新会员信息
                startIntent(PersonalActivity.class);
                break;
            case R.id.rl_certification:
                startIntent(CertificationActivity.class);
                break;
            case R.id.rl_modifyPwd:
                startIntent(ModifyPwdActivity.class);
                break;
        }
    }

    //上传图片
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };

    private int REQUEST_CODE = 0;
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
                    UpdatePhoto(file);
                    Bitmap bm = BitmapFactory.decodeFile(path);
                    iv_photo.setBackground(new BitmapDrawable(context.getResources(),bm));


                }
            }
        }
    }

    private void UpdatePhoto(File file) {
        UpdatePhotoBusiness updatePhotoBusiness = new UpdatePhotoBusiness(context,file);
        updatePhotoBusiness.setHandler(this);
        updatePhotoBusiness.updatePhoto();
    }

    private void showNetworkImg(String photoName) {

        ShowNetworkImgBusiness showNetworkImgBusiness = new ShowNetworkImgBusiness(context,photoName);
        showNetworkImgBusiness.setHandler(this);
        showNetworkImgBusiness.showNetworkImg();

    }

    //更新头像
    @Override
    public void onSuccess() {
        showToast("上传成功");

    }

    @Override
    public void onSuccess(Member member) {
        //后台传过来的图片为空，设置为默认的，否则，就用后台传过来的
        if (member.getAvatar() == null && member.getAvatar().isEmpty()){
            iv_photo.setImageResource(R.drawable.touxiang);
        }else {
            showNetworkImg(member.getAvatar());
        }
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

    }
}
