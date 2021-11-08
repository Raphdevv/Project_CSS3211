package com.example.projectcss3211;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectFoodActivity extends AppCompatActivity {

    CardView takeQuiz,q1,q2,q3,q4,q5;
    RelativeLayout showQuiz;

    protected static int count = 1;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    ArrayList<String> foodforrandom = new ArrayList<String>();
    Map<String,Object> user = new HashMap<>();

    private ImageView food1,food2,food3,food4,food5;
    private TextView tFood1,tFood2,tFood3,tFood4,tFood5,cFood1,cFood2,cFood3,cFood4,cFood5;
    private Button done,random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food);
        takeQuiz = (CardView) findViewById(R.id.take_quiz);
        q1 = (CardView) findViewById(R.id.quiz1);
        q2 = (CardView) findViewById(R.id.quiz2);
        q3 = (CardView) findViewById(R.id.quiz3);
        q4 = (CardView) findViewById(R.id.quiz4);
        q5 = (CardView) findViewById(R.id.quiz5);

        showQuiz = (RelativeLayout) findViewById(R.id.show_quiz);

        done = (Button) findViewById(R.id.Done);
        food1 = (ImageView) findViewById(R.id.imgFood1);
        food2 = (ImageView) findViewById(R.id.imgFood2);
        food3 = (ImageView) findViewById(R.id.imgFood3);
        food4 = (ImageView) findViewById(R.id.imgFood4);
        food5 = (ImageView) findViewById(R.id.imgFood5);
        tFood1 = (TextView) findViewById(R.id.textFood1);
        tFood2 = (TextView) findViewById(R.id.textFood2);
        tFood3 = (TextView) findViewById(R.id.textFood3);
        tFood4 = (TextView) findViewById(R.id.textFood4);
        tFood5 = (TextView) findViewById(R.id.textFood5);
        cFood1 = (TextView) findViewById(R.id.textCui1);
        cFood2 = (TextView) findViewById(R.id.textCui2);
        cFood3 = (TextView) findViewById(R.id.textCui3);
        cFood4 = (TextView) findViewById(R.id.textCui4);
        cFood5 = (TextView) findViewById(R.id.textCui5);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        takeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.Pulse).duration(400).repeat(0).playOn(takeQuiz);
                YoYo.with(Techniques.FadeIn).duration(600).repeat(0).playOn(showQuiz);
                showQuiz.setVisibility(View.VISIBLE);
                DocumentReference documentReference1 = fStore.collection("myfood").document(setTakeQuiz());
                documentReference1.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        Picasso.with(getApplicationContext()).load(value.getString("images")).into(food1);
                        tFood1.setText(value.getString("food name"));
                        cFood1.setText(value.getString("cuisine"));

                        q1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if(count <= 5){
                                    YoYo.with(Techniques.Pulse).duration(400).repeat(0).playOn(q1);
                                    String userID = fAuth.getCurrentUser().getUid();
                                    DocumentReference userDoc = fStore.collection("users").document(userID);
                                    user.put("myFood "+count,value.getString("food name"));
                                    user.put("myCuisine "+count,value.getString("cuisine"));
                                    user.put("myIngredient "+count,value.get("ingredient"));
                                    count++;
                                    userDoc.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d("TAG", "onSuccess: Food is created for "+userID);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("TAG","onFailure "+e.toString());
                                        }
                                    });
                                }else{
                                    YoYo.with(Techniques.Shake).duration(400).repeat(0).playOn(q1);
                                    YoYo.with(Techniques.FadeIn).duration(600).repeat(0).playOn(done);
                                    done.setVisibility(View.VISIBLE);
                                    done.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(SelectFoodActivity.this, MainActivity.class));
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
                DocumentReference documentReference2 = fStore.collection("myfood").document(setTakeQuiz());
                documentReference2.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        Picasso.with(getApplicationContext()).load(value.getString("images")).into(food2);
                        tFood2.setText(value.getString("food name"));
                        cFood2.setText(value.getString("cuisine"));

                        q2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(count <= 5){
                                    YoYo.with(Techniques.Pulse).duration(400).repeat(0).playOn(q2);
                                    String userID = fAuth.getCurrentUser().getUid();
                                    DocumentReference userDoc = fStore.collection("users").document(userID);
                                    user.put("myFood "+count,value.getString("food name"));
                                    user.put("myCuisine "+count,value.getString("cuisine"));
                                    user.put("myIngredient "+count,value.get("ingredient"));
                                    count++;
                                    userDoc.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d("TAG", "onSuccess: Food is created for "+userID);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("TAG","onFailure "+e.toString());
                                        }
                                    });
                                }else {
                                    YoYo.with(Techniques.Shake).duration(400).repeat(0).playOn(q2);
                                    YoYo.with(Techniques.FadeIn).duration(600).repeat(0).playOn(done);
                                    done.setVisibility(View.VISIBLE);
                                    done.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(SelectFoodActivity.this, MainActivity.class));
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
                DocumentReference documentReference3 = fStore.collection("myfood").document(setTakeQuiz());
                documentReference3.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        Picasso.with(getApplicationContext()).load(value.getString("images")).into(food3);
                        tFood3.setText(value.getString("food name"));
                        cFood3.setText(value.getString("cuisine"));

                        q3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(count <= 5){
                                    YoYo.with(Techniques.Pulse).duration(400).repeat(0).playOn(q3);
                                    String userID = fAuth.getCurrentUser().getUid();
                                    DocumentReference userDoc = fStore.collection("users").document(userID);
                                    user.put("myFood "+count,value.getString("food name"));
                                    user.put("myCuisine "+count,value.getString("cuisine"));
                                    user.put("myIngredient "+count,value.get("ingredient"));
                                    count++;
                                    userDoc.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d("TAG", "onSuccess: Food is created for "+userID);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("TAG","onFailure "+e.toString());
                                        }
                                    });
                                }else {
                                    YoYo.with(Techniques.Shake).duration(400).repeat(0).playOn(q3);
                                    YoYo.with(Techniques.FadeIn).duration(600).repeat(0).playOn(done);
                                    done.setVisibility(View.VISIBLE);
                                    done.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(SelectFoodActivity.this, MainActivity.class));
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
                DocumentReference documentReference4 = fStore.collection("myfood").document(setTakeQuiz());
                documentReference4.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        Picasso.with(getApplicationContext()).load(value.getString("images")).into(food4);
                        tFood4.setText(value.getString("food name"));
                        cFood4.setText(value.getString("cuisine"));

                        q4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(count <= 5){
                                    YoYo.with(Techniques.Pulse).duration(400).repeat(0).playOn(q4);
                                    String userID = fAuth.getCurrentUser().getUid();
                                    DocumentReference userDoc = fStore.collection("users").document(userID);
                                    user.put("myFood "+count,value.getString("food name"));
                                    user.put("myCuisine "+count,value.getString("cuisine"));
                                    user.put("myIngredient "+count,value.get("ingredient"));
                                    count++;
                                    userDoc.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d("TAG", "onSuccess: Food is created for "+userID);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("TAG","onFailure "+e.toString());
                                        }
                                    });
                                }else {
                                    YoYo.with(Techniques.Shake).duration(400).repeat(0).playOn(q4);
                                    YoYo.with(Techniques.FadeIn).duration(600).repeat(0).playOn(done);
                                    done.setVisibility(View.VISIBLE);
                                    done.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(SelectFoodActivity.this, MainActivity.class));
                                        }
                                    });
                                }
                            }
                        });
                    }
                });

                DocumentReference documentReference5 = fStore.collection("myfood").document(setTakeQuiz());
                documentReference5.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        Picasso.with(getApplicationContext()).load(value.getString("images")).into(food5);
                        tFood5.setText(value.getString("food name"));
                        cFood5.setText(value.getString("cuisine"));

                        q5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(count <= 5){
                                    YoYo.with(Techniques.Pulse).duration(400).repeat(0).playOn(q5);
                                    String userID = fAuth.getCurrentUser().getUid();
                                    DocumentReference userDoc = fStore.collection("users").document(userID);
                                    user.put("myFood "+count,value.getString("food name"));
                                    user.put("myCuisine "+count,value.getString("cuisine"));
                                    user.put("myIngredient "+count,value.get("ingredient"));
                                    count++;
                                    userDoc.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d("TAG", "onSuccess: Food is created for "+userID);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("TAG","onFailure "+e.toString());
                                        }
                                    });
                                }else {
                                    YoYo.with(Techniques.Shake).duration(400).repeat(0).playOn(q5);
                                    YoYo.with(Techniques.FadeIn).duration(600).repeat(0).playOn(done);
                                    done.setVisibility(View.VISIBLE);
                                    done.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(SelectFoodActivity.this, MainActivity.class));
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    protected String setTakeQuiz(){
        int i;
        i = (int) (Math.random()*100)+1;
        return String.valueOf(i);
    }
}