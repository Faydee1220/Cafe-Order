package com.rq.cafeorder.main;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rq.cafeorder.R;
import com.rq.cafeorder.main.MainContract.Presenter;
import com.rq.cafeorder.order.OrderFragment;
import com.rq.cafeorder.order.OrderPresenter;
import com.rq.cafeorder.orderlist.OrderListFragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Faydee on 2018/6/18.
 */
public class MainPresenter implements Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private MainContract.View mMainView;
    private FragmentManager mFragmentManager;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            ORDER, ORDER_LIST, ORDER_DETAIL
    })
    public @interface FragmentType {
    }
    public static final String ORDER = "ORDER";
    public static final String ORDER_LIST = "ORDER_LIST";
    public static final String ORDER_DETAIL = "ORDER_DETAIL";

    private OrderFragment mOrderFragment;
    private OrderListFragment mOrderListFragment;

    private OrderPresenter mOrderPresenter;

    private FirebaseAuth mAuth;

    public MainPresenter(MainContract.View view, FragmentManager fragmentManager) {
        mMainView = checkNotNull(view, "mainView cannot be null!");
        mMainView.setPresenter(this);

        mFragmentManager = fragmentManager;
    }

    @Override
    public void goToOrder() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mOrderFragment == null) mOrderFragment = OrderFragment.newInstance();
        if (mOrderListFragment != null) transaction.hide(mOrderListFragment);
        if (!mOrderFragment.isAdded()) {
            transaction.add(R.id.relativeLayout_main_container, mOrderFragment, ORDER);
        } else {
            transaction.show(mOrderFragment);
        }
        transaction.commit();

        if (mOrderPresenter == null) {
            mOrderPresenter = new OrderPresenter(mOrderFragment);
        }
    }

    @Override
    public void goToOrderList() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mOrderListFragment == null) mOrderListFragment = OrderListFragment.newInstance();
        if (mOrderFragment != null) transaction.hide(mOrderFragment);
        if (!mOrderListFragment.isAdded()) {
            transaction.add(R.id.relativeLayout_main_container, mOrderListFragment, ORDER_LIST);
        } else {
            transaction.show(mOrderListFragment);
        }
        transaction.commit();
    }

    @Override
    public void login() {
        mAuth.signInWithEmailAndPassword("wayne.chen@awscafe.tw", "123456")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    @Override
    public void start() {
        mAuth = FirebaseAuth.getInstance();
        login();
        goToOrder();
    }
}
