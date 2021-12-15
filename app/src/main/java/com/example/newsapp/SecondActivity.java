package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    Button btnOut;
    RecyclerView newsView;
    newsAdapter newsAdapter;
    List<String> news;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnOut= findViewById(R.id.btnSignOut);
        newsView=findViewById(R.id.newsView);

        GoogleSignInClient mGoogleSignInClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                Intent intent= new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        news= new ArrayList<>();
        news.add("Headline1");
        news.add("Headline 2");
        news.add("Headline3");
        news.add("Headline4");

        final newsAdapter newsAdapter = new newsAdapter(this, news);
        newsView.setAdapter(newsAdapter);
        newsView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void signOut(){
        FirebaseAuth.getInstance().signOut();

        mGoogleSignInClient.signOut()

                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SecondActivity.this,"Signed out Succesfully", Toast.LENGTH_LONG).show();

                    }
                });
    }
}