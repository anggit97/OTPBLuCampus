package com.rontikeky.mycampus.otpblucampus.Activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.raycoarana.codeinputview.CodeInputView;
import com.raycoarana.codeinputview.OnCodeCompleteListener;
import com.rontikeky.mycampus.otpblucampus.Config.Constant;
import com.rontikeky.mycampus.otpblucampus.Config.PrefHandler;
import com.rontikeky.mycampus.otpblucampus.R;
import com.rontikeky.mycampus.otpblucampus.Request.RetryOTPRequest;
import com.rontikeky.mycampus.otpblucampus.Response.RetryOTPResponse;
import com.rontikeky.mycampus.otpblucampus.RestApi.BlucampusClient;
import com.rontikeky.mycampus.otpblucampus.RestApi.ServiceGenerator;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOTP extends AppCompatActivity {

    private Handler mHandler = new Handler();

    TextView tvCountDown, tvTelp, tvNotice;
    CodeInputView   mCodeInputView;
    FButton btnSendAgain;

    String otpKode, telp, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        //Inisialisasi
        tvCountDown     =   (TextView)findViewById(R.id.tvCountDown);
        tvTelp          =   (TextView)findViewById(R.id.tvTelp);
        tvNotice        =   (TextView)findViewById(R.id.tvNotice);
        mCodeInputView  =   (CodeInputView)findViewById(R.id.codeInput);
        btnSendAgain    =   (FButton)findViewById(R.id.btnRetryOTP);

        PrefHandler.init(VerifyOTP.this);
        telp    =   PrefHandler.getTelpKey();
        email   =   PrefHandler.getEmailKey();

        btnSendAgain.setEnabled(false);

        Bundle  extras  =   getIntent().getExtras();

        if (extras  ==  null){
            otpKode =   "Anjay";
        }else{
            otpKode =   extras.getString(Constant.OTP_KEY);
        }

        Toast.makeText(VerifyOTP.this, otpKode, Toast.LENGTH_SHORT).show();

        btnSendAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSendAgain();
                btnSendAgain.setEnabled(false);
                countDownTimer();
                //Make the input enable again so the user can change it
                mCodeInputView.setCode("");
                mCodeInputView.setEditable(true);
                mCodeInputView.setError("");
            }
        });

        mCodeInputView.addOnCompleteListener(new OnCodeCompleteListener() {
            @Override
            public void onCompleted(String code) {

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (mCodeInputView.getCode().equalsIgnoreCase(otpKode)) {
                            PrefHandler.setIsPassOtp("1");
                            Intent toMainActivity   =   new Intent(VerifyOTP.this, MainActivity.class);
                            startActivity(toMainActivity);
                            finish();
                        }else{
                            Toast.makeText(VerifyOTP.this, "Tunggu waktu habis untuk mengulangi", Toast.LENGTH_SHORT).show();
                            //Show error
                            mCodeInputView.setError("Kode OTP Salah");
                        }
                    }
                }, 1000);
            }
        });

        countDownTimer();
    }

    private void countDownTimer(){
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvCountDown.setText(""+millisUntilFinished / 1000);
            }

            public void onFinish() {
                btnSendAgain.setEnabled(true);
                tvCountDown.setText("Silahkan Kirim Ulang..");

            }
        }.start();
    }

    private void doSendAgain(){
        BlucampusClient client  = ServiceGenerator.createService(BlucampusClient.class);

        RetryOTPRequest retryOTPRequest =   new RetryOTPRequest(PrefHandler.getId(),PrefHandler.getPassKey());

        Call<RetryOTPResponse>  call    =   client.doRetryOTP(retryOTPRequest);

        call.enqueue(new Callback<RetryOTPResponse>() {
            @Override
            public void onResponse(Call<RetryOTPResponse> call, Response<RetryOTPResponse> response) {
                Log.d("DEBUG 0",new Gson().toJson(response.body()));
                if (response.isSuccessful()){
                    otpKode =   ""+response.body().getOtp();
                }else{
                    Log.d("DEBUG 2","GAGAL");
                }
            }

            @Override
            public void onFailure(Call<RetryOTPResponse> call, Throwable t) {
                Log.d("DEBUG 3",t.toString());
            }
        });
    }
}
