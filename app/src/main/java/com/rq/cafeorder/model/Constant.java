package com.rq.cafeorder.model;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Faydee on 2018/6/18.
 */
public class Constant {
    public static final String ITEMS = "items";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({CafeNames.ESPRESSO,
            CafeNames.AMERICANO,
            CafeNames.LATTE,
            CafeNames.MOCHA,
            CafeNames.SINGLE_ORIGIN,
            CafeNames.BLACK_TEA,
            CafeNames.GREEN_TEA
    })

    public @interface CafeNames {
        String ESPRESSO = "Espresso";
        String AMERICANO = "Americano";
        String LATTE = "Latte";
        String MOCHA = "Mocha";
        String SINGLE_ORIGIN = "Single Origin";
        String BLACK_TEA = "Black Tea";
        String GREEN_TEA = "Green Tea";
    }
}
