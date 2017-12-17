package com.rontikeky.mycampus.otpblucampus.Request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anggit on 01/12/2017.
 */

public class SignupRequest {

    @SerializedName("username")
    public String username;
    @SerializedName("nama")
    public String nama;
    @SerializedName("email")
    public String email;
    @SerializedName("tanggal_lahir")
    public String tanggalLahir;
    @SerializedName("alamat")
    public String alamat;
    @SerializedName("telp")
    public String telp;
    @SerializedName("password")
    public String password;

    public SignupRequest(String username, String nama, String email, String tanggalLahir, String alamat, String telp, String password) {
        this.username = username;
        this.nama = nama;
        this.email = email;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.telp = telp;
        this.password = password;
    }
}
