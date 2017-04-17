package com.lgmember.activity.message;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

import com.lgmember.activity.BaseActivity;
import com.lgmember.activity.R;
import com.lgmember.adapter.MessageListAdapter;
import com.lgmember.bean.MessageBean;
import com.lgmember.business.message.DeleteMsgBusiness;
import com.lgmember.business.message.MessageBusiness;
import com.lgmember.model.Message;
import com.lgmember.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanan_Wu on 2016/12/19.
 */

public class SystemMessageActivity extends Fragment implements MessageBusiness.MessageResultHandler ,DeleteMsgBusiness.DeleteMsgResultHandler{

    private LinearLayout ll_loading;
    private ProgressBar progressBar;
    private TextView loadDesc;
    private ListView lv_sysmeg_list;
    private int pageNo = 1;
    private int pageSize = 10;
    private int total;
    private boolean isLoading;

    //private int index;

    private List<Message> messageList;
    private MessageListAdapter adapter;
    private String TAG = "-SystemMessageActivity-";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_systemmsg, container, false);
        init(view);
        messageList = new ArrayList<>();
        adapter = new MessageListAdapter(getActivity(),messageList);
        lv_sysmeg_list.setAdapter(adapter);

        getData();

        lv_sysmeg_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int message_id = messageList.get(position).getMessage_id();

                Intent intent = new Intent(getActivity(),MessageDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("message_id",message_id);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        lv_sysmeg_list.setOnScrollListener(new AbsListView.OnScrollListener() {
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

        lv_sysmeg_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DeleteDialog(position);
                messageList.remove(position);
                return true;
            }
        });

        return  view;

    }



    private void DeleteDialog(int position) {

        final int message_id = messageList.get(position).getMessage_id();
        final int state = messageList.get(position).getState();

        AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
        build.setMessage("确定删除该条消息");
        build.setTitle("提示");
        build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteData(message_id,state);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        build.setNegativeButton("取消",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"取消",Toast.LENGTH_LONG).show();

                dialog.dismiss();
            }

        });

        build.show();
    }

    private void deleteData(int message_id, int state) {

        DeleteMsgBusiness deleteMsgBusiness = new DeleteMsgBusiness(getActivity(), message_id, state);
        deleteMsgBusiness.setHandler(this);
        deleteMsgBusiness.deleteMessage();

    }

    private void getData() {
        MessageBusiness messageBusiness = new MessageBusiness(getActivity(), pageNo, pageSize);
        messageBusiness.setHandler(this);
        messageBusiness.getSysMessage();
    }

    private void init(View view) {
        lv_sysmeg_list=(ListView)view.findViewById(R.id.lv_sysmeg_list);
        ll_loading = (LinearLayout) view.findViewById(R.id.ll_loading);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        loadDesc = (TextView) view.findViewById(R.id.tv_loading_desc);
        ll_loading.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        loadDesc.setText("正在拼命加载");
    }

    @Override
    public void onSuccess(MessageBean bean) {


        total = bean.getTotal();
        if (total == 0){
            progressBar.setVisibility(View.GONE);
            loadDesc.setText("当前还没有数据");
        }else {
            ll_loading.setVisibility(View.GONE);
            messageList.addAll(bean.getMessageList());
            adapter.notifyDataSetChanged();
            isLoading = false;
        }

    }


    @Override
    public void onDeleteMsgSuccess() {
        adapter.notifyDataSetChanged();

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
