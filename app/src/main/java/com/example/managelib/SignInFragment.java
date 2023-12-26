package com.example.managelib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInFragment extends Fragment {
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;


    EditText inputEmail_edittext,inputSetPassword_editText,inputConfirmPassword_EditText,inputName_editText,inputAddress_editText;
    Button register;
    public SignInFragment() {
        // Required empty public constructor
    }
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
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
        View view=inflater.inflate(R.layout.fragment_sign_in, container, false);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        inputEmail_edittext=view.findViewById(R.id.email_signUp);
        inputSetPassword_editText=view.findViewById(R.id.setPassword_signUp);
        inputConfirmPassword_EditText=view.findViewById(R.id.ConfirmPassword_signUP);
        inputName_editText=view.findViewById(R.id.name_signup);
        inputAddress_editText=view.findViewById(R.id.address_signup);
        progressBar=view.findViewById(R.id.progressBar_signup);
        register=view.findViewById(R.id.signupButton);



        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                String name=inputName_editText.getText().toString();
                String address=inputAddress_editText.getText().toString();
                String inputEmailAddress=inputEmail_edittext.getText().toString();
                String SetPassword=inputSetPassword_editText.getText().toString();
                String ConfirmPassword=inputConfirmPassword_EditText.getText().toString();
                String email = inputEmail_edittext.getText().toString().trim();


                if (TextUtils.isEmpty(email)){
                    inputEmail_edittext.setError("Please Enter E Mail");
                }
                if (TextUtils.isEmpty(SetPassword)){
                    inputSetPassword_editText.setError("Please Enter Password");
                    if (SetPassword.length()<6){
                        inputSetPassword_editText.setError("Password Should be Minimum of 6 Letters");
                    }
                }
                if (TextUtils.isEmpty(ConfirmPassword)){
                    inputConfirmPassword_EditText.setError("Please Enter Confirm Password");
                }
                if (!isEmailValid(email)) {
                    inputEmail_edittext.setError("Invalid Email Address");
                }
                if (!ConfirmPassword.equals(SetPassword)){
                    inputConfirmPassword_EditText.setError("Password Not Match");
                }



                firebaseAuth.createUserWithEmailAndPassword(inputEmailAddress,SetPassword).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()){
                            String userId=task.getResult().getUser().getUid();
                            DatabaseReference databaseReference=firebaseDatabase.getReference().child("user").child(userId);
                            StorageReference storageReference=firebaseStorage.getReference().child("Upload").child(userId);

                            Users users=new Users(name,address,email,SetPassword);
                            databaseReference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent=new Intent(getActivity(),MainActivity2.class);
                                        startActivity(intent);
                                        if (getActivity() != null) {
                                            getActivity().finish(); // Correct the finish() call here
                                        }
                                        Toast.makeText(getActivity(), "id created successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(getActivity(), "Error in Creating the User", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                        else{
                            Toast.makeText(getActivity(), "an error occured", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        return view;
    }

    private boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}