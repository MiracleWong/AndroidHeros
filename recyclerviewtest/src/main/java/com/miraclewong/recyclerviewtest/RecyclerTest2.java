package com.miraclewong.recyclerviewtest;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecyclerTest2 extends Activity {

    private RecyView mRcList;
    private List<String> mData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData.add("Recycler");
        mData.add("Recycler");
        mData.add("Recycler");
        mRcList = new RecyView(RecyclerTest2.this);
        mRcList.setmData(mData);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//        mRcList.setLayoutParams(params);
        mRcList.init(getBaseContext());

        setContentView(mRcList);
    }
}
