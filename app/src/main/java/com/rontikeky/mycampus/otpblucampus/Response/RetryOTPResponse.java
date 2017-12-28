package com.rontikeky.mycampus.otpblucampus.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anggit on 06/12/2017.
 */

public class RetryOTPResponse {
    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public String message;
    @SerializedName("otp")
    public String otp;

    public RetryOTPResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getOtp() {
        return otp;
    }

}
