package com.example.testapp.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.testapp.R;

public class SharedPreferencesManager {

    private SharedPreferences prefs;
    private Context context;

    public SharedPreferencesManager(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(this.context.getString(R.string.package_name), context.MODE_PRIVATE);
    }

    public String getSharePrefAttr(String attr){
        return prefs.getString(attr, "");
    }

    public void setSharePrefAttr(String attr, String value){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(attr, value);
        editor.apply();
    }

}
