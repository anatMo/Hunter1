package com.example.hunter1.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MSP {

    private final String SP_FILE_NAME = "MY_SP_FILE";
    private SharedPreferences prefs = null;

    private static MSP me;

    private MSP(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void initHelper(Context context) {
        if (me == null) {
            me = new MSP(context);
        }
    }

    public static MSP getMe() {
        return me;
    }

    public void putStringToSP(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringFromSP(String key, String def) {
        return prefs.getString(key, def);
    }
}