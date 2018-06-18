package com.rq.cafeorder.order;

import com.rq.cafeorder.BasePresenter;
import com.rq.cafeorder.BaseView;

/**
 * Created by Faydee on 2018/6/18.
 */
public interface OrderContract {
    interface Presenter extends BasePresenter {
        void loadItems();
    }

    interface View extends BaseView<OrderContract.Presenter> {

    }
}
