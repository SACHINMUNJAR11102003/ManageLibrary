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

public class AllAccessAdapter extends RecyclerView.Adapter<AllAccessAdapter.Viewholder> {

    Context context;
    ArrayList<ContactModel> arrayList;

    public AllAccessAdapter(Context context,ArrayList<ContactModel> allAccessModelList) {
        this.context = context;
        this.arrayList=allAccessModelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.allaccess_row,parent,false);
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.name.setText("Name: "+ arrayList.get(position).name);
        holder.uid.setText("ID: "+ arrayList.get(position).id);
        holder.phone.setText("Phone: "+arrayList.get(position).phone);
        holder.date_joined.setText("Date Joined: "+arrayList.get(position).std);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name,uid,phone,date_joined;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.memberName_allAccess);
            uid=itemView.findViewById(R.id.uid_allAccess);
            phone=itemView.findViewById(R.id.phone_allAccess);
            date_joined=itemView.findViewById(R.id.dateJoined_allAccess);
        }
    }
}
