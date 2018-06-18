package com.rq.cafeorder.order;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rq.cafeorder.R;
import com.rq.cafeorder.databinding.ItemOrderAllItemsBinding;
import com.rq.cafeorder.model.Item;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Faydee on 2018/6/18.
 */
public class AllItemsAdapter extends RecyclerView.Adapter<AllItemsAdapter.AllItemsViewHolder> {
    private static final String TAG = AllItemsAdapter.class.getSimpleName();

    private OrderContract.Presenter mPresenter;
    private ArrayList<Item> mItems;

    public AllItemsAdapter(OrderContract.Presenter orderPresenter, ArrayList<Item> items) {
        mPresenter = orderPresenter;
        mItems = items;
    }

    public void updateItems(ArrayList<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public class AllItemsViewHolder extends RecyclerView.ViewHolder {

        private ItemOrderAllItemsBinding mBinding;

        public AllItemsViewHolder(ItemOrderAllItemsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            ButterKnife.bind(this, binding.getRoot());
        }

        private void bind(Item item) {
            mBinding.setItem(item);
            loadImage(item.image);
        }

        private void loadImage(String imageUrl) {
            mPresenter.loadImage(imageUrl, mBinding.imageViewOrderCafe);
        }

        @OnClick(R.id.imageView_order_cafe)
        public void cafeImagePressed() {
            Log.d(TAG, "cafeImagePressed: " + mItems.get(getAdapterPosition()).name);
            mPresenter.addItem(mItems.get(getAdapterPosition()));
        }
    }

    @NonNull
    @Override
    public AllItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemOrderAllItemsBinding binding = ItemOrderAllItemsBinding.inflate(
                inflater, parent, false);
        AllItemsViewHolder viewHolder = new AllItemsViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllItemsViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


}
