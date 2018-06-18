package com.rq.cafeorder.main;

import com.rq.cafeorder.BasePresenter;
import com.rq.cafeorder.BaseView;

/**
 * Created by Faydee on 2018/6/18.
 */
public interface MainContract {
    interface Presenter extends BasePresenter {
        void goToOrder();
        void goToOrderList();
        void login();
    }

    interface View extends BaseView<Presenter> {

    }
}
