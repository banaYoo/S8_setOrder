package com.example.yoo.s1_drawernavi_lib;

import android.content.Context;
        import android.graphics.drawable.Drawable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
        import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

        import java.util.ArrayList;

/**
 * Created by Yoo on 2015. 6. 24..
 */
public class OrderAdapter extends ArrayAdapter<Drink> {

    public OrderAdapter(Context context, int resource) {
        super(context, resource);
    }

    private OrderFragmentListener orderFragmentListener;

    public void setOrderFragmentListener(OrderFragmentListener orderFragmentListener) {
        this.orderFragmentListener = orderFragmentListener;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if(view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apart_orderitem, parent, false);
            viewHolder.txtview_name = (TextView) view.findViewById(R.id.orderitem_txtview_name);
            viewHolder.txtview_option = (TextView) view.findViewById(R.id.orderitem_txtview_option);
            viewHolder.imgbutton_remove = (ImageButton) view.findViewById(R.id.orderitem_imgbutton_remove);
            viewHolder.imgbutton_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();
                    remove(getItem(position));
                    orderFragmentListener.updateTotalCount(getCount());
                }
            });

            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Drink drink = getItem(position);
        viewHolder.imgbutton_remove.setTag(position);
        viewHolder.txtview_name.setText(drink.name);
        viewHolder.txtview_option.setText(drink.option);

        return view;
    }

    public class ViewHolder{
        public TextView txtview_name;
        public TextView txtview_option;
        public ImageButton imgbutton_remove;
    }

    public interface OrderFragmentListener{
        public void updateTotalCount(int count);
    }
}

