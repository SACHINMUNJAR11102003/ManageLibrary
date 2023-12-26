package com.example.managelib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpireAccessAdapter extends RecyclerView.Adapter<ExpireAccessAdapter.Veiwholder>{

    Context context;
    List<ContactModel> expireAccessModelList;
    public ExpireAccessAdapter(){

    }

    public ExpireAccessAdapter(Context context,List<ContactModel> expireAccessModelList){
        this.context=context;
        this.expireAccessModelList=expireAccessModelList;
    }

    @NonNull
    @Override
    public Veiwholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.expireaccess_row,parent,false);
        Veiwholder veiwholder=new Veiwholder(view);
        return veiwholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Veiwholder holder, int position) {
        holder.name.setText("Name: "+expireAccessModelList.get(position).name);
        holder.uid.setText("UID: "+expireAccessModelList.get(position).id);
        holder.expireDate.setText("Access Expire: "+expireAccessModelList.get(position).edd);
    }

    @Override
    public int getItemCount() {
        return expireAccessModelList.size();
    }

    public interface OnItemClickedListener {
        void onItemClick(int position);
    }

    private OnItemClickedListener itemClickedListener;


    public void setOnItemClickListener(OnItemClickedListener listener) {
        itemClickedListener = listener;
    }

    public class Veiwholder extends RecyclerView.ViewHolder{

        TextView name,uid,expireDate;

        public Veiwholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.memberName_expireAccess);
            uid=itemView.findViewById(R.id.uid_expireAccess);
            expireDate=itemView.findViewById(R.id.expireDate_expireAccess);
        }
    }
}
