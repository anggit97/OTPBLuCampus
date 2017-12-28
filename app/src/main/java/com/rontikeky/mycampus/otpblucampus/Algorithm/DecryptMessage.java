/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rontikeky.mycampus.otpblucampus.Algorithm;

public class DecryptMessage extends Function{
    char[] binnaryMessageIP = new char[64];
    char[] binnaryMessageIPinvers = new char[64];
    char[] tempTrueMessage;
    char[] binnaryBitSelect = new char[48];
    char[] binnaryXORE = new char[48];
    char[] sbox = new char[32];
    char[] permutate = new char[32];
    char[][] l = new char[17][32];
    char[][] r = new char[17][32];
    char[] rl  = new char[64];
    char[] tempUnencryptedMessage =  new char[64];
    int[][] bitSelectGroup = new int[8][6];
    String decryptedBinMessage = "";
    String decryptedMessage = "";
    String binnaryValueMessage = "";
    String[] messageGroup;

    public String DecryptMessage(String message, String key){
        String[] subKey = new SubKey().SubKeyFind(key);                         //Memanggil SubkeyFind pada kelas SubKey dengan mengirim variable key untuk diproses agar dapat mendapatkan subkey
        String decodeChipper = null;
        String stringAschii = hexToAschii(message);
//        try {
//            decodeChipper = new String(Base64.decode(message, Base64.DEFAULT), "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        while (decodeChipper.length() % 8 != 0){
//            decodeChipper = decodeChipper.concat(" ");
//        }
        binnaryValueMessage = textToBinnary(stringAschii);
        messageGroup = GroupingTeks(binnaryValueMessage);
        for(int x=0; x<messageGroup.length; x++){
            System.out.println("\nHasil Dari Group " +(x+1));
            tempTrueMessage = messageGroup[x].toCharArray();
            IndexPermutation();
            for(int i=0; i<16; i++){
                BitSelection(i);
                XOR(subKey[16 -(i+1)].toCharArray(), binnaryBitSelect, binnaryXORE); //Di dalam proses decrypt, urutan dan prosesnya sama saja dengan proses encrypt.
                Grouping();                                                          //Yang menjadi plaintext nya adalah pesan yang sudah di encrypt.
                SBOX();                                                              //Kemudian untuk penggunaan key nya dibalik urutannya, yaitu dari 16 ke 1.
                Permutation();                                                       //Hasil dari proses decrypt adalah pesan awal yang akan di encrypt.
                XOR(l[i], permutate, r[i+1]);
                System.out.println("\nHasil Dari Round " +(i+1));
                System.out.println("L"+(i+1)+" : " + String.valueOf(l[i+1]));
                System.out.println("R" +(i+1)+" : "+ String.valueOf(r[i+1]));

            }
            InversIndexPermutation();
            decryptedMessage = decryptedMessage.concat(BinnaryToChar(decryptedBinMessage));
            System.out.println("\nPesan yang telah di Dekripsi pada block" +(x+1));
            System.out.println(String.valueOf(BinnaryToChar(decryptedBinMessage)));
        }
        return decryptedMessage;
    }
    void IndexPermutation(){
        for(int i=0; i<ip.length; i++){
            binnaryMessageIP[i] = tempTrueMessage[ip[i]];
            if(i<32){
                l[0][i] = binnaryMessageIP[i];
            }else{
                r[0][i-32] = binnaryMessageIP[i];
            }
        }
    }
    void BitSelection(int i){
        for(int j=0; j<32; j++){
            l[i+1][j] = r[i][j];
        }
        for(int j=0; j<bitSelect.length; j++){
            binnaryBitSelect[j] = r[i][bitSelect[j]];
        }
    }
    void XOR(char[] a, char[] b, char[] hasil){
        for(int i=0; i<hasil.length; i++){
            if(a[i] == b[i]){
                hasil[i] = '0';
            }else{
                hasil[i] = '1';
            }
        }
    }
    void Grouping(){
        int start=0;
        for(int j=0; j<8; j++){
            for(int k=0; k<6; k++){
                bitSelectGroup[j][k] = Integer.parseInt(String.valueOf(binnaryXORE[start]));
                start +=1;
            }
        }
    }
    void SBOX(){
        int start=0;
        int[] baris = new int[8];
        int[] kolom = new int[8];
        for(int j=0; j<8; j++){
            if(bitSelectGroup[j][0] != 0){
                baris[j] = baris[j] +(int) (Math.pow(2,1));
            }else{
                baris[j] = baris[j] + 0;
            }

            if(bitSelectGroup[j][1] != 0){
                kolom[j] = kolom[j] +(int) (Math.pow(2,3));
            }else{
                kolom[j] = kolom[j] + 0;
            }

            if(bitSelectGroup[j][2] != 0){
                kolom[j] = kolom[j] +(int) (Math.pow(2,2));
            }else{
                kolom[j] = kolom[j] + 0;
            }

            if(bitSelectGroup[j][3] != 0){
                kolom[j] = kolom[j] +(int) (Math.pow(2,1));
            }else{
                baris[j] = baris[j] + 0;
            }

            if(bitSelectGroup[j][4] != 0){
                kolom[j] = kolom[j] +(int) (Math.pow(2,0));
            }else{
                kolom[j] = kolom[j] + 0;
            }

            if(bitSelectGroup[j][5] != 0){
                baris[j] = baris[j] +(int) (Math.pow(2,0));
            }else{
                baris[j] = baris[j] + 0;
            }
        }
        for(int j=0; j<8; j++){
            int tempB = 0;
            if(j==0){
                tempB = s1[baris[j]][kolom[j]];
            }else if(j==1){
                tempB = s2[baris[j]][kolom[j]];
            }else if(j==2){
                tempB = s3[baris[j]][kolom[j]];
            }else if(j==3){
                tempB = s4[baris[j]][kolom[j]];
            }else if(j==4){
                tempB = s5[baris[j]][kolom[j]];
            }else if(j==5){
                tempB = s6[baris[j]][kolom[j]];
            }else if(j==6){
                tempB = s7[baris[j]][kolom[j]];
            }else if(j==7){
                tempB = s8[baris[j]][kolom[j]];
            }
            String tempBinB = Integer.toBinaryString(tempB);
            while(tempBinB.length() < 4){
                tempBinB = "0".concat(tempBinB);
            }
            char[] binB = tempBinB.toCharArray();
            for(int k=0; k<4; k++){
                sbox[start] =  binB[k];
                start++;
            }
        }
    }
    void Permutation(){
        for(int i=0; i<p.length; i++){
            permutate[i] = sbox[p[i]];
        }
    }
    void InversIndexPermutation(){
        for(int i=0; i<64; i++){
            if(i<32){
                tempUnencryptedMessage[i] = r[16][i];
            }else{
                tempUnencryptedMessage[i] = l[16][i-32];
            }
        }
        for(int i=0; i<ipInvers.length; i++){
            binnaryMessageIPinvers[i] = tempUnencryptedMessage[ipInvers[i]];
        }
        decryptedBinMessage = String.valueOf(binnaryMessageIPinvers);
    }
}
