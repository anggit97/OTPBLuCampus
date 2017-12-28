/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rontikeky.mycampus.otpblucampus.Algorithm;

public class Function {

    int[] ip = {57, 49, 41, 33, 25, 17,  9,  1,
            59, 51, 43, 35, 27, 19, 11,  3,
            61, 53, 45, 37, 29, 21, 13,  5,
            63, 55, 47, 39, 31, 23, 15,  7,
            56, 48, 40, 32, 24, 16,  8,  0,
            58, 50, 42, 34, 26, 18, 10,  2,
            60, 52, 44, 36, 28, 20, 12,  4,
            62, 54, 46, 38, 30, 22, 14,  6};

    int[] bitSelect = {31,  0,  1,  2,  3,  4,
            3,  4,  5,  6,  7,  8,
            7,  8,  9, 10, 11, 12,
            11, 12, 13, 14, 15, 16,
            15, 16, 17, 18, 19, 20,
            19, 20, 21, 22, 23, 24,
            23, 24, 25, 26, 27, 28,
            27, 28, 29, 30, 31,  0};

    int[][] s1 = {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};

    int[][] s2 = {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};

    int[][] s3 = {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
            {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
            {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
            {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};

    int[][] s4 = {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
            {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
            {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
            {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};

    int[][] s5 = {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
            {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
            {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
            {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};

    int[][] s6 = {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
            {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
            {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
            {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};

    int[][] s7 = {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
            {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
            {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
            {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};

    int[][] s8 = {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
            {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
            {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
            {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};

    int[] p = { 15,  6, 19, 20,
            28, 11, 27, 16,
            0, 14, 22, 25,
            4, 17, 30, 9,
            1,  7, 23, 13,
            31, 26,  2,  8,
            18, 12, 29,  5,
            21, 10,  3, 24};

    int[] ipInvers = {39,  7, 47, 15, 55, 23, 63, 31,
            38,  6, 46, 14, 54, 22, 62, 30,
            37,  5, 45, 13, 53, 21, 61, 29,
            36,  4, 44, 12, 52, 20, 60, 28,
            35,  3, 43, 11, 51, 19, 59, 27,
            34,  2, 42, 10, 50, 18, 58, 26,
            33,  1, 41,  9, 49, 17, 57, 25,
            32,  0, 40,  8, 48, 16, 56, 24};

    int[] pc1 = {56, 48, 40, 32, 24, 16, 8,
            0, 57, 49, 41, 33, 25, 17,
            9,  1, 58, 50, 42, 34, 26,
            18, 10,  2, 59, 51, 43, 35,
            62, 54, 46, 38, 30, 22, 14,
            6, 61, 53, 45, 37, 29, 21,
            13,  5, 60, 52, 44, 36, 28,
            20, 12,  4, 27, 19, 11,  3};

    int[] leftShift = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};

    int[] pc2 = {13, 16, 10, 23,  0,  4,
            2, 27, 14,  5, 20,  9,
            22, 18, 11,  3, 25,  7,
            15,  6, 26, 19, 12,  1,
            40, 51, 30, 36, 46, 54,
            29, 39, 50, 44, 32, 47,
            43, 48, 38, 55, 33, 52,
            45, 41, 49, 35, 28, 31};

    /*
     Method di bawah ini berfungsi untuk mengubah teks ke binnary
     dengan cara merubah string input menjadi char array, lalu kita cari nilai integer perkarakter
     lalu ubah nilai integer tersebut menjadi nilai binnary bentuk string, jika panjang string dari
     binnary yang didapat kurang dari 8, maka kami tambahkan 0 .
    */
    String textToBinnary(String teks){
        char[] tempTeks = teks.toCharArray();
        String binnaryValue = "";
        for(int i=0; i<tempTeks.length; i++){
            String zero = "0";
            int integerValue = Integer.valueOf(tempTeks[i]);
            String tempBinnary = Integer.toBinaryString(integerValue);
            while(tempBinnary.length() != 8){
                tempBinnary = zero.concat(tempBinnary);
            }
            binnaryValue = binnaryValue.concat(tempBinnary);
        }
        while (binnaryValue.length() % 64 != 0) {
            binnaryValue = binnaryValue.concat("0");
        }
        return binnaryValue;
    }
    /*
     Method di bawah ini berfungsi untuk mengubah binnary ke karakter dan menjadi string
     dengan cara merubah string binnary input,  lalu kita konversi string binnary dengan mengelompokkannya dimana banyak kelompoknya
     adalah panjang string binnary input dibagi 8, karena akan dikonversi per-8 binnary dimana 8 binnary ini adalah 1 karakter
     binary dikonversi dengan mencari nilai yang bernilai 1 dan dikalikan dengan nilai pangkatnya misalnya posisi pertama adalah 2^7
     lalu ditambahkan dengan nilai selanjutnya hingga 8 binnary tersebut selesai, hasil dari penjumlahan tersebut adalah nilai integer yang akan dikonversi
     ke karakter .
    */
    String BinnaryToChar(String BinMessage){
        char[] cryptChar = new char[(BinMessage.length()/8)];
        char[] tempChar;
        int start = 0;
        for(int i=0; i<cryptChar.length; i++){
            int tempInt=0;
            tempChar = BinMessage.substring(start, start+8).toCharArray();
            start+=8;
            int power=7;
            for(int j=0; j<tempChar.length; j++){
                if(tempChar[j] != '0'){
                    tempInt = tempInt +(int) (Math.pow(2,power));
                }
                power--;
            }
            cryptChar[i] = (char)(tempInt);
        }
        return String.valueOf(cryptChar);
    }
    /*
    karena DES hanya dapat mengkonversi 64-bit oleh karena itu kami menggroupkan string input /64-bit dengan fungsi dibawah ini dengan membuat array
    dimana panjang arraynya adalah panjang string input/64.
    */
    String[] GroupingTeks(String binText){
        String[] tempMessageGroup = new String[(binText.length()/64)];
        int start = 0;
        for(int i=0; i<(binText.length()/64); i++){
            tempMessageGroup[i] = binText.substring(start, (start+64));
            start+=64;
        }
        return tempMessageGroup;
    }

    String hexToAschii(String text){
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < text.length(); i+=2) {
            String str = text.substring(i, i+2);
            output.append((char)Integer.parseInt(str, 16));
        }
        System.out.println(output);
        return new String(output);
    }
    String aschiiToHex(String text){
        char[] testString = text.toCharArray();
        String hex = "";
        for (int i=0; i<testString.length; i++)
        {
            String tempString = "";
            tempString = String.format("%H", testString[i]);
            while(tempString.length() != 2){
                tempString = "0".concat(tempString);
            }
            hex = hex.concat(tempString);
        }
        return hex;
    }
}
