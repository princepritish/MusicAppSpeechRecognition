package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;

public class User {
    Context context;
    SharedPreferences sharedPreferences;
    public void removeuser()
    {
        sharedPreferences.edit().clear().apply();
    }

    public String getUsername() {
        username=sharedPreferences.getString("Userdata","");
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        sharedPreferences.edit().putString("Userdata",username).apply();
      //  sharedPreferences.edit().putString(Constants.KEY_EMAIL,username).apply();
    }
    public void setLang(String lang){
        this.lang=lang;
        sharedPreferences.edit().putString("Lang",lang).apply();
    }
    public String getLang() {
        this.lang=sharedPreferences.getString("Lang","");
        return lang;
    }
    private String username;
    private String lang;

    public User(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
    }
}