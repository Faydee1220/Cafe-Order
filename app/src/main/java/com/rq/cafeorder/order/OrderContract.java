package com.rq.cafeorder.order;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.rq.cafeorder.BasePresenter;
import com.rq.cafeorder.BaseView;
import com.rq.cafeorder.model.Item;

import java.util.ArrayList;

/**
 * Created by Faydee on 2018/6/18.
 */
public interface OrderContract {
    interface Presenter extends BasePresenter {
        void loadItems();
        void loadImage(String imageUrl, ImageView imageView);

        int getColumns();
    }

    interface View extends BaseView<OrderContract.Presenter> {
        void showItems(ArrayList<Item> items);
        Fragment getFragment();
    }
}
