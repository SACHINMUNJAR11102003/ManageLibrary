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

public class Expire_1_5_Adapter extends RecyclerView.Adapter<Expire_1_5_Adapter.Viewholder>{

    Context context;
    public ArrayList<ContactModel> expire_1_5_accessArraylist;

    public Expire_1_5_Adapter(){

    }

    public Expire_1_5_Adapter(Context context, ArrayList<ContactModel> expireAccessModelList){
        this.context=context;
        this.expire_1_5_accessArraylist=expireAccessModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.expire_1_5_row,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.name.setText("Name: "+expire_1_5_accessArraylist.get(position).name);
        holder.uid.setText("UID: "+expire_1_5_accessArraylist.get(position).id);
        holder.edd.setText("Access Expire: "+expire_1_5_accessArraylist.get(position).edd);
        holder.slot.setText(expire_1_5_accessArraylist.get(position).slot);
        holder.std.setText(expire_1_5_accessArraylist.get(position).std);
    }

    @Override
    public int getItemCount() {
        return expire_1_5_accessArraylist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView name,slot,uid,std,edd;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.memberName_expire_1_5_Access);
            slot=itemView.findViewById(R.id.slot_expire_1_5Access);
            uid=itemView.findViewById(R.id.uid_expire_1_5_Access);
            std=itemView.findViewById(R.id.std_expire_1_5_Access);
            edd=itemView.findViewById(R.id.edd_expire_1_5Access);
        }
    }

}
