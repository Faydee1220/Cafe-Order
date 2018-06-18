package com.rq.cafeorder.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rq.cafeorder.MyApplication;
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
    @BindView(R.id.recyclerview_order_added_items) RecyclerView addedItemsRecyclerView;

    private OrderContract.Presenter mPresenter;
    private AllItemsAdapter mAllItemsAdapter;
    private AddedItemAdapter mAddedItemAdapter;

    public OrderFragment() {}

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAllItemsAdapter = new AllItemsAdapter(mPresenter, new ArrayList<Item>());
        mAddedItemAdapter = new AddedItemAdapter(mPresenter, new ArrayList<Item>());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);
        setAllItemsUi();
        setAddedItemsUi();
        return view;
    }

    private void setAddedItemsUi() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getAppContext());
        addedItemsRecyclerView.setLayoutManager(layoutManager);
        addedItemsRecyclerView.setAdapter(mAddedItemAdapter);

        // 設定分隔線
        addedItemsRecyclerView.addItemDecoration(new DividerItemDecoration(
                MyApplication.getAppContext(), DividerItemDecoration.VERTICAL));
    }

    private void setAllItemsUi() {
        int columns = mPresenter.getColumns();
//        Log.d(TAG, "columns: " + columns);
        GridLayoutManager layoutManager = new GridLayoutManager(
                getActivity(),
                columns,
                LinearLayoutManager.HORIZONTAL,
                false);
        setItemSpace(columns);
        allItemsRecyclerView.setLayoutManager(layoutManager);
        allItemsRecyclerView.setAdapter(mAllItemsAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.start();
    }

    @Override
    public void setPresenter(OrderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showItems(ArrayList<Item> items) {
        mAllItemsAdapter.updateItems(items);
    }

    @Override
    public void showAddedItem(Item item) {
        mAddedItemAdapter.addItem(item);
    }

    @Override
    public void setItemSpace(int columns) {
        float pixel = DpTool.convertDpToPixel(8, getActivity());
        int spacing = Math.round(pixel);
        boolean includeEdge = false;
        allItemsRecyclerView.addItemDecoration(new GridSpacingItemDecoration(
                columns, spacing, includeEdge));
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public ArrayList<Item> getAddedItems() {
        return mAddedItemAdapter.getAddedItems();
    }
}
