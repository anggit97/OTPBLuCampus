package com.rontikeky.mycampus.otpblucampus.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.rontikeky.mycampus.otpblucampus.Config.FontHandler;
import com.rontikeky.mycampus.otpblucampus.R;
import com.rontikeky.mycampus.otpblucampus.Request.SignupRequest;
import com.rontikeky.mycampus.otpblucampus.Response.SignupResponse;
import com.rontikeky.mycampus.otpblucampus.ResponseError.RegisterErrorResponse;
import com.rontikeky.mycampus.otpblucampus.RestApi.BlucampusClient;
import com.rontikeky.mycampus.otpblucampus.RestApi.ErrorRegisterUtil;
import com.rontikeky.mycampus.otpblucampus.RestApi.ServiceGenerator;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    FButton btnSignup;
    EditText etUsername, etEmail, etTelp, etNama, etAlamat, etPassword;
    TextView tvResult;
    ProgressBar mProgressLogin;
    ScrollView  scrollView;

    FontHandler fontHandler;
    Typeface fontMedium, fontBold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //Inisialisasi
        etUsername  =   (EditText)findViewById(R.id.etUsername);
        etEmail     =   (EditText)findViewById(R.id.etEmail);
        etNama      =   (EditText)findViewById(R.id.etNama);
        etAlamat    =   (EditText)findViewById(R.id.etAlamat);
        etPassword  =   (EditText)findViewById(R.id.etPassword);
        etTelp      =   (EditText)findViewById(R.id.etTelp);
        btnSignup   =   (FButton) findViewById(R.id.btnSignup);
        tvResult    =   (TextView) findViewById(R.id.result);
        mProgressLogin  =   (ProgressBar)findViewById(R.id.pCircle);
        scrollView  =   (ScrollView)findViewById(R.id.scrollViewRegister);

        //Buat objek FontHandler
        fontHandler =   new FontHandler(this);
        //Mendapatkan nilai font dari Class FontHandler
        fontMedium  =   fontHandler.getFont();
        fontBold    =   fontHandler.getFontBold();


        etEmail.setTypeface(fontMedium);
        etPassword.setTypeface(fontMedium);
        etTelp.setTypeface(fontMedium);
        etNama.setTypeface(fontMedium);
        etAlamat.setTypeface(fontMedium);
        etUsername.setTypeface(fontMedium);
        btnSignup.setTypeface(fontBold);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setText("");
                doSignup();
            }
        });
    }

    private void onProgress(){
        mProgressLogin.setVisibility(View.VISIBLE);
        btnSignup.setVisibility(View.GONE);
    }

    private void postProgress(){
        mProgressLogin.setVisibility(View.GONE);
        btnSignup.setVisibility(View.VISIBLE);
    }

    private void doSignup(){
        onProgress();
        BlucampusClient client  = ServiceGenerator.createService(BlucampusClient.class);

        SignupRequest   signupRequest   =   new SignupRequest(etUsername.getText().toString(),etNama.getText().toString(),etEmail.getText().toString(),"1997-07-15",etAlamat.getText().toString(),etTelp.getText().toString(),etPassword.getText().toString());

        Call<SignupResponse>    call    =   client.dosignup(signupRequest);

        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                postProgress();
                if(response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Berhasil Melakukan Pendaftaran!", Toast.LENGTH_LONG).show();
                    Intent toLoginActivity  =   new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(toLoginActivity);
                    finish();
                }else{
                    RegisterErrorResponse errorResponse = ErrorRegisterUtil.parseError(response);
                    if (errorResponse.getEmail() != null){
                        tvResult.setVisibility(View.VISIBLE);
                        scrollView.smoothScrollTo(0,0);
                        etEmail.setError("Email sudah digunakan");
                        tvResult.append("*Email sudah digunakan \n");
                    }

                    if (errorResponse.getUsername() != null){
                        tvResult.setVisibility(View.VISIBLE);
                        scrollView.smoothScrollTo(0,0);
                        etUsername.setError("Username sudah digunakan");
                        tvResult.append("*Username sudah digunakan \n");
                    }
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                postProgress();
                Toast.makeText(RegisterActivity.this, "Gagal melakukan Login!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
