package com.example.managelib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LiveAccessAdapter extends RecyclerView.Adapter<LiveAccessAdapter.Viewholder>{

    ArrayList<ContactModel> liveAccessModelList;

    Context context;

    public LiveAccessAdapter(Context contetx,ArrayList<ContactModel> liveAccessModelList){
        this.context=contetx;
        this.liveAccessModelList=liveAccessModelList;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.liveaccess_row,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.name.setText("Name: "+liveAccessModelList.get(position).name);
        holder.uid.setText("ID: "+liveAccessModelList.get(position).id);
        holder.slot.setText(liveAccessModelList.get(position).slot);
        holder.std.setText(liveAccessModelList.get(position).std);
        holder.edd.setText(liveAccessModelList.get(position).edd);
    }

    @Override
    public int getItemCount() {
        return liveAccessModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView name,uid,std,edd,slot;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.memberName_liveAccess);
            uid=itemView.findViewById(R.id.uid_liveAccess);
            std=itemView.findViewById(R.id.std_liveAccess);
            edd=itemView.findViewById(R.id.edd_liveAccess);
            slot=itemView.findViewById(R.id.slot_liveAccess);
        }
    }
}
