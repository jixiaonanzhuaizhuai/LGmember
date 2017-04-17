package com.lgmember.activity.score;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.adapter.ExchangeGiftAdapter;
import com.lgmember.adapter.MessageListAdapter;
import com.lgmember.bean.ExchangeGiftResultBean;
import com.lgmember.business.score.ExchangeAllGiftBusiness;
import com.lgmember.model.Gift;
import com.lgmember.util.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.igexin.sdk.GTServiceManager.context;

/**
 * Created by Yanan_Wu on 2016/12/19.
 */

public class ExchangeAllActivity extends Fragment implements ExchangeAllGiftBusiness.ExchangeAllGiftHandler{

    private GridView gridView;
    private LinearLayout ll_loading;
    private ProgressBar progressBar;
    private TextView loadDesc;

    private ExchangeGiftAdapter adapter;
    private List<Gift> giftList;

    private int pageNo = 1;
    private int pageSize = 20;
    private int total;
    private boolean isLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_exchangeall, container, false);
        init(view);
        giftList = new ArrayList<>();
        adapter = new ExchangeGiftAdapter(getActivity(),giftList);
        gridView.setAdapter(adapter);
        fillData();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),ExchangeGiftInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("gift_id",giftList.get(position).getId());
                String str = giftList.get(position).getPicture();
                String result = str.substring(str.lastIndexOf(","),str.length());
                bundle.putString("gift_img",result);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
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
                        fillData();
                    }
                }
            }
        });
        return  view;

    }

    private void init(View view) {
        gridView = (GridView)view.findViewById(R.id.mygridview);
        ll_loading = (LinearLayout)view.findViewById(R.id.ll_loading);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar1);
        loadDesc = (TextView)view.findViewById(R.id.tv_loading_desc);
        ll_loading.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        loadDesc.setText("正在拼命加载");

    }

    private void fillData(){
        ExchangeAllGiftBusiness exchangeAllGiftBusiness = new ExchangeAllGiftBusiness(getActivity(),pageNo,pageSize);
        exchangeAllGiftBusiness.setHandler(this);
        exchangeAllGiftBusiness.getAllGift();
    }


    @Override
    public void onSuccess(ExchangeGiftResultBean bean) {

        total = bean.getTotal();
        if (total == 0){
            progressBar.setVisibility(View.GONE);
            loadDesc.setText("当前还没有数据");
        }else {
            ll_loading.setVisibility(View.GONE);
            giftList.addAll(bean.getData());
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




}