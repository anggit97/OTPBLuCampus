package com.rontikeky.mycampus.otpblucampus.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rontikeky.mycampus.otpblucampus.Adapter.PresenceAdapter;
import com.rontikeky.mycampus.otpblucampus.Config.Constant;
import com.rontikeky.mycampus.otpblucampus.Config.PrefHandler;
import com.rontikeky.mycampus.otpblucampus.R;
import com.rontikeky.mycampus.otpblucampus.Request.PresenceRequest;
import com.rontikeky.mycampus.otpblucampus.Response.DeteailPresenceResponse;
import com.rontikeky.mycampus.otpblucampus.Response.PresenceResponse;
import com.rontikeky.mycampus.otpblucampus.RestApi.BlucampusClient;
import com.rontikeky.mycampus.otpblucampus.RestApi.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenceActivity extends AppCompatActivity {


    RecyclerView    mRecyclerView;
    TextView    tvJudul;
    FButton btnScanQR;

    LinearLayoutManager mLinearLayoutManager;
    PresenceAdapter mPresenceAdapter;
    List<DeteailPresenceResponse.User>  pesertReseponse =   new ArrayList<>();

    String idEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Inisialisasi
        mRecyclerView   =   (RecyclerView)findViewById(R.id.rvPresence);
        tvJudul         =   (TextView)findViewById(R.id.tvJudulEvent);
        btnScanQR       =   (FButton)findViewById(R.id.btnScanQR);

        mLinearLayoutManager    =   new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mPresenceAdapter    =   new PresenceAdapter(pesertReseponse,this);

        mRecyclerView.setAdapter(mPresenceAdapter);


        //Dapatkan nilai id_event yang dioper dari EventFragment
        Bundle  extras  =   getIntent().getExtras();

        if (extras  ==  null){
            idEvent =   "";
        }else{
            idEvent =   extras.getString(Constant.ID_EVENT_KEY);
        }

        btnScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(PresenceActivity.this);
                integrator.setPrompt("Scan a barcode or QRcode");
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });

        //Tarik data dari server berdasarkna idevent
        getDetailPresence(idEvent);
    }

    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                doPostPresence(result.getContents(), idEvent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void doPostPresence(String contents, final String idEvent) {

        BlucampusClient client  =   ServiceGenerator.createService(BlucampusClient.class);

        PresenceRequest request =   new PresenceRequest(contents,idEvent);

        Call<PresenceResponse>  call    =   client.doPresence(request);

        call.enqueue(new Callback<PresenceResponse>() {
            @Override
            public void onResponse(Call<PresenceResponse> call, Response<PresenceResponse> response) {
                Log.d("DEBUG 0", String.valueOf(response.body().getStatus()));
                if (response.isSuccessful()){
                    //COMPLETED::DOING SOMETHING IF SUCCESS
                    if (response.body().getStatus().equalsIgnoreCase("1")){
                        Toast.makeText(PresenceActivity.this,"Anda berhasil diabsensi", Toast.LENGTH_LONG).show();
                        getDetailPresence(idEvent);
                    }else if(response.body().getStatus().equalsIgnoreCase("2")){
                        Toast.makeText(PresenceActivity.this,"Anda sudah terabsensi", Toast.LENGTH_LONG).show();
                        getDetailPresence(idEvent);
                    }

                    Log.d("DEBUG 1", String.valueOf(response.body().getStatus()));
                }else{
                    //COMPLETED::DOING SOMETHING IF FAILURE
                    Log.d("DEBUG 2","gagal");
                }
            }

            @Override
            public void onFailure(Call<PresenceResponse> call, Throwable t) {
                Log.d("DEBUG 3", t.toString());
            }
        });

    }

    private void getDetailPresence(String idEvent){
        pesertReseponse.clear();
        BlucampusClient client  = ServiceGenerator.createService(BlucampusClient.class);

        Call<DeteailPresenceResponse>   call    =   client.getDetailEventsPresence(idEvent);

        call.enqueue(new Callback<DeteailPresenceResponse>() {
            @Override
            public void onResponse(Call<DeteailPresenceResponse> call, Response<DeteailPresenceResponse> response) {
                if (response.isSuccessful()){
                    Log.d("DEBUG 1",new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));

                    tvJudul.setText(response.body().getAcara().getJudulAcara());

                    int i   =   0;

                    while (i < response.body().getUser().size()) {
                        DeteailPresenceResponse.User    pesertaResponse =   new DeteailPresenceResponse.User(response.body().getUser().get(i).getId(),
                                response.body().getUser().get(i).getUsername(),response.body().getUser().get(i).getName(),response.body().getUser().get(i).getEmail(),
                                response.body().getUser().get(i).getTanggalLahir(),response.body().getUser().get(i).getAlamat(),response.body().getUser().get(i).getTelp(),
                                response.body().getUser().get(i).getIsPermission(),response.body().getUser().get(i).getCreatedAt(),response.body().getUser().get(i).getUpdatedAt(),
                                response.body().getUser().get(i).getStatusHadir());

                        pesertReseponse.add(pesertaResponse);

                        i++;
                    }

                    mPresenceAdapter.notifyDataSetChanged();
                }else{
                    Log.d("DEBUG 2","Gagal");
                }
            }

            @Override
            public void onFailure(Call<DeteailPresenceResponse> call, Throwable t) {
                Log.d("DEBUG 3",t.toString());
            }
        });
    }
}
