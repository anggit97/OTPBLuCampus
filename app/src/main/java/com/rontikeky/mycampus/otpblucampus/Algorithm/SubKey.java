/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rontikeky.mycampus.otpblucampus.Algorithm;

public class SubKey extends Function{
	
    String[] SubKeyFind(String key){
        String[] tempKey = new String[16];
        //untuk mencari sub key kita konversi terlebih dahulu string key ke binnary menggunakan method textToBinnary(stringKuncinya) lalu tampung pada suatu variable
        String binnaryValueKey;
        char[] binnaryKeyPC1 = new char[56];
        char[] binnaryKeyPC2 = new char[48];
        char[] tempTrueKey;
        char[][] c = new char[17][28];
        char[][] d = new char[17][28];
        char[] cd = new char[56];

        binnaryValueKey = textToBinnary(key);
        tempTrueKey = binnaryValueKey.toCharArray();
        //lalu kita lakukan pc1 untuk mendapatkan 56-bit dengan perulangan dibawah, lalu membaginya menjadi c0 dan d0
        for(int i=0; i<pc1.length; i++){
            binnaryKeyPC1[i] = tempTrueKey[pc1[i]];
            if(i<28){
                c[0][i] = binnaryKeyPC1[i];
            }else{
                d[0][i-28] = binnaryKeyPC1[i];
            }
        }
        System.out.println("\nHasil Dari PC-1");
        System.out.println(binnaryKeyPC1);
        System.out.println("\nC0 dan D0");
        System.out.println("C0 : " + String.valueOf(c[0]));
        System.out.println("D0 : " + String.valueOf(d[0]));
        System.out.println("\nHasil Dari Left Shift");
        //perulangan dibawah ini adalah untuk mencari c dan  d selanjutnya dengan menggunakan leftshift
        for(int i=1; i<=16; i++){
            int start=leftShift[i-1];
            for(int j=0; j<28; j++){
                if(start >= 28){
                    start=0;
                }
                c[i][j] = c[i-1][start];
                d[i][j] = d[i-1][start];
                start++;
            }
            System.out.println("C"+ i + " : " + String.valueOf(c[i]));
            System.out.println("D"+ i + " : " + String.valueOf(d[i]) + "\n");
        }
        System.out.println("Subkey yang didapat");
        //stelah didapat c dan d 1 hingga 16 lalu gabungkan dengan perlulangan dibawah ini menjadi c1d1 sampai c16d16, dimana c1d1 adalah subkey 1
        for(int i=1; i<=16; i++){
            for(int j=0; j<56; j++){
                if(j<28){
                    cd[j] = c[i][j];
                }else{
                    cd[j] = d[i][j-28];
                }
            }
            for(int j=0; j<48; j++){
                binnaryKeyPC2[j] = cd[pc2[j]];
            }
            tempKey[i-1] = String.valueOf(binnaryKeyPC2);
            System.out.println("K" + i + " : " + (tempKey[i-1]));
        }
        return tempKey;
    }
}
