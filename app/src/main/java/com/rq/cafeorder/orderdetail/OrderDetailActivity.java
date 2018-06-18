package com.rq.cafeorder.orderdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.rq.cafeorder.R;

import static com.rq.cafeorder.main.MainPresenter.ORDER_DETAIL;

/**
 * Created by Faydee on 2018/6/18.
 */
public class OrderDetailActivity extends AppCompatActivity {

    private OrderDetailFragment mOrderDetailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        goToDetail();
    }

    private void goToDetail() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mOrderDetailFragment == null) mOrderDetailFragment = OrderDetailFragment.newInstance();
        if (!mOrderDetailFragment.isAdded()) {
            transaction.add(R.id.relativeLayout_order_detail_container, mOrderDetailFragment, ORDER_DETAIL);
        } else {
            transaction.show(mOrderDetailFragment);
        }
        transaction.commit();
    }
}
