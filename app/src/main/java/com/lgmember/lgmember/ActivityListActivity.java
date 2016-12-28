package com.lgmember.lgmember;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lgmember.adapter.ActivityAdapter;
import com.lgmember.model.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanan_Wu on 2016/12/23.
 */

public class ActivityListActivity extends AppCompatActivity {
    private List<Activity> activityList = new ArrayList<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitylist);

        initActivity();
        ListView listview1 = (ListView) findViewById(R.id.listview1);
        ActivityAdapter activityAdapter = new ActivityAdapter(ActivityListActivity.this, R.layout.activity_activitylistitem, activityList);
        listview1.setAdapter(activityAdapter);
        /*
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(ListViewActivity.this,android.R.layout.simple_list_item_1,data);
        listview1.setAdapter(adapter);
        */
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Activity activity = activityList.get(position);
                //Toast.makeText(ActivityListActivity.this, activity.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("position",String.valueOf(position));
                intent.setClass(ActivityListActivity.this, ActivityDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initActivity() {
        Activity apple = new Activity("活动主题：欢度圣诞节", R.mipmap.image0, "这是一次非常有意义的活动，欢迎大家前来参加！！！");
        activityList.add(apple);
        Activity banana = new Activity("活动主题：欢度春节", R.mipmap.image1, "这是一次非常有意义的活动，欢迎大家前来参加！！！");
        activityList.add(banana);
        Activity cherry = new Activity("活动主题：欢度平安夜", R.mipmap.image2, "这是一次非常有意义的活动，欢迎大家前来参加！！！");
        activityList.add(cherry);
        Activity grape = new Activity("活动主题：欢度小年", R.mipmap.image3, "这是一次非常有意义的活动，欢迎大家前来参加！！！");
        activityList.add(grape);
        Activity kiwi = new Activity("活动主题：欢度生日", R.mipmap.image4, "这是一次非常有意义的活动，欢迎大家前来参加！！！");
        activityList.add(kiwi);
        Activity lemon = new Activity("活动主题：欢度圣诞节", R.mipmap.image5, "这是一次非常有意义的活动，欢迎大家前来参加！！！");
        activityList.add(lemon);
        Activity mango = new Activity("活动主题：欢度圣诞节", R.mipmap.image6, "这是一次非常有意义的活动，欢迎大家前来参加！！！ ");
        activityList.add(mango);
        Activity orange = new Activity("活动主题：欢度圣诞节", R.mipmap.image7, "这是一次非常有意义的活动，欢迎大家前来参加！！！");
        activityList.add(orange);
        Activity pear = new Activity("活动主题：欢度圣诞节", R.mipmap.image0, "这是一次非常有意义的活动，欢迎大家前来参加！！！");
        activityList.add(pear);
        Activity persimmon = new Activity("活动主题：欢度圣诞节", R.mipmap.image1, "这是一次非常有意义的活动，欢迎大家前来参加！！！ ");
        activityList.add(persimmon);
        Activity pineapple = new Activity("活动主题：欢度圣诞节", R.mipmap.image2, "这是一次非常有意义的活动，欢迎大家前来参加！！！");
        activityList.add(pineapple);
        Activity strawberry = new Activity("活动主题：欢度圣诞节", R.mipmap.image3, "这是一次非常有意义的活动，欢迎大家前来参加！！！");
        activityList.add(strawberry);
        Activity watermelon = new Activity("活动主题：欢度圣诞节", R.mipmap.image4, "这是一次非常有意义的活动，欢迎大家前来参加！！！");
        activityList.add(watermelon);

    }
    }
