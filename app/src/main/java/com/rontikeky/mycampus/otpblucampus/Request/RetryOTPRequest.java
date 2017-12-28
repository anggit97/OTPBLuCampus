package com.rontikeky.mycampus.otpblucampus.Request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anggit on 06/12/2017.
 */

public class RetryOTPRequest {
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;

    public RetryOTPRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
