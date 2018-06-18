package com.rq.cafeorder.order;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rq.cafeorder.databinding.ItemOrderAddedItemsBinding;
import com.rq.cafeorder.model.Item;

import java.util.ArrayList;

/**
 * Created by Faydee on 2018/6/18.
 */
public class AddedItemAdapter extends RecyclerView.Adapter<AddedItemAdapter.AddedItemViewHolder> {

    private OrderContract.Presenter mPresenter;
    private ArrayList<Item> mItems;

    public AddedItemAdapter(OrderContract.Presenter orderPresenter, ArrayList<Item> items) {
        mPresenter = orderPresenter;
        mItems = items;
    }

    public void updateItems(ArrayList<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public class AddedItemViewHolder extends RecyclerView.ViewHolder {

        private ItemOrderAddedItemsBinding mBinding;

        public AddedItemViewHolder(ItemOrderAddedItemsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(Item item) {
            mBinding.setItem(item);
            loadImage(item.image);
        }

        private void loadImage(String imageUrl) {
            mPresenter.loadImage(imageUrl, mBinding.imageViewOrderAddedItem);
        }
    }

    @NonNull
    @Override
    public AddedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemOrderAddedItemsBinding binding = ItemOrderAddedItemsBinding.inflate(
                inflater, parent, false);
        AddedItemViewHolder viewHolder = new AddedItemViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddedItemViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
