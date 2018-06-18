package com.rq.cafeorder.order;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Faydee on 2018/6/18.
 */
public class AllItemsAdapter extends RecyclerView.Adapter<AllItemsAdapter.AllItemsViewHolder> {
    
    public class AllItemsViewHolder extends RecyclerView.ViewHolder {

        public AllItemsViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public AllItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AllItemsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
