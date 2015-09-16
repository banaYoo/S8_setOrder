package com.example.yoo.s1_drawernavi_lib;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Yoo on 2015. 7. 18..
 */
public class SelectCafeActivity extends ActionBarActivity {

    public final static String CAFE_NAME = "com.example.yoo.s1_drawernavi_lib.cafename";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcafe);
        findViewById(R.id.selectcafe_imgview_starbucks).setOnClickListener(mClickListener);
        findViewById(R.id.selectcafe_imgview_caffebene).setOnClickListener(mClickListener);

    }

    ImageView.OnClickListener mClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.selectcafe_imgview_starbucks:
                    sendMessage("starbucks");
                    break;
                case R.id.selectcafe_imgview_caffebene:
                    sendMessage("caffebene");
                    break;
            }
        }
    };


    public void sendMessage(String ClickedTextView) {
        Intent intent = new Intent(SelectCafeActivity.this ,MainActivity.class);
        intent.putExtra(CAFE_NAME, ClickedTextView);
        startActivity(intent);
    }
}
