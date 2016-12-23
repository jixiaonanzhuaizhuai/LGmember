package com.lgmember.lgmember;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yanan_Wu on 2016/12/19.
 */

public class ExchangeAllActivity extends Activity {
    private GridView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchangeall);

        //准备要添加的数据条目
        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 9; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("imageItem", R.mipmap.lipin+i);//添加图像资源的ID
            item.put("textItem", "icon" + i);//按序号添加ItemText
            items.add(item);
        }

        //实例化一个适配器
        SimpleAdapter adapter = new SimpleAdapter(this,
                items,
                R.layout.grid_item,
                new String[]{"imageItem", "textItem"},
                new int[]{R.id.image_item, R.id.text_item});

        //获得GridView实例
        gv = (GridView)findViewById(R.id.mygridview);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(ExchangeAllActivity.this,"我要兑换"+position+"礼品",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(ExchangeAllActivity.this,"我要兑换"+position+"礼品",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        //为GridView设置适配器
        gv.setAdapter(adapter);

    }
}
