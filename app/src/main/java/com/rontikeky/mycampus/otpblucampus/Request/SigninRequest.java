package com.rontikeky.mycampus.otpblucampus.Request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anggit on 01/12/2017.
 */

public class SigninRequest {

    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;

    public SigninRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
