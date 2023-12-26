package com.example.managelib;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteMemberFragment extends Fragment {
    public DeleteMemberFragment() {
        // Required empty public constructor
    }

    public static DeleteMemberFragment newInstance(String param1, String param2) {
        DeleteMemberFragment fragment = new DeleteMemberFragment();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_delete_member, container, false);
        EditText editText=view.findViewById(R.id.IDtoBeDeleted);
        Button button=view.findViewById(R.id.buttonIDdelete);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(editText.getText().toString());

                Context context=getContext();
                MyDBHelper myDBHelper=new MyDBHelper(context);
                myDBHelper.deleteContact(id);
                Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}