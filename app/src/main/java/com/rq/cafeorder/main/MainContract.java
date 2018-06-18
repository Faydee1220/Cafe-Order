package com.rq.cafeorder.main;

import com.rq.cafeorder.BasePresenter;
import com.rq.cafeorder.BaseView;
import com.rq.cafeorder.model.Order;

/**
 * Created by Faydee on 2018/6/18.
 */
public interface MainContract {
    interface Presenter extends BasePresenter {
        void goToOrder();
        void goToOrderList();
        void login();
        Order getOrderData();
    }

    interface View extends BaseView<Presenter> {
        void showOrderDetailUi(Order order);
    }
}
