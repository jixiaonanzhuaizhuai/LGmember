package com.lgmember.activity.message;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.business.message.MessageDetailBusiness;
import com.lgmember.model.Message;
import com.lgmember.util.StringUtil;
import com.lgmember.view.TopBarView;

/**
 * Created by Yanan_Wu on 2017/2/14.
 */

public class MessageDetailActivity extends BaseActivity implements MessageDetailBusiness.MessageDetailResultHandler ,TopBarView.onTitleBarClickListener {

    private TextView tv_title,tv_create_time,
            tv_content,tv_hyperlink,tv_author;
    private TopBarView topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        Bundle bundle = this.getIntent().getExtras();
        int message_id = bundle.getInt("message_id");
        init();
        getData(message_id);
    }

    private void init() {
        topBar = (TopBarView)findViewById(R.id.topbar);
        topBar.setClickListener(this);
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_create_time = (TextView)findViewById(R.id.tv_create_time);
        tv_content = (TextView)findViewById(R.id.tv_content);
        tv_hyperlink = (TextView)findViewById(R.id.tv_hyperlink);
        tv_author = (TextView)findViewById(R.id.tv_author);
    }


    private void getData(int message_id) {
        MessageDetailBusiness messageDetailBusiness = new MessageDetailBusiness(context, message_id);
        messageDetailBusiness.setHandler(this);
        messageDetailBusiness.getMessageDetail();
    }

    @Override
    public void onMessageDetailSuccess(Message message) {
        tv_title.setText(message.getTitle());
        tv_create_time.setText(message.getCreate_time());
        tv_content.setText(Html.fromHtml(message.getContent()));
        tv_author.setText(message.getAuthor());
        tv_hyperlink.setText(message.getHyperlink());
        tv_hyperlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.baidu.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
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
