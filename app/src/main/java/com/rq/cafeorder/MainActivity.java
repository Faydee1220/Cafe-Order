package com.rq.cafeorder;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.bottom_navigationview_main) BottomNavigationViewEx bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBottomNavigationView();
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
                        break;
                    case R.id.order_list:
                        Log.d(TAG, "go to order list");
                        break;
                }
                return true;
            }
        });
    }

    @OnClick(R.id.button_main_confirm)
    public void confirmButtomPressed() {
        Log.d(TAG, "confirmButtomPressed");
    }
}
