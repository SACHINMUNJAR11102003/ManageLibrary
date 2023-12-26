package com.example.managelib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;

public class Login_Signup extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FrameLayout frameLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        frameLayout=findViewById(R.id.frameLayout);
        firebaseAuth=FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()==null){
            loadFragment(new LoginFragment());
        }
        else {
            Intent intent=new Intent(Login_Signup.this,MainActivity2.class);
            startActivity(intent);
            finish();
        }
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }
}