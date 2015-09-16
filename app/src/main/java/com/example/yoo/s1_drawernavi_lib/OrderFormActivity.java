package com.example.yoo.s1_drawernavi_lib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Yoo on 2015. 8. 23..
 */
public class OrderFormActivity extends ActionBarActivity {
    private ListView listView;
    private OrderFormAdapter orderFormAdapter;
    private ArrayList<Drink> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        orderList = intent.getParcelableArrayListExtra("orderList");

        setContentView(R.layout.activity_orderform);
        listView = (ListView)findViewById(R.id.lstview_orderform);
        orderFormAdapter = new OrderFormAdapter(this, R.layout.apart_orderform);
        listView.setAdapter(orderFormAdapter);
        addData();
    }

    private void addData(){
        orderFormAdapter.addAll(orderList);
    }

}
