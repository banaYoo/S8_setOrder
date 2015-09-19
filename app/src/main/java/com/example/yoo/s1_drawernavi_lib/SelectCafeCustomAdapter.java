package com.example.yoo.s1_drawernavi_lib;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

/**
 * Created by Yoo on 2015. 9. 18..
 */
public class SelectCafeCustomAdapter extends BaseAdapter {
    private Context mContext;
    public ArrayList<SelectCafeInfo> mArrayList;

    public SelectCafeCustomAdapter(Context mContext){
        super();
        this.mContext = mContext;
        mArrayList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(Drawable logo, String cafename, String update){
        SelectCafeInfo cafeInfo = new SelectCafeInfo();
        cafeInfo.mLogo = logo;
        cafeInfo.mCafename = cafename;
        cafeInfo.mUpdate = update;

        mArrayList.add(cafeInfo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.apart_selectcafe_customlistviewitem, parent, false);

            holder.imgview_cafelogo = (ImageView) convertView.findViewById(R.id.selectcafe_listviewitem_imageview_cafebrand);
            holder.txtView_cafename = (TextView) convertView.findViewById(R.id.selectcafe_listviewitem_txtview_cafename);
            holder.txtView_update = (TextView) convertView.findViewById(R.id.selectcafe_listviewitem_txtview_update);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        SelectCafeInfo selectCafeInfo = mArrayList.get(position);
        if(selectCafeInfo != null){
            holder.imgview_cafelogo.setImageDrawable(selectCafeInfo.mLogo);
            holder.txtView_cafename.setText(selectCafeInfo.mCafename);
            holder.txtView_update.setText(selectCafeInfo.mUpdate);
        }

        return convertView;
    }

    public class ViewHolder{
        public ImageView imgview_cafelogo;
        public TextView txtView_cafename;
        public TextView txtView_update;
    }
}
