package com.example.yoo.s1_drawernavi_lib;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yoo on 2015. 5. 29..
 */
public class OrderFragment extends Fragment {

    public static final String TAG = OrderFragment.class.getCanonicalName();
    //    private HorizontalListView mHorizontalListView = null;
    private OrderAdapter mAdapter = null;
    private  ListView listview_ordered_drink = null;
    private TextView txtview_tot = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        listview_ordered_drink = (ListView) view.findViewById(R.id.order_listview_ordered_drink);
        mAdapter = new OrderAdapter(view.getContext(), 0);
        mAdapter.setOrderFragmentListener(orderFragmentListener);
        listview_ordered_drink.setAdapter(mAdapter);
        txtview_tot = (TextView) view.findViewById(R.id.order_txtview_total);

        return view;
    }


    public void addOrder(Bundle addInfo) {
        mAdapter.add(getSelectedDrink(addInfo));
        orderFragmentListener.updateTotalCount(mAdapter.getCount());
    }


    private Drink getSelectedDrink(Bundle bundle) {
        return new Drink
                (bundle.get("name").toString(),
                        bundle.get("img_src").toString(),
                        bundle.get("option").toString());
    }

    private OrderAdapter.OrderFragmentListener orderFragmentListener = new OrderAdapter.OrderFragmentListener() {
        @Override
        public void updateTotalCount(int count) {
            txtview_tot.setText("Total : "+count);
        }
    };
}
