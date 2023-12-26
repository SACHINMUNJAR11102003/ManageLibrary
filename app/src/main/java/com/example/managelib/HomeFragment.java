package com.example.managelib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    Context context;

    Button allAccess,liveAccess,expireAccess,expire1_5;
    EditText search_bar;
    ImageButton searched_id_button;
    TextView searched_id_name,searched_id_uid,searched_id_slot;
    LinearLayout searched_id_linearLayout,main_linearLayout_homeFragment;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        context=getContext();

        MyDBHelper myDBHelper=new MyDBHelper(context);

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        allAccess=view.findViewById(R.id.allAccess);
        liveAccess=view.findViewById(R.id.liveAccess);
        expireAccess=view.findViewById(R.id.expireAccess);
        expire1_5=view.findViewById(R.id.expire1_5);
        search_bar=view.findViewById(R.id.search_bar);
        searched_id_name=view.findViewById(R.id.searched_id_name);
        searched_id_slot=view.findViewById(R.id.searched_id_slot);
        searched_id_uid=view.findViewById(R.id.searched_id_uid);
        searched_id_linearLayout=view.findViewById(R.id.searched_id_linearlayout);
        main_linearLayout_homeFragment=view.findViewById(R.id.main_linearlayout_homeFragment);
        searched_id_button=view.findViewById(R.id.searched_id_button);

        searched_id_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int searched_id=Integer.parseInt(search_bar.getText().toString());
                main_linearLayout_homeFragment.setVisibility(View.GONE);
                searched_id_linearLayout.setVisibility(View.VISIBLE);

                ArrayList<ContactModel> arrayList=myDBHelper.fetchContacts();

                boolean idFound=false;

                for (ContactModel contactModel : arrayList){
                    if (contactModel.id ==searched_id){
                        String name= contactModel.name;
                        String slot= contactModel.slot;
                        searched_id_name.setText("Name: "+name);
                        searched_id_uid.setText("ID: "+(searched_id));
                        searched_id_slot.setText("Slot: "+slot);
                        idFound=true;
                        break;
                    }
                    else {
                        searched_id_name.setText("No ID found");
                        searched_id_slot.setText("");
                        searched_id_uid.setText(String.valueOf(""));
                    }
                }

            }
        });


        expireAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new ExpireAccessFragment());
            }
        });


        liveAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new LiveAccessFragment());
            }
        });


        allAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new AllAccessFragment());
            }
        });

        expire1_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new EXPIRE_1_5());
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up the back press handling
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Check if searched_id_linearlayout is visible
                if (searched_id_linearLayout.getVisibility() == View.VISIBLE) {
                    // Handle back press when searched_id_linearlayout is visible
                    main_linearLayout_homeFragment.setVisibility(View.VISIBLE);
                    searched_id_linearLayout.setVisibility(View.GONE);
                } else {
                    // Otherwise, proceed with the default back press behavior
                    getParentFragmentManager().popBackStack();
                }
            }
        });
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_mainActivity2,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}