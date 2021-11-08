package com.example.projectcss3211;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultActivity extends AppCompatActivity {


    ImageView resultimg;
    Button back;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView resultText;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        back = (Button) findViewById(R.id.backtomain);
        resultText = (TextView) findViewById(R.id.textResultfinal);
        resultimg = (ImageView) findViewById(R.id.imgResultfinal);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userID);

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String[] userInRand = new String[5];
                int randCu;
                int randIn = 0;
                final int[] ticket = {0};
                String dishName = null, cuisine;
                String cuisineDemo;

                DocumentSnapshot doc = task.getResult();
                ArrayList<String> ingredient1 = (ArrayList<String>) doc.get("myIngredient 1");
                ArrayList<String> ingredient2 = (ArrayList<String>) doc.get("myIngredient 2");
                ArrayList<String> ingredient3 = (ArrayList<String>) doc.get("myIngredient 3");
                ArrayList<String> ingredient4 = (ArrayList<String>) doc.get("myIngredient 4");
                ArrayList<String> ingredient5 = (ArrayList<String>) doc.get("myIngredient 5");
                ArrayList<String> cuisineRand = new ArrayList<>();

                cuisineRand.add(doc.getString("myCuisine 1"));
                cuisineRand.add(doc.getString("myCuisine 2"));
                cuisineRand.add(doc.getString("myCuisine 3"));
                cuisineRand.add(doc.getString("myCuisine 4"));
                cuisineRand.add(doc.getString("myCuisine 5"));

                randCu = (int) (Math.random() * cuisineRand.size());

                cuisineDemo = (String)  cuisineRand.get(randCu);

                ArrayList<String> userIngredient= new ArrayList<String>();
                userIngredient.addAll(ingredient1);
                userIngredient.addAll(ingredient2);
                userIngredient.addAll(ingredient3);
                userIngredient.addAll(ingredient4);
                userIngredient.addAll(ingredient5);

                for (int i = 0; i < 5; i++) {
                    randIn = (int) (Math.random() * userIngredient.size());
                    userInRand[i] = (String) userIngredient.get(randIn);
                }
                List userTagsList = Arrays.asList(userInRand.clone());

                for(int i = 1;i <= 767;i++){
                    DocumentReference docDatabase = fStore.collection("myfood").document(String.valueOf(i));
                    docDatabase.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                            if(cuisineDemo.equals(value.getString("cuisine"))){
                                for (int i = 0; i < 5; i++) {
                                    System.out.println("Loop In Checked"); // debug
                                    if (userIngredient.contains(userTagsList.get(i))) {

                                        ticket[0]++;
                                    }
                                }
                                if (ticket[0] == 5) {
                                    Picasso.with(getApplicationContext()).load(value.getString("images")).into(resultimg);
                                    resultText.setText(value.getString("food name"));
                                }
                            }
                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}