package com.example.managelib;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.util.UUID;

public class AddMember extends Fragment {

    Context context;
    EditText newMemberNameEditText,newMemberPhoneEditText,newMemberSlotEditText,newMemberAadharcardEdittext;
    Button addButton;

   
    public AddMember() {
        // Required empty public constructor
    }
    public static AddMember newInstance(String param1, String param2) {
        AddMember fragment = new AddMember();
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

        View view=inflater.inflate(R.layout.fragment_add_member, container, false);
        newMemberNameEditText=view.findViewById(R.id.addMember_name);
        newMemberPhoneEditText=view.findViewById(R.id.addMember_MobileNumber);
        newMemberSlotEditText=view.findViewById(R.id.addMember_ChooseSlot);
        newMemberAadharcardEdittext = view.findViewById(R.id.addMember_aadharNumber);
        addButton=view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UUID uuid = UUID.randomUUID();
                String randomUUIDString = uuid.toString();

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; // Month starts from 0
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Print current date
                String currentDate = day + "-" + month + "-" + year;
                int nextMonth = month + 1;
                String endDate;
                MyDBHelper myDBHelper=new MyDBHelper(context);

                if (nextMonth > 12) {
                    int nextYear = year + 1;
                    nextMonth = 1;
                    endDate = day + "-" + nextMonth + "-" + nextYear;
                    String name=newMemberNameEditText.getText().toString();
                    String phone=newMemberPhoneEditText.getText().toString();
                    String slot=newMemberSlotEditText.getText().toString();
                    String aadhar=newMemberAadharcardEdittext.getText().toString();


                    if (name.isEmpty() || phone.isEmpty() || slot.isEmpty() || aadhar.isEmpty()) {
                        Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        return; // Stop execution if any field is empty
                    }


                    myDBHelper.addContact(name,phone,slot,aadhar,currentDate,endDate);
                    Toast.makeText(getContext(), "Added Successfully 1", Toast.LENGTH_SHORT).show();
                    //allAccessFragment.allAccessModelList.add(new AllAccessModel(name,randomUUIDString,currentDate,aadhar,phone,slot));
                    //liveAccessFragment.liveAccessModelList.add(new LiveAccessModel(name, randomUUIDString, currentDate, endDate));
                } else {
                    endDate = day + "-" + nextMonth + "-" + year;
                    String name=newMemberNameEditText.getText().toString();
                    String phone=newMemberPhoneEditText.getText().toString();
                    String slot=newMemberSlotEditText.getText().toString();
                    String aadhar=newMemberAadharcardEdittext.getText().toString();

                    if (name.isEmpty() || phone.isEmpty() || slot.isEmpty() || aadhar.isEmpty()) {
                        Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        return; // Stop execution if any field is empty
                    }

                    myDBHelper.addContact(name,phone,slot,aadhar,currentDate,endDate);
                    Toast.makeText(getContext(), "Added "+endDate, Toast.LENGTH_SHORT).show();
                    //allAccessFragment.allAccessModelList.add(new AllAccessModel(name,randomUUIDString,currentDate,aadhar,phone,slot));
                    //liveAccessFragment.liveAccessModelList.add(new LiveAccessModel(name, randomUUIDString, currentDate, endDate));
                }



            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

}