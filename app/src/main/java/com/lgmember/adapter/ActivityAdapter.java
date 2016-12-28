package com.lgmember.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgmember.lgmember.R;
import com.lgmember.model.Activity;

import java.util.List;

/**
 * Created by Yanan_Wu on 2016/12/23.
 */

public class ActivityAdapter extends ArrayAdapter {
    private int resourceId;

    public ActivityAdapter(Context context, int textViewResourceId, List<Activity> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }
        ImageView activityImage = (ImageView) view.findViewById(R.id.activity_img);
        TextView activityName = (TextView) view.findViewById(R.id.activity_name);
        TextView activityIntroduction = (TextView) view.findViewById(R.id.activity_introduction);

        activityImage.setImageResource(activity.getImageId());
        activityName.setText(activity.getName());
        activityIntroduction.setText(activity.getIntroduction());
        return view;

    }
}
