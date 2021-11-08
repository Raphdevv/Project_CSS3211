package com.example.projectcss3211;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    LinearLayout cakeee;
    View view;

    protected EditText email_input,user_input,pass_input,con_input;
    protected Button signup_btn,backLogin;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ProgressBar progressBar;
    String userID;
    Map<String,Object> user = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        view = (View) findViewById(R.id.view);
        email_input = (EditText) findViewById(R.id.email_text);
        user_input = (EditText) findViewById(R.id.user_text);
        pass_input = (EditText) findViewById(R.id.password_text);
        con_input = (EditText) findViewById(R.id.confirmpassword_text);
        signup_btn = (Button) findViewById(R.id.buttonSign);
        backLogin = (Button) findViewById(R.id.butBack);
        cakeee = (LinearLayout) findViewById(R.id.cake);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_input.getText().toString().trim();
                String password = pass_input.getText().toString().trim();
                String conPassword = con_input.getText().toString().trim();
                String username = user_input.getText().toString().trim();


                if(TextUtils.isEmpty(email)) {
                    email_input.setError("Email is Required!!");
                    YoYo.with(Techniques.Shake).duration(1000).repeat(0).playOn(email_input);
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    pass_input.setError("Password is Required!!");
                    YoYo.with(Techniques.Shake).duration(1000).repeat(0).playOn(pass_input);

                    return;
                }

                if (!conPassword.equals(password)){
                    con_input.setError("Password not Equals!!");
                    YoYo.with(Techniques.Shake).duration(1000).repeat(0).playOn(con_input);
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"User created.", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            user.put("password", password);
                            user.put("username",username);
                            user.put("Email", email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG", "onSuccess: user Profile is created for "+userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG","onFailure "+e.toString());
                                }
                            });
                            startActivity(new Intent(RegisterActivity.this,SelectFoodActivity.class));
                            switch (view.getId()){
                                case R.id.buttonSign:
                                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                    break;
                            }
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this,"Error ! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                switch (view.getId()){
                    case R.id.butBack:
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                }
                finish();
            }
        });
    }
}