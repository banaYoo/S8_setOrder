package com.example.yoo.s1_drawernavi_lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Yoo on 2015. 8. 23..
 */
public class OrderFormAdapter extends ArrayAdapter<Drink> {
    int viewResource;

    public OrderFormAdapter(Context context, int resource) {

        super(context, resource);
        viewResource = resource;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if(view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(viewResource, parent, false);
            holder.txt_Dname = (TextView) view.findViewById(R.id.txtv_drinkname);
            holder.txt_Option = (TextView) view.findViewById(R.id.txtv_option);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Drink selectedDrink = getItem(position);

        holder.txt_Dname.setText(selectedDrink.getName());
        holder.txt_Option.setText(selectedDrink.getOption());

        return view;
    }

    private static class ViewHolder{
        public TextView txt_Dname;
        public TextView txt_Option;
    }
}

