package com.rontikeky.mycampus.otpblucampus.Algorithm;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;


public class DiffieHelmanGenerator {
    public static String diffieHKeyGenerator(){
        try {
            /* pertama buat nilai bilangan prima yang besar yg telah disepakati oleh 2 orang yang akan berkomunikasi
               yaitu n dan g, tapi g < n. n dan g ini kita buat isinya dari tipe data long(n1,g1) agar dapat ditampung oleh
               fungsi BigInteger yg nantinya akan menampung isi bilangan yg sangat besar.
            */
            long n1 = 331;
            BigInteger n = BigInteger.valueOf(n1);
            System.out.println("nilai n = "+n);

            long g1 = 2;
            BigInteger g = BigInteger.valueOf(g1);
            System.out.println("nilai g = "+g);

            /* lalu baik sisi pengirim dan penerima membangkitkan bilangan bulat acak yg besar yakni x dan y,
               lalu dibuat kembali isinya ditipe data long(x1,y1) agar dapat ditampung oleh
               fungsi BigInteger yg nantinya akan menampung isi bilangan yg sangat besar.
             */
            long x1 = 257;
            BigInteger x = BigInteger.valueOf(x1);
            System.out.println("nilai x = "+x);

            long y1 = 281;
            BigInteger y = BigInteger.valueOf(y1);
            System.out.println("nilai y = "+y);

            /* lalu kita buat perhitungan agar dapat membuat public keynya dari masukkan diatas.
                R1 = g berpangkat x modulus n
                R2 = g berpangkat y modulus n
                lalu tampilkan hasilnya R1 dan R2 tersebut.
             */
            BigInteger R1 = g.modPow(x,n);
            System.out.println("R1 = "+R1);
            BigInteger R2 = g.modPow(y,n);
            System.out.println("R2 = "+R2);

            /* lalu kita buat perhitungan agar dapat membuat private keynya dari masukkan diatas.
                k1 = R2 berpangkat x modulus n
                k2 = R1 berpangkat y modulus n
                disini untuk mendapatkan k1 nya dengan menggunakan R2(begitu juga k2 menggunakan R1)
                karena hasil generate angka yg dibuat sang penerima(ryan) dalam konsepnya,
                tapi disini kita membuat private key untuk dipakai encrypt dan decrypt.
                karena DES itu berkonsep simetri key.

                lalu tampilkan hasilnya k1 dan k2 tersebut. disini hasilnya 128 dan 128 karena simetri(untuk private key)
             */
            BigInteger k1 = R2.modPow(x,n);
            System.out.println("Key calculated at Ihsan's side = "+k1);
            BigInteger k2 = R1.modPow(y,n);
            System.out.println("Key calculated at Ryan's side = "+k2);
            System.out.println("deffie-hellman secret key Encryption has Taken");

            /* lalu saya mempunyai ide sendiri dimuali dari penggalan program dibawah ini.
                untuk membuat key eksternal yg akan dipakai di key DES, kita buat key dari hasil diatas.
                dengan kita gabungkan hasil k1 dan k2 tersebut, tapi kita ubah dulu kebentuk string(sa,sb)
                agar bisa digabung secara karakter(bukan ditambah ya). lalu kita tampung distring baru(jk).
                lalu tampilkan hasil penggabungannya. maka jadi 128128 (bukan 128+128= 256 ya).
             */
            String sa = k1.toString();
            String sb = k2.toString();
            String jk = sa+sb;
            System.out.println("Penggabungan k1 dan k2 = "+jk);

            /* lalu string jk kita pindahkan ke string baru yakni message.
                lalu kita buat byte array agar dapet versi utf-8 nya. dan kita coba ubah dari
                hasil karakter tersebut (128128) jadi bentuk binernya. untuk pengetahuan aja ini mah.
                karena jumlah karakternya hanya ada 6 maka hasil binernya berjumlah 48bit (6 x 8 bit = 48).
             */
            String message = jk;
            byte[] bytes = message.getBytes("UTF-8");
            StringBuilder binary = new StringBuilder();
            for (byte b : bytes)
            {
                int val = b;
                for (int i = 0; i < 8; i++)
                {
                    binary.append((val & 128) == 0 ? 0 : 1);
                    val <<= 1;
                }
            }
            //lalu tampilkan karakter 128128 binernya itu .......
            System.out.println("'" + message + "' to binary: " + binary + " ini panjangnya = " +binary.length()+ " bit");

            /* nah karena key DES itu harus 64 bit maka saya punya ide gimana kita ubah kebentuk encode base64.
                karena saya coba jika karakter itu berjumlah 6 lalu dijadikan ke bentuk encode base 64 maka
                akan menghasilkan 8 karakter. yang mana jika karakternya berjumlah 8 maka binernya berjumlah 64 bit.
                (8 x 8 bit = 64bit).
                maka tergenerate lah key tersebut yang mana bisa diolah untuk menjadi key eksternal pada proses
                key expansion atau sub key pada Algoritma Encryption DES.
             */
            String decodeKey = Base64.encodeToString(bytes, Base64.DEFAULT);
            // lalu kita tampilkan hasil encode base 64 dari karakter 128128 itu deh. (hasilnya = MTI4MTI4).
            System.out.println("hasil encode karakter " +message +  " = " + decodeKey);
            // lalu kita return untuk bisa dikirim untuk diolah jadi key eksternalnya di DES.
            return decodeKey;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
