package com.lgmember.activity.sign;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lgmember.activity.AudioFileFunc;
import com.lgmember.activity.MainActivity;
import com.lgmember.activity.R;
import com.lgmember.adapter.SignListAdapter;
import com.lgmember.bean.SignListBean;
import com.lgmember.business.project.ActivityCodeBusiness;
import com.lgmember.business.sign.ScannerSignBusiness;
import com.lgmember.business.sign.SignListBusiness;
import com.lgmember.business.sign.UploadRecordBusiness;
import com.lgmember.model.SignResult;
import com.lgmember.util.ErrorCode;
import com.lgmember.util.StringUtil;
import com.lgmember.view.TopBarView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanan_Wu on 2017/4/5.
 */

public class SignListActivity extends Fragment implements SignListBusiness.SignListResultHandler,TopBarView.onTitleBarClickListener,ActivityCodeBusiness.ActivityCodeResulHandler,ScannerSignBusiness.ScannerSignResulHandler,UploadRecordBusiness.UploadRecordResulHandler {

    private TopBarView topbar;
    private LinearLayout ll_loading;
    private ProgressBar progressBar;
    private TextView loadDesc;
    private ListView lv_sign_list;
    private Button btn_record;
    private int pageNo = 1;
    private int pageSize = 10;
    private int total;
    private boolean isLoading;

    private AlertDialog dialog;

    private final static int FLAG_WAV = 0;
    private final static int FLAG_AMR = 1;
    private int mState = -1;    //-1:没再录制，0：录制wav，1：录制amr
    private UIHandler uiHandler;
    private UIThread uiThread;

