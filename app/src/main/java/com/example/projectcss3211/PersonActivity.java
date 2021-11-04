package com.example.projectcss3211;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class PersonActivity extends AppCompatActivity {

    private TextView username_show;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    private MaterialButton btn_home,btn_fav,btn_person;
    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        btn_fav = (MaterialButton) findViewById(R.id.fav);
        btn_home = (MaterialButton) findViewById(R.id.home_main);
        logout = (Button) findViewById(R.id.button);
        username_show = (TextView) findViewById(R.id.show_username);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                username_show.setText(value.getString("username"));
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonActivity.this,MainActivity.class);
                startActivity(intent);
                switch (view.getId()){
                    case R.id.home_main:
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                }
                finish();
            }
        });
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonActivity.this,FavouriteActivity.class);
                startActivity(intent);
                switch (view.getId()){
                    case R.id.fav:
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                }
                finish();
            }
        });
    }

}