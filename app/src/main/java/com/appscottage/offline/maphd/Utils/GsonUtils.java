package com.appscottage.offline.maphd.Utils;

import com.google.gson.Gson;

public class GsonUtils {
    private static GsonUtils sInstance;
    private Gson mGson;

    private GsonUtils() {
        this.mGson = new Gson();
    }

    public static GsonUtils getInstance() {
        if (sInstance == null) {
            sInstance = new GsonUtils();
        }
        return sInstance;
    }

    public String toJson(Object object) {
        return this.mGson.toJson(object);
    }

    public Gson getGson() {
        return this.mGson;
    }
}
