package com.rontikeky.mycampus.otpblucampus.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anggit on 01/12/2017.
 */

public class SigninResponse {

    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public String message;
    @SerializedName("userId")
    public String   userId;
    @SerializedName("otp")
    public String otp;
    @SerializedName("email")
    public String email;
    @SerializedName("telp")
    public String telp;
    @SerializedName("nama")
    public String nama;

    public SigninResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public String getOtp() {
        return otp;
    }

    public String getEmail() {
        return email;
    }

    public String getTelp() {
        return telp;
    }

    public String getNama() {
        return nama;
    }
}
