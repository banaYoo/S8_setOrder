package com.example.yoo.s1_drawernavi_lib;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Yoo on 2015. 7. 4..
 */
public class OptionDialogFragment extends DialogFragment{
    private OptionExpAdapter optionAdapter;
    private ArrayList<String> mGroupList = null;
    private ArrayList<ArrayList<String>> mChildList = null;
    private ArrayList<String> mChildListContent = null;
    private ExpandableListView mListView;
    private String selectedAllOpt = "no option";
    private String drinkName = "";
    private String imgResource = "";

    public OptionDialogFragment(Drink seletedDrinkInfo) {
        if(seletedDrinkInfo != null){
            drinkName = seletedDrinkInfo.getName();
            imgResource = seletedDrinkInfo.getImgsource();
            selectedAllOpt = seletedDrinkInfo.getOption();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(getArguments() != null) {
//            drinkName = getArguments().get("name").toString();
//            imgResource = getArguments().get("img_src").toString();
//            selectedAllOpt = getArguments().get("option").toString();
//        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_optiondialog,null);

        mGroupList = new ArrayList<String>();
        mChildList = new ArrayList<ArrayList<String>>();
        mChildListContent = new ArrayList<String>();
        if(((MainActivity)getActivity()).option != null) {
            for (int i = 0; i < ((MainActivity)getActivity()).option.size(); i++) {
                mGroupList.add(((MainActivity)getActivity()).option.get(i).c_name);
                mChildListContent = ((MainActivity)getActivity()).option.get(i).getO_name();
                mChildList.add(mChildListContent);
            }
        }

        optionAdapter = new OptionExpAdapter(getActivity().getApplicationContext(), mGroupList, mChildList);
        mListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        mListView.setAdapter(optionAdapter);
        builder.setView(view);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"cancel button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedAllOpt = optionAdapter.allSelectedOptionMemo();
                send2orderFragment_SelectedInfo(drinkName, imgResource, getSelectedAllOpt());
                Toast.makeText(getActivity(),"ok button clicked",Toast.LENGTH_SHORT).show();
            }
        });

        Dialog dialog = builder.create();
        return dialog;
    }
    public String getSelectedAllOpt() { return selectedAllOpt; }

    public void send2orderFragment_SelectedInfo(String d_name, String imgsrc, String option){
        Bundle addInfo = new Bundle();
        addInfo.putString("name", d_name);
        addInfo.putString("img_src", imgsrc);
        addInfo.putString("option", option);
//        SelectedDrink s_Drink = new SelectedDrink(d_name, imgsrc, option);
        Fragment fragment = ((ActionBarActivity)getActivity()).getSupportFragmentManager().findFragmentByTag(OrderFragment.TAG);
        if(fragment != null && fragment instanceof OrderFragment){
            ((OrderFragment)fragment).addOrder(addInfo);
        }
//        OrderFragment orderFragment = new OrderFragment();
//        // update the main content by replacing fragments
//        FragmentManager fragmentManager = ((Activity) mContext).getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.detailFragment, orderFragment.addInstance(addInfo))
//                .addToBackStack(null)
//                .commit();
    }
//    public void send2orderFragment_SelectedInfo(String d_name, String imgsrc, String option){
//        Bundle addInfo = new Bundle();
//        addInfo.putString("name", d_name);
//        addInfo.putString("img_src", imgsrc);
//        addInfo.putString("option", option);
////        SelectedDrink s_Drink = new SelectedDrink(d_name, imgsrc, option);
//
////        OrderFragment orderFragment = new OrderFragment();
////        android.app.FragmentManager fragmentManager = getFragmentManager();
////        fragmentManager.beginTransaction()
////                .replace(R.id.detailFragment, orderFragment.addInstance(addInfo))
////                .commit();
//    }

//    public static OptionDialogFragment addInstance(Bundle optionInfo) {
//        OptionDialogFragment optionDialogFragment = new OptionDialogFragment();
//        optionDialogFragment.setArguments(optionInfo);
//        return optionDialogFragment;
//    }
}
