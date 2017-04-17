package com.lgmember.activity.collection;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.message.MessageDetailActivity;
import com.lgmember.activity.R;
import com.lgmember.activity.project.ProjectMessageDetailActivity;
import com.lgmember.adapter.ProjectMessageListAdapter;
import com.lgmember.bean.ProjectMessageBean;
import com.lgmember.business.collection.CollectionAddBusiness;
import com.lgmember.business.collection.CollectionListBusiness;
import com.lgmember.model.ProjectMessage;
import com.lgmember.util.StringUtil;
import com.lgmember.view.TopBarView;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends BaseActivity implements CollectionListBusiness.CollectionListResulHandler,CollectionAddBusiness.CollectionResulHandler,TopBarView.onTitleBarClickListener {

	private LinearLayout ll_loading;
	private ProgressBar progressBar;
	private TextView loadDesc;
	private ListView lv_collection_list;
	private int pageNo = 1;
	private int pageSize = 5;
	private int total;
	private boolean isLoading;
	private String is_checked_in = "true";

	private TopBarView topBar;


	private List<ProjectMessage> collectionList = new ArrayList<>();
	private ProjectMessageListAdapter adapter;
	private String TAG = "-CollectionActivity-";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection);
		init();
		adapter = new ProjectMessageListAdapter(this,collectionList);
		lv_collection_list.setAdapter(adapter);

		getData();

		lv_collection_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(CollectionActivity.this,ProjectMessageDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("projectMessage",collectionList.get(position));

				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		lv_collection_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				deleteCollection(position);
				collectionList.remove(position);
				return true;
			}
		});


	}
	private void deleteCollection(int position) {
		int id = collectionList.get(position).getId();
		CollectionAddBusiness collectionAddBusiness = new CollectionAddBusiness(context,id);
		collectionAddBusiness.setHandler(this);
		collectionAddBusiness.deleteCollection();
	}

	private void init() {
		topBar = (TopBarView)findViewById(R.id.topbar);
		topBar.setClickListener(this);
		lv_collection_list=(ListView)findViewById(R.id.lv_collection_list);
		ll_loading = (LinearLayout)findViewById(R.id.ll_loading);
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		loadDesc = (TextView)findViewById(R.id.tv_loading_desc);
		ll_loading.setVisibility(View.VISIBLE);
		progressBar.setVisibility(View.VISIBLE);
		loadDesc.setText("正在拼命加载");
	}

	private void getData() {

		CollectionListBusiness collectionListBusiness = new CollectionListBusiness(context, pageNo,
				pageSize);
		collectionListBusiness.setHandler(this);
		collectionListBusiness.getCollectionList();
	}


	@Override
	public void onSuccess(ProjectMessageBean projectMessageBean) {
		if (projectMessageBean.getList() == null){
			progressBar.setVisibility(View.GONE);
			loadDesc.setText("还没有数据");
		}else {
			ll_loading.setVisibility(View.GONE);
			total = projectMessageBean.getTotal();
			collectionList.addAll(projectMessageBean.getList());
			adapter.notifyDataSetChanged();
			isLoading = false;
		}
	}

	@Override
	public void onCollectionSuccess(String str) {
		showToast(str);
	}

	@Override
	public void onBackClick() {
		finish();
	}

	@Override
	public void onRightClick() {

	}
}
