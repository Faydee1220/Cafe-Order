package com.rq.cafeorder.model;

/**
 * Created by Faydee on 2018/6/18.
 */
public class Item {
    public String name;
    public int price;
    public String image;
    public int type = Constant.Types.ICED;
    public boolean isSugar = true;

    public Item() {}

    public boolean isIced() {
        if (type == Constant.Types.ICED) {
            return true;
        }
        return false;
    }
}
