/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rontikeky.mycampus.otpblucampus.Algorithm;

public class EncryptMessage extends Function{
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

     public String EncryptMessage(String message, String key){            //masukkan plain dan key ke method
        String[] subKey = new SubKey().SubKeyFind(key);                         //Memanggil SubkeyFind pada kelas SubKey dengan mengirim variable key untuk diproses agar dapat mendapatkan subkey
        binnaryValueMessage = textToBinnary(message);                           //ubah plain text ke binnary
        messageGroup = GroupingTeks(binnaryValueMessage);                       //bagi binner menjadi beberapa group dgn 64 bit/group
        for(int x=0; x<messageGroup.length; x++){                               //lakukan encrypt pada 64 bit sebanyak group
            System.out.println("\nHasil Dari Group " +(x+1));                   //print urutan
            System.out.println("\nMessageGroup " +(x+1)+ messageGroup[x]);
            tempTrueMessage = messageGroup[x].toCharArray();                    //simpan 64 bit string menjadi array
            IndexPermutation();                                                 //lakukan IP pada 64 bit dan dapatkan L0 dan R0 nya
            for(int i=0; i<16; i++){                                            //lakukan 16 kali step di bawah untuk mendapatkan L 16 dan R 16
                BitSelection(i);                                                //lakukan bitselection utk mendapat Li+1 dan hasil bit selection dr Ri
                XOR(subKey[i].toCharArray(), binnaryBitSelect, binnaryXORE);    //XOR  Li+1 dan bit selection dr Ri
                Grouping();                                                     //Grouping 6 bit/group total group adalah 8
                SBOX();                                                         //lakukan sbox pada tiap group untuk mendapat s1-s8 dan satukan
                Permutation();                                                  //kemudian hasilnya di permutation
                XOR(l[i], permutate, r[i+1]);                                   //lakukan xor L dengan hasil permutation
                System.out.println("\nHasil Dari Round " +(i+1));               //print urutan
                System.out.println("L"+(i+1)+" : " + String.valueOf(l[i+1]));   //print Li+1
                System.out.println("R"+(i+1)+" : " + String.valueOf(r[i+1]));   //print Ri+1

            }
            InversIndexPermutation();
            decryptedMessage = decryptedMessage.concat(BinnaryToChar(decryptedBinMessage));
            System.out.println("\nPesan yang telah di Enkripsi pada block" +(x+1));
            aschiiToHex(String.valueOf(BinnaryToChar(decryptedBinMessage)));
            System.out.println(aschiiToHex(String.valueOf(BinnaryToChar(decryptedBinMessage))));
            System.out.println(decryptedBinMessage);
        }
//        /
        return aschiiToHex(decryptedMessage);
    }

    private String toHex(String text){
        char[] arrayText = text.toCharArray();
        String hexTex = "";
        for(int i=0; i<arrayText.length; i++){
            int aschi  = (int) (arrayText[i]);
            hexTex = hexTex.concat(Integer.toHexString(aschi));
        }
        return  hexTex;
    }
    //method dibawah berguna untuk melakukan IndexPermutation binnary message dengan table ip
    void IndexPermutation(){
        for(int i=0; i<ip.length; i++){
            binnaryMessageIP[i] = tempTrueMessage[ip [i]];
            //fungsi dibawah adalah untuk menentukan L0 dan R0
            if(i<32){
                l[0][i] = binnaryMessageIP[i];//
            }else{
                r[0][i-32] = binnaryMessageIP[i];
            }
        }
    }
    //Method dibawah ini berguna untu menentukan li dan E-bit selection dimana li+i = ri, menampung E-bitselec(ri) ke variable binnaryBitSelect
    void BitSelection(int i){
        //Mencari li
        for(int j=0; j<32; j++){
            l[i+1][j] = r[i][j];
        }
        //Binnary bitselect
        for(int j=0; j<bitSelect.length; j++){
            binnaryBitSelect[j] = r[i][bitSelect[j]];
        }
    }
    //fungsi dibawah digunakan untuk menge-xor-kan dengan susunan XOR(arraybinnary1, arraybinnary2, arrayhasil), dimana jika kedua array pada index pertama
    //bernilai sama, maka pada array hasil pada index yang sama akan bernilai 0, jika tidak maka 1.
    void XOR(char[] a, char[] b, char[] hasil){
        for(int i=0; i<hasil.length; i++){
            if(a[i] == b[i]){
                hasil[i] = '0';
            }else{
                hasil[i] = '1';
            }
        }
    }
    //Method dibawah ini berfungsi untuk membuat 8 group untuk proses sbox dimana disetiap group terdiri dari 6-bit yang digroupkan adalah hasil
    //peng-XOR antara subkey dengan binnarybitselect/E(ri)
    void Grouping(){
        int start=0;
        for(int j=0; j<8; j++){
            for(int k=0; k<6; k++){
                bitSelectGroup[j][k] = Integer.parseInt(String.valueOf(binnaryXORE[start]));
                start +=1;
            }
        }
    }
    //mencari sbox
    void SBOX(){
        int start=0;
        int[] baris = new int[8];
        int[] kolom = new int[8];
        //pertama-tama kita cari tahu baris dan kolom dari SBOX, dengan menkonversi tiap 6-bit menjadi integer dimana 1-bit pertma dan terakhir adalah baris dan
        //4 bit sisanya adalah kolom
        for(int j=0; j<8; j++){//8 adalah banyaknya group jadi kita akan mengetahui barsi dan kolom dari setiap group
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
        //perulangan dibawah digunakan untuk mencari tau isi dari baris dan kolom pada setiap sbox
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
            //maka kita dapatkan nilai s1 hingga s8, nilai tersebut diubah menjadi string binnary dan tambahkan 0 di depannya jika panjang setiap s-nya kurang dari 4
            String tempBinB = Integer.toBinaryString(tempB);
            while(tempBinB.length() < 4){
                tempBinB = "0".concat(tempBinB);
            }
            char[] binB = tempBinB.toCharArray();
            //lalu kita simpan pada array sbox  hasil yang disimpan, hanya pada rondenya saja
            for(int k=0; k<4; k++){
                sbox[start] =  binB[k];
                start++; //fungsi ini agara penmindahan s2 tidak menimpa s1 jadi pada index setelahnya.
            }
        }
    }
    //setalah didapat sbox lalukan permutasi dengan tabel p dan simpan pada variable permutate.
    void Permutation(){
        for(int i=0; i<p.length; i++){
            permutate[i] = sbox[p[i]];
        }
    }
    //method dibawah digunakan untuk melakukan InversIndexPermutation dengan mengabungkan l16 dan r16 dengan struktur r terlebih dahulu baru l
    void InversIndexPermutation(){
        for(int i=0; i<64; i++){
            //dibawah untuk mendapatkan r16danl6 lalu tampung pada tempUnencryptedMessage
            if(i<32){
                tempUnencryptedMessage[i] = r[16][i];
            }else{
                tempUnencryptedMessage[i] = l[16][i-32];
            }
        }
        //dibawah ini berguna untuk melakukan InversIndexPermutation lalu tampung pada array binnaryMessageIPinvers
        for(int i=0; i<ipInvers.length; i++){
            binnaryMessageIPinvers[i] = tempUnencryptedMessage[ipInvers[i]];
        }
        decryptedBinMessage = String.valueOf(binnaryMessageIPinvers);//merubah array binnaryMessageIPinvers menjadi string
    }
}
