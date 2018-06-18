package com.rq.cafeorder.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.rq.cafeorder.R;
import com.rq.cafeorder.orderdetail.OrderDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.bottom_navigationview_main) BottomNavigationViewEx bottomNavigationView;

    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBottomNavigationView();
        mPresenter = new MainPresenter(this, getSupportFragmentManager());
        mPresenter.start();
    }

    private void setBottomNavigationView() {
        bottomNavigationView.setTextVisibility(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.order:
                        Log.d(TAG, "go to order");
                        mPresenter.goToOrder();
                        break;
                    case R.id.order_list:
                        Log.d(TAG, "go to order list");
                        mPresenter.goToOrderList();
                        break;
                }
                return true;
            }
        });
    }

    @OnClick(R.id.button_main_confirm)
    public void confirmButtonPressed() {
        Log.d(TAG, "confirmButtonPressed");
        Intent intent = new Intent(this, OrderDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
