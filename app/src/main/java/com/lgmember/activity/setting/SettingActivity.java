package com.lgmember.activity.setting;

import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushManager;
import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.service.DemoIntentService;
import com.lgmember.view.TopBarView;
import com.maiml.library.BaseItemLayout;
import com.maiml.library.config.ConfigAttrs;
import com.maiml.library.config.Mode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanan_Wu on 2016/12/19.
 */

public class SettingActivity extends BaseActivity implements TopBarView.onTitleBarClickListener {


    private BaseItemLayout layout;
    private TopBarView topBar;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);

        topBar = (TopBarView)findViewById(R.id.topbar);
        topBar.setClickListener(this);
        layout = (BaseItemLayout) findViewById(R.id.baseLayout);

        List<String> valueList = new ArrayList<>();

        valueList.add("是否接受推送消息");
        valueList.add("分享设置");
        valueList.add("清除图片缓存");
        valueList.add("关于我们");

        List<Integer> resIdList = new ArrayList<>();

        resIdList.add(R.mipmap.xiaoxituisong);
        resIdList.add(R.mipmap.fenxiang);
        resIdList.add(R.mipmap.clearbuffer);
        resIdList.add(R.mipmap.aboutus);

        List<String> rightTextList = new ArrayList<>();

        rightTextList.add("test1");
        rightTextList.add("test2");
        rightTextList.add("test3");
        rightTextList.add("test4");
        rightTextList.add("test5");

        ConfigAttrs attrs  =new ConfigAttrs();//把全部参数的配置，委托给 ConfigAttrs 类处理。

        //参数 使用链式方式配置
        attrs.setValueList(valueList)  // 文字 list
                .setResIdList(resIdList) // icon list
                .setIconWidth(24)  //设置icon 的大小
                .setIconHeight(24)
                .setItemMarginTop(10) //设置 全部item的间距
                .setItemMode(Mode.NORMAL)//设置item 为 Mode.NORMAL 模式
                .setItemMode(0,Mode.BOTTON)
                .setItemMode(1,Mode.ARROW) //设置第二个item 为 Mode.TEXT 模式
                .setItemMode(2,Mode.ARROW)//设置第三个item 为 Mode.ARROW 模式
                .setItemMode(3,Mode.ARROW)//设置第四个item 为 Mode.BOTTON 模式
                .setArrowResId(R.mipmap.img_find_arrow) //设置箭头资源值
                .setUpResId(R.drawable.img_up) //设置开闭按钮资源值
                .setTrunResId(R.drawable.img_turn_down)//设置开闭按钮资源值
                .setMarginRight(10); //设置距右边边距

        layout.setConfigAttrs(attrs).create();

        layout.setOnBaseItemClick(new BaseItemLayout.OnBaseItemClick() {
            @Override
            public void onItemClick(int position) {
                Log.i("----------->","位置"+position);
                showToast("----- position = " + position);
            }
        });


        layout.setOnSwitchClickListener(new BaseItemLayout.OnSwitchClickListener() {
            @Override
            public void onClick(int position, boolean isCheck) {
               showToast("-----> position = " + position +" isCheck = " + isCheck + position);
            }
        });
    }


    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
