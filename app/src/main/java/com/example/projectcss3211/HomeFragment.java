package com.example.projectcss3211;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Source;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    ScrollView scrollView;

    private CardView searchCard,cuisineCard,resCard;
    private RelativeLayout rest_layout;
    String userID;
    static String username="";

    ImageView img;
    TextView text;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        text = (TextView) view.findViewById(R.id.test_home);
        rest_layout = (RelativeLayout) view.findViewById(R.id.rest);
        YoYo.with(Techniques.Bounce).duration(700).repeat(0).playOn(rest_layout);
        searchCard = (CardView) view.findViewById(R.id.search_card);
        cuisineCard = (CardView) view.findViewById(R.id.cuisine_card);
        resCard = (CardView) view.findViewById(R.id.res_card);
        scrollView = (ScrollView) view.findViewById(R.id.contentHome);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        searchCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.Pulse).duration(400).repeat(0).playOn(searchCard);
                startActivity(new Intent(getContext(),FindOutActivity.class));

            }
        });

        cuisineCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.Pulse).duration(400).repeat(0).playOn(cuisineCard);
            }
        });

        resCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.Pulse).duration(400).repeat(0).playOn(resCard);

            }
        });

        return view;
    }
}