package com.rq.cafeorder.model;

import java.util.ArrayList;

/**
 * Created by Faydee on 2018/6/18.
 */
public class Order {
    public int orderNumber;
    public String account;
    public long time;
    public int status;
    public ArrayList<Item> contents;
    public int itemCount;
    public int price;
}
