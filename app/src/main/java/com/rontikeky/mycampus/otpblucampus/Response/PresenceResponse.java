package com.rontikeky.mycampus.otpblucampus.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anggit on 16/12/2017.
 */

public class PresenceResponse {

    @SerializedName("status")
    public String status;

    public String getStatus() {
        return status;
    }
}
