package com.example.yoo.s1_drawernavi_lib;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Yoo on 2015. 7. 18..
 */
public class SelectCafeActivity extends ActionBarActivity {

    public final static String CAFE_NAME = "com.example.yoo.s1_drawernavi_lib.cafename";
    private ListView mListview;
    private SelectCafeCustomAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcafe);
//        findViewById(R.id.selectcafe_imgview_starbucks).setOnClickListener(mClickListener);
//        findViewById(R.id.selectcafe_imgview_caffebene).setOnClickListener(mClickListener);
        mListview = (ListView) findViewById(R.id.selectcafe_listview_listgroup);
        mAdapter = new SelectCafeCustomAdapter(this);
        mListview.setAdapter(mAdapter);
        setData();
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendMessage(mAdapter.mArrayList.get(position).mCafename);
            }
        });
    }

    private void setData() {
        mAdapter.addItem(getResources().getDrawable(R.drawable.logo_statbucks),"starbucks","2015.07.31");
        mAdapter.addItem(getResources().getDrawable(R.drawable.logo_caffebene),"caffebene","2015.08.31");
    }

    public void sendMessage(String ClickedTextView) {
        Intent intent = new Intent(SelectCafeActivity.this ,MainActivity.class);
        intent.putExtra(CAFE_NAME, ClickedTextView);
        startActivity(intent);
    }
}
