package com.rontikeky.mycampus.otpblucampus.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rontikeky.mycampus.otpblucampus.Algorithm.DecryptMessage;
import com.rontikeky.mycampus.otpblucampus.Algorithm.DiffieHelmanGenerator;
import com.rontikeky.mycampus.otpblucampus.Config.Constant;
import com.rontikeky.mycampus.otpblucampus.Config.FontHandler;
import com.rontikeky.mycampus.otpblucampus.Config.PrefHandler;
import com.rontikeky.mycampus.otpblucampus.R;
import com.rontikeky.mycampus.otpblucampus.Request.SigninRequest;
import com.rontikeky.mycampus.otpblucampus.Response.SigninResponse;
import com.rontikeky.mycampus.otpblucampus.RestApi.BlucampusClient;
import com.rontikeky.mycampus.otpblucampus.RestApi.ServiceGenerator;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView    tvRegister,tvResult;
    MaterialEditText etEmail, etPassword;
    Button btnLogin;
    ProgressBar mProgressLogin;

    FontHandler fontHandler;
    Typeface    fontMedium, fontBold;

    Boolean valid;

    String key;
    String plainTextOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        //Inisialisasi
        etEmail         =   (MaterialEditText)findViewById(R.id.etEmail);
        etPassword      =   (MaterialEditText)findViewById(R.id.etPassword);
        btnLogin        =   (FButton)findViewById(R.id.btnLogin);
        tvRegister      =   (TextView)findViewById(R.id.tvRegister);
        tvResult        =   (TextView)findViewById(R.id.result);
        mProgressLogin  =   (ProgressBar)findViewById(R.id.pCircle);

        //Buat objek FontHandler
        fontHandler =   new FontHandler(this);

        //Mendapatkan nilai font dari Class FontHandler
        fontMedium  =   fontHandler.getFont();
        fontBold    =   fontHandler.getFontBold();

        etEmail.setTypeface(fontMedium);
        etPassword.setTypeface(fontMedium);
        btnLogin.setTypeface(fontBold);
        tvRegister.setTypeface(fontBold);

        PrefHandler.init(LoginActivity.this);


        key = DiffieHelmanGenerator.diffieHKeyGenerator().trim();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setVisibility(View.GONE);
                tvResult.setText("");
                if (isValid()){
                    doLogin(etEmail.getText().toString(),etPassword.getText().toString());
                }else{
                    tvResult.setText("Tidak boleh ada field yang kosong!");
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRegisterActivity   =   new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(toRegisterActivity);
            }
        });
    }

    private void onProgress(){
        mProgressLogin.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);
    }

    private void postProgress(){
        mProgressLogin.setVisibility(View.GONE);
        btnLogin.setVisibility(View.VISIBLE);
    }

    private void doLogin(String email, final String password){

        onProgress();

        BlucampusClient client  = ServiceGenerator.createService(BlucampusClient.class);

        SigninRequest   signinRequest   =   new SigninRequest(email,password);

        Call<SigninResponse>    call    =   client.doLogin(signinRequest);

        call.enqueue(new Callback<SigninResponse>() {

            @Override
            public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {

                postProgress();

                Log.d("DEBUG 1", new Gson().toJson(response.body()));

                if (response.isSuccessful()){
                    //COMPLETED::DOING SOMETHING IF SUCCESS

                    if (response.body().getStatus().equalsIgnoreCase("true")){

                        //Method untuk melakukan dekripsi pada kode otp
                        String hasil = doDecryptMessage(key, response.body().getOtp().toString());

                        Log.d("DEBUG 2", hasil);
                        PrefHandler.setId(response.body().getUserId());
                        PrefHandler.setEmailKey(response.body().getEmail());
                        PrefHandler.setTelpKey(response.body().getTelp());
                        PrefHandler.setPassKey(password);
                        PrefHandler.setNameKey(response.body().getNama());

                        Intent  toVerifyActivity    =   new Intent(LoginActivity.this, VerifyOTP.class);
                        toVerifyActivity.putExtra(Constant.OTP_KEY,hasil);
                        startActivity(toVerifyActivity);
                        finish();
                    }else{
                        tvResult.setVisibility(View.VISIBLE);
                        tvResult.setText("Kombinasi Username dan Password salah!");
                    }
                }else{
                    //COMPLETED::DOING SOMETHING IF FAILURE
                    tvResult.setVisibility(View.VISIBLE);
                    tvResult.setText("Gagal Login xxx");
                    Toast.makeText(LoginActivity.this,"Gagal Login xxx", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SigninResponse> call, Throwable t) {
                //COMPLETED:DOING SOMETHING IF ERROR
                postProgress();
                tvResult.setVisibility(View.VISIBLE);
                tvResult.setText("Gagal Login");
                Toast.makeText(LoginActivity.this,"Gagal Login", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String doDecryptMessage(String key, String chipperText) {

        try {
            DecryptMessage  decryptMessage  =   new DecryptMessage();
            plainTextOTP    =   decryptMessage.DecryptMessage(chipperText, key);
            Log.d("Plaintext", plainTextOTP);
            return plainTextOTP;
        } catch (Exception e) {
            Log.d("Plaintext ERROR", e.getMessage());
        }

        return key;
    }

    private boolean isValid(){
        valid   =   true;

        if (etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
            tvResult.setVisibility(View.VISIBLE);
            valid   =   false;
        }

        return valid;
    }
}
