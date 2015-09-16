package com.example.yoo.s1_drawernavi_lib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Yoo on 2015. 5. 16..
 */
public class GridmenuFragment extends Fragment {
    // Store instance variables
    private String cafename;
    private int position;
//    private String st_products;
    private ArrayList<Drink> drinksByseletedCategory;

    // MainActivity에서 drwarable list의 인자 저장해서 GredmenuFragment를 만들어준다잉. 이떄 인자값 전달
    public static GridmenuFragment newInstance(Bundle gridinfo) {
        GridmenuFragment gridmenuFragment = new GridmenuFragment();
        Bundle args = new Bundle();
        args = gridinfo;
        gridmenuFragment.setArguments(args);
        return gridmenuFragment;
    }
    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cafename = getArguments().getString("cafename");
        position = getArguments().getInt("position", 0);
        drinksByseletedCategory = getArguments().getParcelableArrayList("productsList");
    }

    // Inflate the view for the fragment based on layout XML
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gridmenu, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new GridImageAdapter(view.getContext(), drinksByseletedCategory));
        return view;
    }

}