    private List<SignResult> signResultsList;
    private SignListAdapter adapter;
    private String TAG = "-SignListActivity-";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_sign_list, container, false);
        init(view);
        signResultsList = new ArrayList<>();
        adapter = new SignListAdapter(getActivity(),signResultsList);
        lv_sign_list.setAdapter(adapter);

        getData();


        lv_sign_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            //滑动状态改变的时候，回调
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            //在滑动的时候不断的回调
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
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

    //获得节目签到列表
    private void getData() {
        SignListBusiness signListBusiness = new SignListBusiness(getActivity(), pageNo, pageSize);
        signListBusiness.setHandler(this);
        signListBusiness.getSignList();
    }
    //初始化控件
    private void init(View view) {
        uiHandler = new UIHandler();
        //rightPopupMenu = new RightPopupMenu(this,R.layout.popup_menu_sign,true);
        topbar = (TopBarView)view.findViewById(R.id.topbar) ;
        topbar.setClickListener(this);
        lv_sign_list=(ListView)view.findViewById(R.id.lv_sign_list);
        ll_loading = (LinearLayout) view.findViewById(R.id.ll_loading);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        loadDesc = (TextView) view.findViewById(R.id.tv_loading_desc);
        ll_loading.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        loadDesc.setText("正在拼命加载");
    }

    @Override
    public void onSuccess(SignListBean signListBean) {

        total = signListBean.getTotal();

        if (total == 0){
            progressBar.setVisibility(View.GONE);
            loadDesc.setText("当前还没有数据");
        }else {
            ll_loading.setVisibility(View.GONE);
            signResultsList.addAll(signListBean.getData());
            adapter.notifyDataSetChanged();
            isLoading = false;
        }
    }

    @Override
    public void onBackClick() {

        Intent intent = new Intent(getActivity(),ActivitySignActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRightClick() {
        recordSign();
    }
    /*
    * 录音签到
    * */
    public void recordSign() {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        dialog = adb.create();
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_record_sign, null);
        btn_record = (Button)view.findViewById(R.id.btn_record_wav);

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record(FLAG_WAV);
            }
        });
        dialog.setView(view,0,0,0,0);
        dialog.show();
    }

    /**
     * 开始录音
     * @param mFlag，0：录制wav格式，1：录音amr格式
     */
    private void record(int mFlag){
        if(mState != -1){
            Message msg = new Message();
            Bundle b = new Bundle();// 存放数据
            b.putInt("cmd",CMD_RECORDFAIL);
            b.putInt("msg", ErrorCode.E_STATE_RECODING);
            msg.setData(b);

            uiHandler.sendMessage(msg); // 向Handler发送消息,更新UI
            return;
        }
        int mResult = -1;
        switch(mFlag){
            case FLAG_WAV:
                MediaRecordFunc mRecord = MediaRecordFunc.getInstance();
                mResult = mRecord.startRecordAndFile();
                break;
        }
        if(mResult == ErrorCode.SUCCESS){
            uiThread = new UIThread();
            new Thread(uiThread).start();
            mState = mFlag;
        }else{
            Message msg = new Message();
            Bundle b = new Bundle();// 存放数据
            b.putInt("cmd",CMD_RECORDFAIL);
            b.putInt("msg", mResult);
            msg.setData(b);

            uiHandler.sendMessage(msg); // 向Handler发送消息,更新UI
        }
    }
    /**
     * 停止录音
     */
    private void stop(){
        if(mState != -1){
            switch(mState){
                case FLAG_WAV:
                    MediaRecordFunc mRecord = MediaRecordFunc.getInstance();
                    mRecord.stopRecordAndFile();
                    break;
            }
            if(uiThread != null){
                uiThread.stopThread();
            }
            if(uiHandler != null)
                uiHandler.removeCallbacks(uiThread);
            Message msg = new Message();
            Bundle b = new Bundle();// 存放数据
            b.putInt("cmd",CMD_STOP);
            b.putInt("msg", mState);
            msg.setData(b);
            uiHandler.sendMessageDelayed(msg,1000); // 向Handler发送消息,更新UI
            mState = -1;
        }
    }
    private final static int CMD_RECORDING_TIME = 2000;
    private final static int CMD_RECORDFAIL = 2001;
    private final static int CMD_STOP = 2002;



    class UIHandler extends Handler {
        public UIHandler() {
        }
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            Log.d("MyHandler", "handleMessage......");
            super.handleMessage(msg);
            Bundle b = msg.getData();
            int vCmd = b.getInt("cmd");
            switch(vCmd)
            {
                case CMD_RECORDING_TIME:
                    int vTime = b.getInt("msg");
                    btn_record.setText("正在录音");
                    btn_record.setEnabled(false);
                    if (vTime == 3){
                        stop();
                    }
                    break;
                case CMD_RECORDFAIL:
                    int vErrorCode = b.getInt("msg");
                    String vMsg =
                            ErrorCode.getErrorInfo(getActivity(),
                                    vErrorCode);
                    btn_record.setText("录音失败");
                    break;
                case CMD_STOP:
                    int vFileType = b.getInt("msg");
                    switch(vFileType){
                        case FLAG_WAV:
                            MediaRecordFunc mRecord_2 = MediaRecordFunc.getInstance();
                            long mSize = mRecord_2.getRecordFileSize();
                            String path = AudioFileFunc.getWavFilePath();
                            uploadRecord(path);
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void uploadRecord(String path) {

       String session_id = java.util.UUID.randomUUID().toString();

        UploadRecordBusiness uploadRecordBusiness =
                new UploadRecordBusiness(getActivity(),
                        session_id,path,MediaRecordFunc.timestamp);
        uploadRecordBusiness.setHandler(this);
        uploadRecordBusiness.uploadRecord();
    }

    class UIThread implements Runnable {
        int mTimeMill = 0;
        boolean vRun = true;
        public void stopThread(){
            vRun = false;
        }
        public void run() {
            while(vRun){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mTimeMill ++;
                Log.d("thread", "mThread........"+mTimeMill);
                Message msg = new Message();
                Bundle b = new Bundle();// 存放数据
                b.putInt("cmd",CMD_RECORDING_TIME);
                b.putInt("msg", mTimeMill);
                msg.setData(b);
                SignListActivity.this.uiHandler.sendMessage(msg); // 向Handler发送消息,更新UI
            }

        }
    }

    @Override
    public void onArgumentEmpty(String s) {
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onArgumentFormatError(String s) {
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }

    //活动码签到结果
    @Override
    public void onSuccess(String s) {
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onUploadRecordSuccess(String s) {
        btn_record.setText(s);
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
