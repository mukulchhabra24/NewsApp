package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ArticleActivity extends AppCompatActivity {
    String article;
    TextView textView;
    String individual;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Bundle bd = getIntent().getExtras();
        article = (String) bd.get("inews");
        textView= findViewById(R.id.textView);

       read();

    }
    private void read() {

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference myRef= database.getReference(article);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                String value = dataSnapshot.getValue(String.class);
                // ..
                textView.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

            }
        };
        myRef.addValueEventListener(postListener);

    }




}