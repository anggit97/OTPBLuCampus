package com.rontikeky.mycampus.otpblucampus.Request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anggit on 16/12/2017.
 */

public class PresenceRequest {

    @SerializedName("kode")
    public String kode;
    @SerializedName("id_acara")
    public String idAcara;

    public PresenceRequest(String kode, String idAcara) {
        this.kode = kode;
        this.idAcara = idAcara;
    }
}
