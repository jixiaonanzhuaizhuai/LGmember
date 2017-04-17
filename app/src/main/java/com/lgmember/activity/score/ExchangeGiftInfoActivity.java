package com.lgmember.activity.score;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.business.score.ExchangeGiftInfoBusiness;
import com.lgmember.model.Gift;
import com.lgmember.util.StringUtil;
import com.lgmember.view.TopBarView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yanan_Wu on 2017/3/8.
 */

public class ExchangeGiftInfoActivity extends BaseActivity implements ExchangeGiftInfoBusiness.ExchangeGiftInfoHandler,View.OnClickListener ,TopBarView.onTitleBarClickListener {
    private ImageView iv_gift_img;
    private TextView tv_gift_name,tv_gift_desc,tv_gift_point,tv_gift_number;
    private int gift_id;
    private Button btn_change;
    private String gift_img;
    private TopBarView topBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_gift_info);
        init();
        fillData();
    }
    private void init() {
        topBar = (TopBarView)findViewById(R.id.topbar);
        topBar.setClickListener(this);
        iv_gift_img = (ImageView)findViewById(R.id.iv_gift_img);
        tv_gift_name = (TextView)findViewById(R.id.tv_gift_name);
        tv_gift_desc = (TextView)findViewById(R.id.tv_gift_desc);
        tv_gift_point = (TextView)findViewById(R.id.tv_gift_point);
        tv_gift_number = (TextView)findViewById(R.id.tv_gift_number);
        btn_change = (Button)findViewById(R.id.btn_exchange);
        btn_change.setOnClickListener(this);
    }

    private void fillData() {
        Bundle bundle = this.getIntent().getExtras();
        gift_id = bundle.getInt("gift_id");
        gift_img = bundle.getString("gift_img");
        ExchangeGiftInfoBusiness exchangeGiftInfoBusiness = new ExchangeGiftInfoBusiness(context,gift_id);
        exchangeGiftInfoBusiness.setHandler(this);
        exchangeGiftInfoBusiness.getGiftInfo();
    }

    @Override
    public void onSuccess(JSONObject jsob) {
        int code = 100;
        Gift gift ;
        try {
            code = jsob.getInt("code");

            if (code == 0){
                JSONObject jobject = jsob.getJSONObject("data");
                gift = new Gift(jobject.getInt("id"),jobject.getString("name"),
                        jobject.getString("description"),jobject.getInt("point"),
                        jobject.getString("picture"),jobject.getInt("number"));
                iv_gift_img.setBackground(new BitmapDrawable(context.getResources(),StringUtil.base64ToBitmap(gift_img)));

                tv_gift_name.setText(gift.getName());
                tv_gift_desc.setText(gift.getDescription());
                tv_gift_point.setText(""+gift.getPoint());
                tv_gift_number.setText(""+gift.getNumber());
            }else {
                showToast(context.getString(StringUtil.codeTomsg(code)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_exchange:
                Intent intent = new Intent(ExchangeGiftInfoActivity.this,ExchangeGiftDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("gift_id",gift_id);
                bundle.putString("gift_img",gift_img);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
