package com.miraclewong.recyclerviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class RecyclerTest3 extends Activity {

    private RecyclerView mRcList;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<String> mData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout frameLayout = new FrameLayout(RecyclerTest3.this);

        mRcList = new RecyclerView(RecyclerTest3.this);
        mLayoutManager = new LinearLayoutManager(this);
        mRcList.setLayoutManager(mLayoutManager);
        mRcList.setHasFixedSize(true);
        // 设置显示动画
        mRcList.setItemAnimator(new DefaultItemAnimator());
        // 增加测试数据
        mData.add("Recycler");
        mData.add("Recycler");
        mData.add("Recycler");
        mAdapter = new RecyclerAdapter(mData);
        mRcList.setAdapter(mAdapter);

        frameLayout.addView(mRcList);
        setContentView(frameLayout);
    }
}
