package com.rontikeky.mycampus.otpblucampus.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rontikeky.mycampus.otpblucampus.R;
import com.rontikeky.mycampus.otpblucampus.Response.DeteailPresenceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anggit on 15/12/2017.
 */

public class PresenceAdapter extends RecyclerView.Adapter<PresenceAdapter.PresenceHolder>{

    List<DeteailPresenceResponse.User>  pesertaResponses    =   new ArrayList<>();
    Context context;

    public PresenceAdapter(List<DeteailPresenceResponse.User> pesertaResponses, Context context) {
        this.context            =   context;
        this.pesertaResponses   =   pesertaResponses;
    }

    public class PresenceHolder extends RecyclerView.ViewHolder {

        TextView    tvNumbering,tvUsername, tvNama, tvStatus;

        public PresenceHolder(View itemView) {
            super(itemView);

            tvNumbering =   (TextView)itemView.findViewById(R.id.tvNumbering);
            tvUsername  =   (TextView)itemView.findViewById(R.id.tvtUsernamePeserta);
            tvNama      =   (TextView)itemView.findViewById(R.id.tvNamaPeserta);
            tvStatus    =   (TextView)itemView.findViewById(R.id.tvStatusPeserta);
        }
    }

    @Override
    public PresenceHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View    view    = LayoutInflater.from(context).inflate(R.layout.single_presence,parent,false);

        return new PresenceHolder(view);
    }

    @Override
    public void onBindViewHolder(PresenceHolder holder, int position) {

        DeteailPresenceResponse.User    model   =   pesertaResponses.get(position);

        holder.tvNumbering.setText(String.valueOf(position+1));
        holder.tvUsername.setText(model.getUsername());
        holder.tvNama.setText(model.getName());
        holder.tvStatus.setText(String.valueOf(model.getStatusHadir().getStatus()));

    }

    @Override
    public int getItemCount() {
        if (pesertaResponses    ==  null)return 0;

        return pesertaResponses.size();
    }

}
