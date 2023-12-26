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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class LiveAccessFragment extends Fragment {

    RecyclerView recyclerView;
    Context context;
    List<LiveAccessModel> liveAccessModelList=new ArrayList<>();

    public LiveAccessFragment() {
        // Required empty public constructor
    }

    public static LiveAccessFragment newInstance(String param1, String param2) {
        LiveAccessFragment fragment = new LiveAccessFragment();
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
        View view=inflater.inflate(R.layout.fragment_live_access, container, false);
        recyclerView=view.findViewById(R.id.recyclerView_liveAccess);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        Context context1=getContext();
        MyDBHelper myDBHelper=new MyDBHelper(context1);
        ArrayList<ContactModel> arrayList=myDBHelper.getLiveAccessArrayList();
        LiveAccessAdapter liveAccessAdapter=new LiveAccessAdapter(context,arrayList);
        recyclerView.setAdapter(liveAccessAdapter);

        return view;
    }

    public void okAdded(){
        Toast.makeText(getContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
    }
    public void noAdded(){
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

}