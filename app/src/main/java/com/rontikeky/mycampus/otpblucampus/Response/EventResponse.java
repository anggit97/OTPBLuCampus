package com.rontikeky.mycampus.otpblucampus.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Anggit on 14/12/2017.
 */

public class EventResponse {

    @SerializedName("current_page")
    public int currentPage;
    @SerializedName("data")
    public List<Data> data;
    @SerializedName("first_page_url")
    public String firstPageUrl;
    @SerializedName("from")
    public int from;
    @SerializedName("last_page")
    public int lastPage;
    @SerializedName("last_page_url")
    public String lastPageUrl;
    @SerializedName("next_page_url")
    public String nextPageUrl;
    @SerializedName("path")
    public String path;
    @SerializedName("per_page")
    public int perPage;
    @SerializedName("prev_page_url")
    public String prevPageUrl;
    @SerializedName("to")
    public int to;
    @SerializedName("total")
    public int total;


    public static class Data {
        @SerializedName("id")
        public int id;
        @SerializedName("id_user")
        public String idUser;
        @SerializedName("judul_acara")
        public String judulAcara;
        @SerializedName("isi_acara")
        public String isiAcara;
        @SerializedName("tempat_acara")
        public String tempatAcara;
        @SerializedName("contact_person_acara")
        public String contactPersonAcara;
        @SerializedName("jumlah_peserta")
        public String jumlahPeserta;
        @SerializedName("tanggal_acara")
        public String tanggalAcara;
        @SerializedName("jam_acara")
        public String jamAcara;
        @SerializedName("foto_acara")
        public String fotoAcara;
        @SerializedName("status_acara")
        public String statusAcara;

        public Data(int id, String idUser, String judulAcara, String isiAcara, String tempatAcara, String contactPersonAcara, String jumlahPeserta, String tanggalAcara, String jamAcara, String fotoAcara, String statusAcara) {
            this.id = id;
            this.idUser = idUser;
            this.judulAcara = judulAcara;
            this.isiAcara = isiAcara;
            this.tempatAcara = tempatAcara;
            this.contactPersonAcara = contactPersonAcara;
            this.jumlahPeserta = jumlahPeserta;
            this.tanggalAcara = tanggalAcara;
            this.jamAcara = jamAcara;
            this.fotoAcara = fotoAcara;
            this.statusAcara = statusAcara;
        }

        public int getId() {
            return id;
        }

        public String getIdUser() {
            return idUser;
        }

        public String getJudulAcara() {
            return judulAcara;
        }

        public String getIsiAcara() {
            return isiAcara;
        }

        public String getTempatAcara() {
            return tempatAcara;
        }

        public String getContactPersonAcara() {
            return contactPersonAcara;
        }

        public String getJumlahPeserta() {
            return jumlahPeserta;
        }

        public String getTanggalAcara() {
            return tanggalAcara;
        }

        public String getJamAcara() {
            return jamAcara;
        }

        public String getFotoAcara() {
            return fotoAcara;
        }

        public String getStatusAcara() {
            return statusAcara;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<Data> getData() {
        return data;
    }

    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    public int getFrom() {
        return from;
    }

    public int getLastPage() {
        return lastPage;
    }

    public String getLastPageUrl() {
        return lastPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public String getPath() {
        return path;
    }

    public int getPerPage() {
        return perPage;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public int getTo() {
        return to;
    }

    public int getTotal() {
        return total;
    }
}
