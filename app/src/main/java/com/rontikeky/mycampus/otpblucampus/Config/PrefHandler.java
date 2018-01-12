package com.rontikeky.mycampus.otpblucampus.Config;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Anggit on 19/09/2017.
 */

public class PrefHandler {
    public static SharedPreferences.Editor editor;
    public static SharedPreferences sharedPreferences;
    public Context context;

    int PRIVATE_MODE = 0;

    private static final String SHAREDPREFNAME = "token";

    private static final String IS_LOGIN    = "isLoggin";
    private static final String ID_KEY      =   "idkey";
    private static final String IS_PASS_OTP =   "ispassotp";
    private static final String EMAIL_KEY   =   "emailkey";
    private static final String TELP_KEY    =   "telpkey";
    private static final String PASS_KEY    =   "passkey";
    private static final String NAME_KEY    =   "namekey";

    public PrefHandler(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHAREDPREFNAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public static void init(Context context){
        if (sharedPreferences == null){
                sharedPreferences   =   context.getSharedPreferences(SHAREDPREFNAME, Activity.MODE_PRIVATE);
                editor              =   sharedPreferences.edit();
        }
    }

    public static void setId(String id){
        editor.putString(ID_KEY,id);
        editor.commit();
    }

    public static String getId(){
        return sharedPreferences.getString(ID_KEY,"");
    }

    public static void setLogout(){
        editor.clear();
        editor.commit();
    }

    public static void setEmailKey(String email){
        editor.putString(EMAIL_KEY,email);
        editor.commit();
    }

    public static String getEmailKey(){
        return sharedPreferences.getString(EMAIL_KEY,"");
    }

    public static void setTelpKey(String telp){
        editor.putString(TELP_KEY,telp);
        editor.commit();
    }

    public static String getTelpKey(){
        return sharedPreferences.getString(TELP_KEY,"");
    }

    public static void setIsPassOtp(String isPassOtp){
        editor.putString(IS_PASS_OTP,isPassOtp);
        editor.commit();
    }

    public static String getIsPassOtp(){
        return sharedPreferences.getString(IS_PASS_OTP,"");
    }

    public static void setPassKey(String passKey){
        editor.putString(PASS_KEY,passKey);
        editor.commit();
    }

    public static String getNameKey() {
        return sharedPreferences.getString(NAME_KEY,"");
    }

    public static void setNameKey(String nameKey){
        editor.putString(NAME_KEY,nameKey);
        editor.commit();
    }

    public static String getPassKey(){
        return sharedPreferences.getString(PASS_KEY,"");
    }
}
