package com.rontikeky.mycampus.otpblucampus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rontikeky.mycampus.otpblucampus.Activity.PresenceActivity;
import com.rontikeky.mycampus.otpblucampus.Config.Constant;
import com.rontikeky.mycampus.otpblucampus.R;
import com.rontikeky.mycampus.otpblucampus.Response.EventResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anggit on 14/12/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder>{

    List<EventResponse.Data> eventResponses  =   new ArrayList<>();
    Context context;

    public EventAdapter(List<EventResponse.Data> eventResponses, Context context) {
        this.eventResponses =   eventResponses;
        this.context        =   context;
    }

    public class EventHolder extends RecyclerView.ViewHolder {

        TextView    tvJudul, tvTanggal, tvLokasi, tvIdEvent;
        ImageView   ivGambar;

        public EventHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent  toDetailPresenceEvent   =   new Intent(context, PresenceActivity.class);
                    toDetailPresenceEvent.putExtra(Constant.ID_EVENT_KEY,tvIdEvent.getText().toString());
                    itemView.getContext().startActivity(toDetailPresenceEvent);
                }
            });

            tvJudul     =   (TextView)itemView.findViewById(R.id.tvJudulEvent);
            tvTanggal   =   (TextView)itemView.findViewById(R.id.tvTanggalAcara);
            tvLokasi    =   (TextView)itemView.findViewById(R.id.tvTempatAcara);
            tvIdEvent   =   (TextView)itemView.findViewById(R.id.tvIdEventEO);
            ivGambar    =   (ImageView)itemView.findViewById(R.id.ivEvent);

        }
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View    view    = LayoutInflater.from(context).inflate(R.layout.single_event,parent,false);

        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        EventResponse.Data  model   =   eventResponses.get(position);


        try {
            holder.tvJudul.setText(model.getJudulAcara());
            holder.tvTanggal.setText(model.getTanggalAcara());
            holder.tvLokasi.setText(model.getTempatAcara());
            holder.tvIdEvent.setText(String.valueOf(model.getId()));
            Glide.with(context).load("http://blucampus.anggitprayogo.com/"+model.getFotoAcara()).placeholder(R.drawable.guest).error(R.drawable.guest).into(holder.ivGambar);
        } catch (Exception e) {
            Log.d("DEBUG ID", e.toString());
        }

    }

    @Override
    public int getItemCount() {
        if (eventResponses == null) return 0;

        return eventResponses.size();
    }

}
