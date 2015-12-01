package com.miraclewong.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miraclewong on 15/11/5.
 */
public class RecyView extends FrameLayout {

    private RecyclerView mRcList;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> mData = new ArrayList<String>();

    public void setmData(List<String> mData) {
        this.mData = mData;
    }

    public RecyView(Context context) {

        super(context);
    }

    public RecyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context) {

        mRcList = new RecyclerView(context);
        mLayoutManager = new LinearLayoutManager(context);
        mRcList.setLayoutManager(mLayoutManager);
        mRcList.setHasFixedSize(true);
        // 设置显示动画
        mRcList.setItemAnimator(new DefaultItemAnimator());
        // 增加测试数据
        mAdapter = new RecyclerAdapter(mData);
        mRcList.setAdapter(mAdapter);
        this.addView(mRcList);
    }
}
