package com.example.managelib;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class AllAccessFragment extends Fragment {

    Context context;
    public AllAccessFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public static AllAccessFragment newInstance(String param1, String param2) {
        AllAccessFragment fragment = new AllAccessFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView;
        View view=inflater.inflate(R.layout.fragment_all_access, container, false);
        recyclerView=view.findViewById(R.id.allAccessRecyclerView);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);


        Context context1=getContext();
        MyDBHelper myDBHelper=new MyDBHelper(context1);
        ArrayList<ContactModel> arrayList=myDBHelper.fetchContacts();
        AllAccessAdapter allAccessAdapter=new AllAccessAdapter(context,arrayList);

        //AllAccessAdapter allAccessAdapter=new AllAccessAdapter(context,MyDBHelper.arrContacts);
        recyclerView.setAdapter(allAccessAdapter);

        return view;
    }

}