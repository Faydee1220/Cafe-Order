package com.rq.cafeorder.order;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.rq.cafeorder.BasePresenter;
import com.rq.cafeorder.BaseView;

/**
 * Created by Faydee on 2018/6/18.
 */
public interface OrderContract {
    interface Presenter extends BasePresenter {
        void loadItems();
        void loadImage(String imageUrl, ImageView imageView);
    }

    interface View extends BaseView<OrderContract.Presenter> {
        Fragment getFragment();
    }
}
