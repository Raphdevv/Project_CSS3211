package com.example.projectcss3211;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email_user,pass;
    private Button login_btn,signUp;

    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_btn = (Button) findViewById(R.id.button_log);
        signUp = (Button) findViewById(R.id.buttonSign);
        email_user = (EditText) findViewById(R.id.email_text);
        pass = (EditText) findViewById(R.id.password_text);
        progressBar = (ProgressBar) findViewById(R.id.progressBarLog);
        fAuth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_user.getText().toString().trim();
                String password = pass.getText().toString().trim();


                if(TextUtils.isEmpty(email)) {
                    email_user.setError("Email is Required!!");
                    YoYo.with(Techniques.Shake).duration(1000).repeat(0).playOn(email_user);
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    pass.setError("Password is Required!!");
                    YoYo.with(Techniques.Shake).duration(1000).repeat(0).playOn(pass);
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Login is Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            switch (view.getId()){
                                case R.id.button_log:
                                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                    break;
                            }
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"Error ! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                switch (view.getId()){
                    case R.id.buttonSign:
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                }
                finish();
            }
        });
    }
}