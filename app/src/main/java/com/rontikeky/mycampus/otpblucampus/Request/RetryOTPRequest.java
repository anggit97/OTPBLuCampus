package com.rontikeky.mycampus.otpblucampus.Request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anggit on 06/12/2017.
 */

public class RetryOTPRequest {
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;

    public RetryOTPRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
