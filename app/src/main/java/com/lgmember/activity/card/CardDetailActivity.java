package com.lgmember.activity.card;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.business.card.CardDetailBusiness;
import com.lgmember.business.card.ExchangeCardBusiness;
import com.lgmember.business.card.GetCardBusiness;
import com.lgmember.model.Card;
import com.lgmember.model.ProjectMessage;
import com.lgmember.util.StringUtil;
import com.lgmember.view.TopBarView;

/**
 * Created by Yanan_Wu on 2017/2/13.
 */

public class CardDetailActivity extends BaseActivity
        implements View.OnClickListener,
        GetCardBusiness.GetCardResultHandler,
        ExchangeCardBusiness.ExchangeCardResultHandler,
        TopBarView.onTitleBarClickListener {

    private Button btn_get_card,btn_exchange;
    private TextView tv_name,tv_des,tv_start_time,tv_end_time,tv_type_name,
            tv_num,tv_level, tv_no;
    private Card card;
    private TopBarView topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carddetail);

        Bundle bundle = this.getIntent().getExtras();
        card = (Card)bundle.getSerializable("card");

        initView();
    }


    private void initView() {

        topBar = (TopBarView)findViewById(R.id.topbar);
        topBar.setClickListener(this);

        btn_get_card = (Button) findViewById(R.id.btn_get_card);
        btn_get_card.setOnClickListener(this);

        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_name.setText(""+card.getName());

        tv_des = (TextView)findViewById(R.id.tv_des);
        tv_des.setText(""+card.getDescription());

        tv_start_time = (TextView)findViewById(R.id.tv_start_time);
        tv_start_time.setText(""+card.getStart_time());

        tv_end_time = (TextView)findViewById(R.id.tv_end_time);
        tv_end_time.setText(""+card.getEnd_time());


        tv_type_name = (TextView)findViewById(R.id.tv_type_name);
        tv_type_name.setText(""+card.getType_name());

        tv_num = (TextView)findViewById(R.id.tv_num);
        tv_num.setText(""+card.getNum());

        tv_level = (TextView)findViewById(R.id.tv_level);
        tv_level.setText(""+card.getLevel());

        tv_no = (TextView)findViewById(R.id.tv_no);
        tv_no.setText(""+card.getNo());



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get_card:
                getCard();
                break;
            case R.id.btn_exchange:
                /*exchange();*/
                break;
        }
    }

    private void getCard() {

        Log.i("-----888------",card.getId()+"");

        GetCardBusiness getCardBusiness = new GetCardBusiness(context, card.getId());
        getCardBusiness.setHandler(this);
        getCardBusiness.getCard();
    }

    /*private void exchange() {
        ExchangeCardBusiness exchangeCardBusiness = new ExchangeCardBusiness(context, card_id);
        exchangeCardBusiness.setHandler(this);
        exchangeCardBusiness.exchangeCard();
    }*/

    @Override
    public void onGetCardSuccess(int data) {
        if (data == 0){
            showToast("成功领取");
        }else if(data == 1){
            showToast("已经被抢光");
        }else {
            showToast("请勿重复领券");
        }
    }


    //获取兑换码的结果
    @Override
    public void onExchangeCardSuccess(String data) {
        showToast("兑换码为："+data);
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }
}
