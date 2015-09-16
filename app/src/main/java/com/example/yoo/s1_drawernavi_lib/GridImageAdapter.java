package com.example.yoo.s1_drawernavi_lib;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.ion.Ion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Yoo on 2015. 5. 21..
 */
public class GridImageAdapter extends BaseAdapter {
    private Context mContext;
    private HashMap<String, String> hs_drinks = new HashMap<>();
    private ArrayList<String> list_drinks = new ArrayList<>();
    private final int width;
    private final int height;

    public GridImageAdapter(Context c, ArrayList<Drink> st_products) {
        mContext = c;

        int iDisplayWidth = mContext.getResources().getDisplayMetrics().widthPixels-10 ;
        width = iDisplayWidth/3;
        height = (iDisplayWidth * 4)/9;

        if(st_products != null) {
            for(int i=0 ; i<st_products.size() ; i++){
                hs_drinks.put(st_products.get(i).getName(),st_products.get(i).getImgsource());
                list_drinks.add(i, st_products.get(i).getName());
            }
        }
    }

    @Override
    public int getCount() {
        return list_drinks.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        final String drinkName = list_drinks.get(position);
        final String imgResource = hs_drinks.get(list_drinks.get(position));

        if (convertView == null) {
            imageView = new ImageView(mContext);//3:4 360:480
            imageView.setAdjustViewBounds(false);
            imageView.setLayoutParams(new GridView.LayoutParams(width, height));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(1, 1, 1, 1);
            convertView = imageView;

        } else {
            imageView = (ImageView) convertView;
        }

        Ion.with(imageView)
                .placeholder(R.drawable.lodingimg)
                .resize(width, height)
                .error(R.drawable.noimage)
//                .animateLoad(spinAnimation)
//                .animateIn(fadeInAnimation)
                .load("android.resource://" + mContext.getPackageName() + "/drawable/" + imgResource);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SelectedDrink inputDrink = new SelectedDrink(drinkName, imgResource);
                send2orderFragment_SelectedInfo(drinkName, imgResource, "no option");
            }
        });
        //long press event
        /** 왜 onItemLongClick에 boolean 반환자가 있냐면
         * it means that the View that currently received
         * the event is the true event receiver and
         * the event should not be propagated to the other Views in the tree
         * longClick event 후에 click이벤트 등을 안받겠다공.
         * */
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg) {
                Drink selectedDrinkInfo = new Drink(drinkName, imgResource, "");
                OptionDialogFragment optionDialog = new OptionDialogFragment(selectedDrinkInfo);
                optionDialog.show(((Activity) mContext).getFragmentManager(), "my dialog");
                return true;
            }
        });
        return imageView;
    }

    public void send2orderFragment_SelectedInfo(String d_name, String imgsrc, String option){
        Bundle addInfo = new Bundle();
        addInfo.putString("name", d_name);
        addInfo.putString("img_src", imgsrc);
        addInfo.putString("option", option);
//        SelectedDrink s_Drink = new SelectedDrink(d_name, imgsrc, option);
        Fragment fragment = ((ActionBarActivity)mContext).getSupportFragmentManager().findFragmentByTag(OrderFragment.TAG);
        if(fragment != null && fragment instanceof OrderFragment){
            ((OrderFragment)fragment).addOrder(addInfo);
        }
    }

}

