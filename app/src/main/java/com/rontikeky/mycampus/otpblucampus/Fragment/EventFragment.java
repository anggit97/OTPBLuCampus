package com.rontikeky.mycampus.otpblucampus.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.rontikeky.mycampus.otpblucampus.Adapter.EventAdapter;
import com.rontikeky.mycampus.otpblucampus.Config.FontHandler;
import com.rontikeky.mycampus.otpblucampus.Config.PrefHandler;
import com.rontikeky.mycampus.otpblucampus.R;
import com.rontikeky.mycampus.otpblucampus.Response.EventResponse;
import com.rontikeky.mycampus.otpblucampus.RestApi.BlucampusClient;
import com.rontikeky.mycampus.otpblucampus.RestApi.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anggit on 14/12/2017.
 */

public class EventFragment extends Fragment{

    //COMPONENT VIEW XML
    TextView tvUsername;
    FButton btnLogout;

    //SINGLETON
    FontHandler fontHandler;
    Typeface fontMedium, fontBold;

    //LIST
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    EventAdapter mEventAdpater;

    //MODEL
    List<EventResponse.Data> eventResponses  = new ArrayList<>();

    public EventFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View    view    =   inflater.inflate(R.layout.fragment_event,container,false);

        //Inisialisasi
        tvUsername  =   (TextView)view.findViewById(R.id.tvUsername);
        mRecyclerView   =   (RecyclerView)view.findViewById(R.id.rvEvent);

        //Orientation Layout Recyclerview
        mLinearLayoutManager    =   new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mEventAdpater   =   new EventAdapter(eventResponses,getActivity());

        mRecyclerView.setAdapter(mEventAdpater);


        //Buat objek FontHandler
        fontHandler =   new FontHandler(getActivity());
        //Mendapatkan nilai font dari Class FontHandler
        fontMedium  =   fontHandler.getFont();
        fontBold    =   fontHandler.getFontBold();

        PrefHandler.init(getActivity());

        tvUsername.setTypeface(fontBold);

        getEvents();

        return view;
    }

    private void getEvents(){

        BlucampusClient client  = ServiceGenerator.createService(BlucampusClient.class);

        Call<EventResponse> call  =   client.getEvents(PrefHandler.getId());

        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                if (response.isSuccessful()){
                    Log.d("DEBUG 1", new GsonBuilder().setPrettyPrinting().create().toJson(response.body()));

                    int i   =   0;
                    while (i < response.body().getData().size()){
                        EventResponse.Data  data    =   new EventResponse.Data(response.body().data.get(i).getId(),response.body().data.get(i).getIdUser(),response.body().data.get(i).getJudulAcara(),response.body().data.get(i).getIsiAcara(),
                                response.body().data.get(i).getTempatAcara(),response.body().data.get(i).getContactPersonAcara(),response.body().data.get(i).getJumlahPeserta(),response.body().data.get(i).getTanggalAcara(),
                                response.body().data.get(i).getJamAcara(),response.body().data.get(i).getFotoAcara(),response.body().data.get(i).getStatusAcara());

                        eventResponses.add(data);
                        i++;
                    }

                    Log.d("DEBUG 5", String.valueOf(eventResponses.size()));

                    mEventAdpater.notifyDataSetChanged();
                }else{
                    Log.d("DEBUG 2", "GAGAL");
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Log.d("DEBUG 4", t.toString());
            }
        });
    }
}
