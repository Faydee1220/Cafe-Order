package com.rq.cafeorder.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Faydee on 2018/6/18.
 */
public class Constant {
    public static final String ITEMS = "items";


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Types.ICED,
            Types.HOT})

    public @interface Types {
        int ICED = 0;
        int HOT = 1;
    }
}
