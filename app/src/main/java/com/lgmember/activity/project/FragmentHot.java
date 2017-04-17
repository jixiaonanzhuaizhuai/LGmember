package com.lgmember.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lgmember.activity.MainActivity;
import com.lgmember.activity.R;
import com.lgmember.adapter.ProjectMessageListAdapter;
import com.lgmember.bean.ProjectMessageBean;
import com.lgmember.business.project.ProjectMessageListBusiness;
import com.lgmember.model.ProjectMessage;
import com.lgmember.util.StringUtil;
import com.lgmember.view.TopBarView;

import java.util.ArrayList;
import java.util.List;

public class FragmentHot extends Fragment implements ProjectMessageListBusiness.ProjectMessageListResultHandler,TopBarView.onTitleBarClickListener {

	private LinearLayout ll_loading;
	private ProgressBar progressBar;
	private TextView loadDesc;
	private ListView lv_hot_activity_list;
	private int pageNo = 1;
	private int pageSize = 10;
	private int total;
	private boolean isLoading;
	private TopBarView topBar;
	

	private List<ProjectMessage> projectMessageList ;
	private ProjectMessageListAdapter adapter;
	private String TAG = "-FragmentHot-";


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_hot, container, false);
		init(view);
		projectMessageList = new ArrayList<>();
		adapter = new ProjectMessageListAdapter(getActivity(),projectMessageList);

		lv_hot_activity_list.setAdapter(adapter);

		getData();

		lv_hot_activity_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new
						Intent(getActivity(),ProjectMessageDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("projectMessage",projectMessageList.get(position));

				intent.putExtras(bundle);
				startActivity(intent);
			}
		});


		return view;
	}

	private void init(View view) {
		topBar = (TopBarView)view.findViewById(R.id.topbar);
		topBar.setClickListener(this);
		lv_hot_activity_list=(ListView)view.findViewById(R.id.lv_hot_activity_list);
		ll_loading = (LinearLayout)view.findViewById(R.id.ll_loading);
		progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
		loadDesc = (TextView)view.findViewById(R.id.tv_loading_desc);
		ll_loading.setVisibility(View.VISIBLE);
		progressBar.setVisibility(View.VISIBLE);
		loadDesc.setText("正在拼命加载");
	}

	private void getData() {
		int tag = 0;

		ProjectMessageListBusiness projectMessageListBusiness = new
				ProjectMessageListBusiness(getActivity(),pageNo,pageSize,tag);
		projectMessageListBusiness.setHandler(this);
		projectMessageListBusiness.getHotProjectMessage();
	}


	@Override
	public void onSuccess(ProjectMessageBean bean) {

		total = bean.getTotal();


		if (bean.getList().size() == 0){
			progressBar.setVisibility(View.GONE);
			loadDesc.setText("还没有数据");
		}else {
			ll_loading.setVisibility(View.GONE);
			projectMessageList.addAll(bean.getList());
			adapter.notifyDataSetChanged();
			isLoading = false;
		}

	}

	@Override
	public void onError(int code) {

		Toast.makeText(getActivity(),getActivity().getText(StringUtil.codeTomsg(code)),Toast.LENGTH_LONG).show();

	}

	@Override
	public void onNetworkDisconnect() {

		Toast.makeText(getActivity(),getActivity().getText(R.string.http_network_disconnect),Toast.LENGTH_LONG).show();
	}

	@Override
	public void onFailed(int code, String msg) {

		Toast.makeText(getActivity(),"服务器出错了",Toast.LENGTH_LONG).show();

	}

	@Override
	public void onBackClick() {
		Intent intent = new Intent(getActivity(), MainActivity.class);
		startActivity(intent);
	}

	@Override
	public void onRightClick() {

	}
}
