package com.rq.cafeorder.main;

import android.support.annotation.StringDef;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.rq.cafeorder.main.MainContract.Presenter;

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
            ORDER, ORDER_LIST
    })
    public @interface FragmentType {
    }
    public static final String ORDER = "ORDER";
    public static final String ORDER_LIST = "ORDER_LIST";

    public MainPresenter(MainContract.View view, FragmentManager fragmentManager) {
        mMainView = checkNotNull(view, "mainView cannot be null!");
        mMainView.setPresenter(this);

        mFragmentManager = fragmentManager;
    }

    @Override
    public void goToOrder() {
//        FragmentTransaction transaction = mFragmentManager.beginTransaction();
    }

    @Override
    public void goToOrderList() {

    }

    @Override
    public void start() {

    }
}
