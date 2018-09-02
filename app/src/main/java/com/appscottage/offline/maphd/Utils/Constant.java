package com.appscottage.offline.maphd.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Jointech on 5/9/2017.
 */

public class Constant {
    public static int countiesadsflag=0;

    public static Typeface headingTypeface(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/njnaruto.ttf");
    }

    public static Typeface simpleTypeface(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/Adler.ttf");
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
