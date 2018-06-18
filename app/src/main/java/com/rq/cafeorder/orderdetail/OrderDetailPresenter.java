package com.rq.cafeorder.orderdetail;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Faydee on 2018/6/18.
 */
public class OrderDetailPresenter implements OrderDetailContract.Presenter {

    private OrderDetailContract.View mOrderDetailView;

    public OrderDetailPresenter(OrderDetailContract.View orderDetailView) {
        mOrderDetailView = checkNotNull(orderDetailView, "orderDetailView cannot be null!");
        mOrderDetailView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
