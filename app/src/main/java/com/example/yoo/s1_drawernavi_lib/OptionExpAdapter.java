package com.example.yoo.s1_drawernavi_lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yoo on 2015. 8. 19..
 */
public class OptionExpAdapter extends BaseExpandableListAdapter {
    private ArrayList<String> groupList = null;
    private ArrayList<ArrayList<String>> childList = null;
    private LayoutInflater inflater = null;
    private ViewHolder viewHolder = null;
    public ArrayList<ArrayList<Boolean>> childcheckList = null;

    public OptionExpAdapter(Context c, ArrayList<String> groupList, ArrayList<ArrayList<String>> childList){
        super();
        this.inflater = LayoutInflater.from(c);
        this.groupList = groupList;
        this.childList = childList;
        initChildcheckList();
    }
    //checkbox checked state를 기억하기 위한 childcheckList 변수의 초기화
    public void initChildcheckList(){
        childcheckList = new ArrayList<>();
        for(int i=0 ; i<groupList.size() ; i++ ){
            ArrayList<Boolean> arrList = new ArrayList<>();
            for(int j=0 ; j<childList.get(i).size() ; j++){
                arrList.add(false);
            }
            childcheckList.add(arrList);
        }
    }

    // 그룹 포지션을 반환한다.
    @Override
    public String getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    // 그룹 사이즈를 반환한다.
    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    // 그룹 ID를 반환한다.
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // 그룹뷰 각각의 ROW
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null){
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.apart_optiongroup, parent, false);
            viewHolder.tv_groupName = (TextView) v.findViewById(R.id.tv_group);
            v.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.tv_groupName.setText(getGroup(groupPosition));
        viewHolder.tv_groupName.setPadding(50,0,0,0);

        return v;
    }

    // 차일드뷰를 반환한다.
    @Override
    public String getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    // 차일드뷰 사이즈를 반환한다.
    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    // 차일드뷰 ID를 반환한다.
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    // 차일드뷰 각각의 ROW
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.apart_optiongroup, null);
            viewHolder.tv_childName = (TextView) v.findViewById(R.id.tv_child);
            viewHolder.cb_childBox = (CheckBox) v.findViewById(R.id.chcbox_child);
            v.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.tv_childName.setText(getChild(groupPosition, childPosition));
        viewHolder.cb_childBox.setVisibility(View.VISIBLE);

        viewHolder.cb_childBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                childcheckList.get(groupPosition).set(childPosition, isChecked);
            }
        });
        viewHolder.cb_childBox.setChecked(childcheckList.get(groupPosition).get(childPosition));

        return v;
    }

    @Override
    public boolean hasStableIds() { return true; }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) { return true; }

    class ViewHolder {
        public TextView tv_groupName;
        public TextView tv_childName;
        public CheckBox cb_childBox;
    }

    public String allSelectedOptionMemo(){
        String tot = "";
        for(int i=0 ; i<groupList.size() ; i++ ){
            ArrayList<Boolean> arrList = new ArrayList<>();
            boolean isFirst = false;
            for(int j=0 ; j<childList.get(i).size() ; j++){
                if(childcheckList.get(i).get(j)){
                    if(!isFirst){
                        isFirst = true;
                        tot += groupList.get(i)+":"+childList.get(i).get(j);
                    }
                    else{
                        tot += ", " + childList.get(i).get(j);
                    }
                }
            }
            if(isFirst)    tot += " / ";
            childcheckList.add(arrList);
        }
        return tot.substring(0,tot.length()-3);
    }
}
