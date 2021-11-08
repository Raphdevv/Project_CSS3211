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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

//    public void setJsonFood () {
//        ArrayList<String> ingredientTags = new ArrayList<String>();
//        String name = null;
//        String cuisine;
//        String imgFood = null;
//        String json = null;
//        for (int j = 1; j <= 100; j++) {
//            String files = null;
//            if ((j <= 9))
//                files = "meta0000" + j + ".json";
//            else if (j <= 99)
//                files = "meta000" + j + ".json";
//            else if (j <= 999)
//                files = "meta00" + j + ".json";
//            else if (j <= 9999)
//                files = "meta0" + j + ".json";
//            else files = "meta" + j + ".json";
//            try {
//                InputStream inputStream = getAssets().open("meta00001.json");
//                int size = inputStream.available();
//                byte[] buffer = new byte[size];
//                inputStream.read(buffer);
//                inputStream.close();
//                json = new String(buffer, "UTF-8");
//                JSONObject object = new JSONObject(json);
////                    JSONObject attributes = object.getJSONObject("attributes");
//                JSONArray ingredient = object.getJSONArray("ingredientLines");
////                    JSONArray img = object.getJSONArray("images");
////                    JSONObject imgJSONObject = img.getJSONObject(0);
////                    imgFood = imgJSONObject.getJSONObject("imageUrlsBySize").getString("360");
////                    cuisine = attributes.getJSONArray("cuisine").getString(0);
//                name = object.getString("name");
//                for (int i = 0; i < ingredient.length(); i++) {
//                    String sub = (String) ingredient.get(i);
//                    String regexSub = sub.replaceAll("[0-9]", "")
//                            .replaceAll(" cups ", "")
//                            .replaceAll(" cup ", "")
//                            .replaceAll(" tsp ", "")
//                            .replaceAll(" tbsp ", "")
//                            .replaceAll("½", "")
//                            .replaceAll("¼", "")
//                            .replaceAll(" teaspoon ", "")
//                            .replaceAll(" teaspoons ", "")
//                            .replaceAll(" tablespoon ", "")
//                            .replaceAll(" tablespoons ", "")
//                            .replaceAll(" -inch ", "")
//                            .replaceAll("-inch", "")
//                            .replaceAll(" ounces ", "")
//                            .replaceAll("[Z0-9]", " ")
//                            .replaceAll(" clove ", "")
//                            .replaceAll(" lb ", "")
//                            .replaceAll(" pound ", "")
//                            .replaceAll("[\\[\\](){}]","")
//                            //.replaceAll("\\s", "")
//                            .replaceAll("/", "");
//                    ingredientTags.add(regexSub);
//                }
//                foodID = name;
//                DocumentReference documentReference = fStore.collection("myfood").document(foodID);
//                food.put("ingredient", ingredientTags);
//                documentReference.set(food).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Log.d("TAG", "onSuccess: Myfood is created for " + foodID);
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d("TAG", "onFailure " + e.toString());
//                    }
//                });
//            } catch (IOException | JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}