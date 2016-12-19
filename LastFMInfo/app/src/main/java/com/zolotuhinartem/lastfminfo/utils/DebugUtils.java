package com.zolotuhinartem.lastfminfo.utils;

import android.util.Log;

/**
 * Created by zolotuhinartem on 17.12.16.
 */

public class DebugUtils {
    public static final String LOG_TAG = "dbg";

    public static void i(Object where, String text){
        Log.i(LOG_TAG, where.getClass().getName() + " # " + text);
    }
}
