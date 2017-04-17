package com.lgmember.activity.score;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.adapter.HistoryScoresListAdapter;
import com.lgmember.bean.HistoryScoresBean;
import com.lgmember.business.score.HistoryScoresBusiness;
import com.lgmember.model.HistoryScores;
import com.lgmember.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanan_Wu on 2016/12/19.
 */

public class HistoryScoresActivity extends Fragment implements HistoryScoresBusiness.HistoryScoresResultHandler {

    private LinearLayout ll_loading;
    private ProgressBar progressBar;
    private TextView loadDesc;
    private ListView listView;
    private int pageNo = 1;
    private int pageSize = 5;
    private int total;
    private boolean isLoading;

    private List<HistoryScores> historyScoresList = new ArrayList<HistoryScores>();
    private HistoryScoresListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_historyscores, container, false);
        init(view);
        adapter = new HistoryScoresListAdapter(getActivity(),historyScoresList);
        listView.setAdapter(adapter);
        getData();
        listView.setOnScrollListener(new OnScrollListener() {
            //滑动状态改变的时候，回调
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            //在滑动的时候不断的回调
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem+visibleItemCount==totalItemCount&&!isLoading) {
                    isLoading = true;
                    if (totalItemCount< total){
                        pageNo++;
                        getData();
                    }
                }
            }
        });
        return view;

    }




    public void init(View view) {
        listView = (ListView) view.findViewById(R.id.lv_history_scores);
        ll_loading = (LinearLayout) view.findViewById(R.id.ll_loading);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        loadDesc = (TextView) view.findViewById(R.id.tv_loading_desc);
        ll_loading.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        loadDesc.setText("正在拼命加载");
    }
    private void getData() {
        HistoryScoresBusiness historyScoresBusiness = new HistoryScoresBusiness(getActivity(), pageNo, pageSize);
        historyScoresBusiness.setHandler(this);
        historyScoresBusiness.getHistoryScores();
    }

    @Override
    public void onHisSuccess(HistoryScoresBean bean) {

            ll_loading.setVisibility(View.GONE);
            total = bean.getTotal();
            historyScoresList.addAll(bean.getHistoryScoresList());
            adapter.notifyDataSetChanged();
            isLoading = false;
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

}
