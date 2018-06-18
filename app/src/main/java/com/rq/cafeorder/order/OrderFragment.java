package com.rq.cafeorder.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rq.cafeorder.R;
import com.rq.cafeorder.model.Item;
import com.rq.cafeorder.tool.DpTool;
import com.rq.cafeorder.tool.GridSpacingItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Faydee on 2018/6/18.
 */
public class OrderFragment extends Fragment implements OrderContract.View {
    private static final String TAG = OrderFragment.class.getSimpleName();

    @BindView(R.id.recyclerview_order_all_items) RecyclerView allItemsRecyclerView;

    private OrderContract.Presenter mPresenter;
    private AllItemsAdapter mAdapter;

    public OrderFragment() {}

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new AllItemsAdapter(mPresenter, new ArrayList<Item>());
    }

    private void setItemSpace(int columns) {
        float pixel = DpTool.convertDpToPixel(8, getActivity());
        int spacing = Math.round(pixel);
//        Log.d(TAG, "pixel:" + String.valueOf(pixel));
//        int spacing = 20;
        boolean includeEdge = false;
        allItemsRecyclerView.addItemDecoration(new GridSpacingItemDecoration(
                columns, spacing, includeEdge));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);

        int columns = mPresenter.getColumns();
//        Log.d(TAG, "columns: " + columns);
        GridLayoutManager layoutManager = new GridLayoutManager(
                getActivity(),
                columns,
                LinearLayoutManager.HORIZONTAL,
                false);
        setItemSpace(columns);
        allItemsRecyclerView.setLayoutManager(layoutManager);
        allItemsRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.start();
        mPresenter.loadItems();
    }

    @Override
    public void setPresenter(OrderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showItems(ArrayList<Item> items) {
        mAdapter.updateItems(items);

    }

    @Override
    public Fragment getFragment() {
        return this;
    }
}
