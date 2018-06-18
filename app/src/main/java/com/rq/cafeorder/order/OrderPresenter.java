package com.rq.cafeorder.order;

import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rq.cafeorder.R;
import com.rq.cafeorder.model.Constant;
import com.rq.cafeorder.model.Item;
import com.rq.cafeorder.model.Order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.rq.cafeorder.model.Constant.ITEMS;

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
        mDb.collection(ITEMS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Item> items = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData() + "\n");
                                Item item = document.toObject(Item.class);
                                items.add(item);
//                                Log.d(TAG, "name: " + item.name);
                            }
                            mOrderView.showItems(items);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public void loadImage(String imageUrl, ImageView imageView) {
        RequestOptions options = new RequestOptions();
        options = options.fitCenter();
        options = options.placeholder(R.drawable.ic_local_cafe);
//        Log.d(TAG, "loadImage image: " + imageUrl);
        Glide.with(mOrderView.getFragment())
                .load(imageUrl)
                .apply(options)
                .into(imageView);
    }

    @Override
    public int getColumns() {
        DisplayMetrics displayMetrics = mOrderView.getFragment().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 150);
    }

    @Override
    public Order getOrderData() {
        ArrayList<Item> addedItems = mOrderView.getAddedItems();

//        List<Item> espressoItems = addedItems.stream()
//                .filter(item -> item.name.contains(Constant.CafeNames.ESPRESSO))
//                .collect(Collectors.toList());
//
//        List<Item> americanoItems = addedItems.stream()
//                .filter(item -> item.name.contains(Constant.CafeNames.AMERICANO))
//                .collect(Collectors.toList());
//
//        List<Item> latteItems = addedItems.stream()
//                .filter(item -> item.name.contains(Constant.CafeNames.LATTE))
//                .collect(Collectors.toList());
//
//        List<Item> mochaItems = addedItems.stream()
//                .filter(item -> item.name.contains(Constant.CafeNames.MOCHA))
//                .collect(Collectors.toList());
//
//        List<Item> singleOriginItems = addedItems.stream()
//                .filter(item -> item.name.contains(Constant.CafeNames.SINGLE_ORIGIN))
//                .collect(Collectors.toList());
//
//        List<Item> blackTeaItems = addedItems.stream()
//                .filter(item -> item.name.contains(Constant.CafeNames.BLACK_TEA))
//                .collect(Collectors.toList());
//
//        List<Item> greenTeaItems = addedItems.stream()
//                .filter(item -> item.name.contains(Constant.CafeNames.GREEN_TEA))
//                .collect(Collectors.toList());

        Log.d(TAG, "getOrderData");

        List<Item> espressoItems = new ArrayList<>();
        List<Item> americanoItems = new ArrayList<>();
        List<Item> latteItems = new ArrayList<>();
        List<Item> mochaItems = new ArrayList<>();
        List<Item> singleOriginItems = new ArrayList<>();
        List<Item> blackTeaItems = new ArrayList<>();
        List<Item> greenTeaItems = new ArrayList<>();

        for (Item item: addedItems) {
            item.cups += 1;
            switch (item.name) {
                case Constant.CafeNames.ESPRESSO:
                    espressoItems.add(item);
                    break;
                case Constant.CafeNames.AMERICANO:
                    americanoItems.add(item);
                    break;
                case Constant.CafeNames.LATTE:
                    latteItems.add(item);
                    break;
                case Constant.CafeNames.MOCHA:
                    mochaItems.add(item);
                    break;
                case Constant.CafeNames.SINGLE_ORIGIN:
                    singleOriginItems.add(item);
                    break;
                case Constant.CafeNames.BLACK_TEA:
                    blackTeaItems.add(item);
                    break;
                case Constant.CafeNames.GREEN_TEA:
                    greenTeaItems.add(item);
                    break;
            }

        }

        Order order = new Order();
        List<Item> contents = new ArrayList<>();

        return null;
    }

    @Override
    public void addItem(Item item) {
        mOrderView.showAddedItem(item);
    }

    @Override
    public void start() {
        mDb = FirebaseFirestore.getInstance();
        loadItems();
    }
}
