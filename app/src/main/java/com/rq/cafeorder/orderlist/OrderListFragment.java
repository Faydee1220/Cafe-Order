package com.rq.cafeorder.orderlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rq.cafeorder.R;

/**
 * Created by Faydee on 2018/6/18.
 */
public class OrderListFragment extends Fragment {
    public OrderListFragment() {}

    public static OrderListFragment newInstance() {
        return new OrderListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);
        return view;
    }
}
