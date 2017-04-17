package com.lgmember.business.collection;

import android.content.Context;

import com.lgmember.api.HttpHandler;
import com.lgmember.bean.ProjectMessageBean;
import com.lgmember.impl.CollectionImpl;

/**
 * Created by Yanan_Wu on 2017/2/27.
 */

public class CollectionListBusiness {
    private int pageNo;
    private int pageSize;
    private Context context;
    private CollectionImpl collectionImpl;

    public CollectionListBusiness(Context context, int pageNo, int pageSize ) {
        super();
        this.context = context;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    // 先验证参数的可发性，再登陆
    public void getCollectionList() {

        // 判断活动码是否有效
        collectionImpl = new CollectionImpl();
        collectionImpl.getCollectionList(pageNo,pageSize,handler,context);
    }

    private CollectionListResulHandler handler;

    public interface CollectionListResulHandler extends HttpHandler {

            public void onSuccess(ProjectMessageBean projectMessageBean);

    }
    public void setHandler(CollectionListResulHandler handler){
        this.handler = handler;
    }

    }