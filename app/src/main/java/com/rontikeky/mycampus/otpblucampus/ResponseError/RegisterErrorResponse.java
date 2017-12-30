package com.rontikeky.mycampus.otpblucampus.ResponseError;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Anggit on 05/12/2017.
 */

public class RegisterErrorResponse {
    @SerializedName("errors")
    public Errors errors;

    public static class Errors {
        @SerializedName("username")
        public List<String> username;
        @SerializedName("email")
        public List<String> email;

        public List<String> getUsername() {
            return username;
        }

        public List<String> getEmail() {
            return email;
        }
    }

    public Errors getErrors() {
        return errors;
    }

    //    @SerializedName("username")
//    @Expose
//    public List<String> username = null;
//    @SerializedName("email")
//    @Expose
//    public List<String> email = null;
//
//    public List<String> getUsername() {
//        return username;
//    }
//
//    public List<String> getEmail() {
//        return email;
//    }


}
