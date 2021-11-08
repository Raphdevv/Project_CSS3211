package com.example.projectcss3211;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth fAuth;
    Map<String,Object> food = new HashMap<>();
    FirebaseFirestore fStore;
    String foodID;
    String name;
    String cuisine;
    String imgFood;
    ArrayList<String> ingredientTags = new ArrayList<String>();

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_conner, new HomeFragment()).commit();

        bottomNavigationView.setSelectedItemId(R.id.bottom_navigation_main);
        fStore = FirebaseFirestore.getInstance();
//        setJsonFood();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.fav:
                        fragment = new FavouriteFragment();
                        break;
                    case R.id.person:
                        fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_conner, fragment).commit();
                return true;
            }
        });

    }


//        public void setJsonFood () {
//            String json=null;
//            for (int j = 744; j <= 15000; j++) {
//                String files = null;
//                if ((j <= 10))
//                    files = "meta0000"+j+".json";
//                else if (j <= 99)
//                    files = "meta000"+j+".json";
//                else if (j <= 999)
//                    files = "meta00" + j + ".json";
//                else if (j <= 9999)
//                    files = "meta0" + j + ".json";
//                else {
//                    files = "meta" + j + ".json";
//                }
//
//                try {
//                    InputStream inputStream = getAssets().open(files);
//                    int size = inputStream.available();
//                    byte[] buffer = new byte[size];
//                    inputStream.read(buffer);
//                    inputStream.close();
//                    json = new String(buffer, "UTF-8");
//                    JSONObject object = new JSONObject(json);
//                    JSONObject attributes = object.getJSONObject("attributes");
//                    JSONArray ingredient = object.getJSONArray("ingredientLines");
//                    JSONArray img = object.getJSONArray("images");
//                    JSONObject imgJSONObject = img.getJSONObject(0);
//                    imgFood = imgJSONObject.getJSONObject("imageUrlsBySize").getString("360");
//                    cuisine = attributes.getJSONArray("cuisine").getString(0);
//                    name = object.getString("name");
//                    for (int i = 0; i < ingredient.length(); i++) {
//                        String sub = (String) ingredient.getString(i);
//                        String regexSub = sub.replaceAll("[0-9]", "")
//                                .replaceAll(" cups ", "")
//                                .replaceAll(" cup ", "")
//                                .replaceAll(" tsp ", "")
//                                .replaceAll(" tbsp ", "")
//                                .replaceAll("½", "")
//                                .replaceAll("¼", "")
//                                .replaceAll(" teaspoon ", "")
//                                .replaceAll(" teaspoons ", "")
//                                .replaceAll(" tablespoon ", "")
//                                .replaceAll(" tablespoons ", "")
//                                .replaceAll(" -inch ", "")
//                                .replaceAll("-inch", "")
//                                .replaceAll(" ounces ", "")
//                                .replaceAll("[Z0-9]", " ")
//                                .replaceAll(" clove ", "")
//                                .replaceAll(" lb ", "")
//                                .replaceAll(" pound ", "")
//                                .replaceAll("[\\[\\](){}]","")
//                                .replaceAll("/", "");
//                        ingredientTags.add(regexSub);
//                    }
//                    DocumentReference documentReference = fStore.collection("myfood").document(String.valueOf(j));
//                    food.put("images", imgFood);
//                    food.put("ingredient", ingredientTags);
//                    food.put("cuisine", cuisine);
//                    food.put("food name", name);
//                    documentReference.set(food).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            Log.d("TAG", "onSuccess: My food is created for "+foodID);
//
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.d("TAG","onFailure "+e.toString());
//                        }
//                    });
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
}