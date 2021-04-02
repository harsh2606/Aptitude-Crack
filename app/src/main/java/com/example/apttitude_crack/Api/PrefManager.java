package com.example.apttitude_crack.Api;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "aptitude_app";


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setBoolean(String PREF_NAME, Boolean val) {
        editor.putBoolean(PREF_NAME, val);
        editor.commit();
    }

    public void setString(String PREF_NAME, String VAL) {
        editor.putString(PREF_NAME, VAL);
        editor.commit();
    }

    public void setInt(String PREF_NAME, int VAL) {
        editor.putInt(PREF_NAME, VAL);
        editor.commit();
    }

    public boolean getBoolean(String PREF_NAME) {
        return pref.getBoolean(PREF_NAME, true);
    }

    public void remove(String PREF_NAME) {
        if (pref.contains(PREF_NAME)) {
            editor.remove(PREF_NAME);
            editor.commit();
        }
    }

    public String getString(String PREF_NAME) {
        if (pref.contains(PREF_NAME)) {
            return pref.getString(PREF_NAME, null);
        }

        return null;
    }

    public String getString(String PREF_NAME, String DefaultVal) {
        if (pref.contains(PREF_NAME)) {
            return pref.getString(PREF_NAME, null);
        }
        return DefaultVal;
    }

    public int getInt(String key) {
        return pref.getInt(key, 0);
    }

    public void setLong(String PREF_NAME, long VAL) {
        editor.putLong(PREF_NAME, VAL);
        editor.commit();
    }

    public long getLong(String key) {
        return pref.getLong(key, 0);
    }

    public void setFloat(String PREF_NAME, float VAL) {
        editor.putFloat(PREF_NAME, VAL);
        editor.commit();
    }

    public float getFloat(String key) {
        return pref.getFloat(key, 0.0f);
    }
}
