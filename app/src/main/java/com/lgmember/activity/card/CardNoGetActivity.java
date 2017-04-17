package com.lgmember.activity.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lgmember.activity.MainActivity;
import com.lgmember.activity.R;
import com.lgmember.adapter.CardListAdapter;
import com.lgmember.bean.CardListBean;
import com.lgmember.business.card.CardListBusiness;
import com.lgmember.model.Card;
import com.lgmember.util.StringUtil;
import com.lgmember.view.TopBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanan_Wu on 2017/3/19.
 */

public class CardNoGetActivity extends Fragment implements CardListBusiness.CardListResultHandler,CardListAdapter.Callback,TopBarView.onTitleBarClickListener {

    private LinearLayout ll_loading;
    private ProgressBar progressBar;
    private TextView loadDesc;
    private ListView lv_card_list;
    private int pageNo = 1;
    private int pageSize = 5;
    private int total;
    private boolean isLoading;
    private int State = 0;

    private List<Card> cardList;
    private CardListAdapter adapter;
    private TopBarView topBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_card_list, container, false);

        initView(view);
        cardList = new ArrayList<>();
        adapter = new CardListAdapter(getActivity(),cardList,this);
        lv_card_list.setAdapter(adapter);
        getData();
        lv_card_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new
                        Intent(getActivity(),CardDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("card",cardList.get(position));

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        lv_card_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            //滑动状态改变的时候，回调
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}
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

    private void initView(View view) {
        topBar = (TopBarView)view.findViewById(R.id.topbar);
        topBar.setClickListener(this);
        lv_card_list = (ListView)view.findViewById(R.id.lv_card_list);
        ll_loading = (LinearLayout) view.findViewById(R.id.ll_loading);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar1);
        loadDesc = (TextView) view.findViewById(R.id.tv_loading_desc);
        ll_loading.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        loadDesc.setText("正在拼命加载");
    }
    private void getData() {
        CardListBusiness cardListBusiness = new CardListBusiness(getActivity(), pageNo,
                pageSize,State);
        cardListBusiness.setHandler(this);
        cardListBusiness.getCardList();
    }

    @Override
    public void onSuccess(CardListBean bean) {
        if (bean.getCardList().size() == 0){
            progressBar.setVisibility(View.GONE);
            loadDesc.setText("目前还没有数据");
        }else {
            ll_loading.setVisibility(View.GONE);
            total = bean.getTotal();
            cardList.addAll(bean.getCardList());
            adapter.notifyDataSetChanged();
            isLoading = false;
        }
    }

    @Override
    public void click(View v) {

        Toast.makeText(getActivity(),"hahahha",Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRightClick() {

    }
}
