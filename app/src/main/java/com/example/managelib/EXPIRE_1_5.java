package com.example.managelib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class EXPIRE_1_5 extends Fragment {

    Context context;
    TextView textView;
    Button renew_button_expire_1_5_access;
    RecyclerView recyclerView;

    public EXPIRE_1_5() {
        // Required empty public constructor
    }

    public static EXPIRE_1_5 newInstance(String param1, String param2) {
        EXPIRE_1_5 fragment = new EXPIRE_1_5();
        Bundle args = new Bundle();
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
        View view=inflater.inflate(R.layout.fragment_e_x_p_i_r_e_1_5, container, false);
        recyclerView=view.findViewById(R.id.expireAccessRecyclerView1_5);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        Context context1=getContext();
        MyDBHelper myDBHelper=new MyDBHelper(context1);
        ArrayList<ContactModel> arrayList=myDBHelper.getMembersLeftFiveDaysOrLess();

        Expire_1_5_Adapter expire_1_5_adapter=new Expire_1_5_Adapter(context,arrayList);
        recyclerView.setAdapter(expire_1_5_adapter);


        return  view;
    }

    Connection connection;
    String ip, name, port, database, password;


    public Connection connectionHelper_Class() {
        ip = "127.0.0.1";
        database = "ManageLib";
        port = "3306";
        name = "root";
        password = "#Ss@11102003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + ip + "/" + database;
            connection = DriverManager.getConnection(url, name, password);
            if (connection != null) {
                System.out.println("Connected to the database");
                textView.setText("connected");
                Toast.makeText(getContext(), "connected", Toast.LENGTH_SHORT).show();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found");
            Toast.makeText(getContext(), "MySQL JDBC Driver not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed");
            Toast.makeText(getContext(), "Connection failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return connection;

    }


}