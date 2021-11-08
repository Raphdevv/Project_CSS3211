package com.example.projectcss3211;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class FindOutActivity extends AppCompatActivity {

    FrameLayout conShow;
    Button find;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_out);
        conShow = (FrameLayout) findViewById(R.id.find);
        YoYo.with(Techniques.FadeInDown).duration(1500).repeat(0).playOn(conShow);
        backBtn = (ImageView) findViewById(R.id.btnback);
        find = (Button) findViewById(R.id.fineOut);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindOutActivity.this, LoadActivity.class));
            }
        });
    }
}