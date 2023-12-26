package com.example.managelib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class ExpireAccessFragment extends Fragment implements ExpireAccessAdapter.OnItemClickedListener{

    Context context;
    List<ExpireAccessModel> expireAccessModelList=new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayout afterRenewSelected;
    EditText inputID;

    Button renewButton,startRenew;

    LiveAccessFragment liveAccessFragment=new LiveAccessFragment();

    ExpireAccessAdapter expireAccessAdapter1=new ExpireAccessAdapter();


    public ExpireAccessFragment() {
        // Required empty public constructor
    }

    public static ExpireAccessFragment newInstance(String param1, String param2) {
        ExpireAccessFragment fragment = new ExpireAccessFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_expire_access, container, false);
        recyclerView=view.findViewById(R.id.expireAccess_recyclerview);
        afterRenewSelected=view.findViewById(R.id.afterRenewSelected);
        inputID=view.findViewById(R.id.inputId);
        renewButton=view.findViewById(R.id.renewButton);
        startRenew=view.findViewById(R.id.startRenew);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);


        Context context1=getContext();
        MyDBHelper myDBHelper=new MyDBHelper(context1);
        ArrayList<ContactModel> arrayList=myDBHelper.getExpireAccessList();


        ExpireAccessAdapter expireAccessAdapter=new ExpireAccessAdapter(context,arrayList);
        expireAccessAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(expireAccessAdapter);


        renewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afterRenewSelected.setVisibility(View.VISIBLE);
                startRenew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String enteredId=inputID.getText().toString();
                        Toast.makeText(context, "Clicked: "+enteredId, Toast.LENGTH_SHORT).show();
                        renewAccess(enteredId);
                    }
                });
            }
        });

        expireAccessAdapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void renewAccess(String id){
        Iterator<ExpireAccessModel> iterator = expireAccessModelList.iterator();
        while (iterator.hasNext()) {
            ExpireAccessModel expireAccessModel = iterator.next();
            if (expireAccessModel.uid.equals(id)) {
                String name = expireAccessModel.name;

                // Get current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; // Month starts from 0
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Print current date
                String currentDate = day + "/" + month + "/" + year;
                int nextMonth = month + 1;
                String endDate;

                if (nextMonth > 12) {
                    int nextYear = year + 1;
                    nextMonth = 1;
                    endDate = day + "/" + nextMonth + "/" + nextYear;
                    liveAccessFragment.liveAccessModelList.add(new LiveAccessModel(name, id, currentDate, endDate));
                    Toast.makeText(context, "renew successful", Toast.LENGTH_SHORT).show();
                    iterator.remove(); // Remove the current element using the iterator
                } else {
                    endDate = day + "/" + nextMonth + "/" + year;
                    liveAccessFragment.liveAccessModelList.add(new LiveAccessModel(name, id, currentDate, endDate));
                    iterator.remove(); // Remove the current element using the iterator
                    Toast.makeText(context, "remove successful", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    public void onItemClick(int position) {

    }

    public void addUsers(){

    }
}