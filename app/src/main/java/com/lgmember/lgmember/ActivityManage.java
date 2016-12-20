package com.lgmember.lgmember;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ActivityManage extends BaseActivity {

	private ListView ago_activity_list;
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);
		ago_activity_list = (ListView) findViewById(R.id.ago_activity_list);
		adapter = new ArrayAdapter<String>(ActivityManage.this,android.R.layout.simple_list_item_1, getDataSource());
		ago_activity_list.setAdapter(adapter);
		ago_activity_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(ActivityManage.this, "------>>"+ adapter.getItem(position), Toast.LENGTH_LONG).show();;

			}
		});
	}

	public List<String> getDataSource() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			list.add("anna" + i);
		}

		return list;

	}
	
	

}
