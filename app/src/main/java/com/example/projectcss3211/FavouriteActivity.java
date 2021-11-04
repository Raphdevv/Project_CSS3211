package com.example.projectcss3211;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class FavouriteActivity extends AppCompatActivity {

    private MaterialButton btn_home,btn_fav,btn_person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        btn_home = (MaterialButton) findViewById(R.id.home_main);
        btn_person = (MaterialButton) findViewById(R.id.person);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavouriteActivity.this,MainActivity.class);
                startActivity(intent);
                switch (view.getId()){
                    case R.id.home_main:
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                }
                finish();
            }
        });
        btn_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavouriteActivity.this,PersonActivity.class);
                startActivity(intent);
                switch (view.getId()){
                    case R.id.person:
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                }
                finish();
            }
        });
    }
}