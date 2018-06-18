package com.rq.cafeorder.order;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rq.cafeorder.R;
import com.rq.cafeorder.databinding.ItemOrderAddedItemsBinding;
import com.rq.cafeorder.model.Constant;
import com.rq.cafeorder.model.Item;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Faydee on 2018/6/18.
 */
public class AddedItemAdapter extends RecyclerView.Adapter<AddedItemAdapter.AddedItemViewHolder> {
    private static final String TAG = AddedItemAdapter.class.getSimpleName();

    private OrderContract.Presenter mPresenter;
    private ArrayList<Item> mItems;

    public AddedItemAdapter(OrderContract.Presenter orderPresenter, ArrayList<Item> items) {
        mPresenter = orderPresenter;
        mItems = items;
    }

    public void addItem(Item item) {
        mItems.add(0, item);
        notifyDataSetChanged();
    }

    public class AddedItemViewHolder extends RecyclerView.ViewHolder {

        private ItemOrderAddedItemsBinding mBinding;

        public AddedItemViewHolder(ItemOrderAddedItemsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            ButterKnife.bind(this, binding.getRoot());
        }

        private void bind(Item item) {
            mBinding.setItem(item);
            loadImage(item.image);
        }

        private void loadImage(String imageUrl) {
            mPresenter.loadImage(imageUrl, mBinding.imageViewOrderAddedItem);
        }

        @OnClick(R.id.radioButton_added_item_iced)
        public void icedPressed() {
//            Log.d(TAG, "icedPressed");
            mItems.get(getAdapterPosition()).type = Constant.Types.ICED;
        }

        @OnClick(R.id.radioButton_added_item_hot)
        public void hotPressed() {
//            Log.d(TAG, "hotPressed");
            mItems.get(getAdapterPosition()).type = Constant.Types.HOT;
        }

        @OnClick(R.id.radioButton_added_item_sugar)
        public void sugarPressed() {
            mItems.get(getAdapterPosition()).isSugar = true;
        }

        @OnClick(R.id.radioButton_added_item_no_sugar)
        public void noSugarPressed() {
            mItems.get(getAdapterPosition()).isSugar = false;
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
