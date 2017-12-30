package com.rontikeky.mycampus.otpblucampus.Response;

import com.google.gson.annotations.SerializedName;
import com.rontikeky.mycampus.otpblucampus.ResponseError.RegisterErrorResponse;

import java.util.List;

/**
 * Created by Anggit on 01/12/2017.
 */

public class SignupResponse {

    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public String message;

    public SignupResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

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
}
