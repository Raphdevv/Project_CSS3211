package com.example.projectcss3211;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Main_AnyEat extends AppCompatActivity {

    private Button btn_next;
    private ImageView anyyeat;
    protected ObjectAnimator progressAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_any_eat);
        btn_next = (Button) findViewById(R.id.next);
        anyyeat = findViewById(R.id.imageView4);
        YoYo.with(Techniques.Bounce).duration(1200).repeat(0).playOn(anyyeat);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_AnyEat.this,RegisterActivity.class));
                finish();
            }
        });

    }


}