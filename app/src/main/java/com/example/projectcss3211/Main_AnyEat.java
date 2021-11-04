package com.example.projectcss3211;

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

    View context;
    private Button btn_next;
    private ImageView anyyeat;
    protected ProgressBar progressBar;
    protected ObjectAnimator progressAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_any_eat);
        anyyeat = findViewById(R.id.imageView4);
        context = (View) findViewById(R.id.test);
        YoYo.with(Techniques.Pulse).duration(1200).repeat(0).playOn(anyyeat);
        init();
        progressAnim.setDuration(3000);
        progressAnim.start();
        progressAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startActivity(new Intent(Main_AnyEat.this, RegisterActivity.class));
                finish();
            }
        });
    }

    protected void init(){
        progressBar = findViewById(R.id.progressBar2);
        progressAnim = ObjectAnimator.ofInt(progressBar,"progress",0,100);
    }
}