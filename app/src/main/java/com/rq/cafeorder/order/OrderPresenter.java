package com.rq.cafeorder.order;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Faydee on 2018/6/18.
 */
public class OrderPresenter implements OrderContract.Presenter {
    private static final String TAG = OrderPresenter.class.getSimpleName();

    private OrderContract.View mOrderView;
    private FirebaseFirestore mDb;

    public OrderPresenter(OrderContract.View orderView) {
        mOrderView = checkNotNull(orderView, "orderView cannot be null!");
        mOrderView.setPresenter(this);
    }

    @Override
    public void loadItems() {
        mDb.collection("items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public void start() {
        mDb = FirebaseFirestore.getInstance();
    }
}
