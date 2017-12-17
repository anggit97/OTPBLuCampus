package com.rontikeky.mycampus.otpblucampus.ResponseError;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Anggit on 05/12/2017.
 */

public class RegisterErrorResponse {
    @SerializedName("username")
    @Expose
    public List<String> username = null;
    @SerializedName("email")
    @Expose
    public List<String> email = null;

    public List<String> getUsername() {
        return username;
    }

    public List<String> getEmail() {
        return email;
    }


}
