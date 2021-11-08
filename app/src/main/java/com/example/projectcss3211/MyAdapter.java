package com.example.projectcss3211;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public String [] menufood, discrip;
    public int[] image;
    public Context context;

    public MyAdapter(Context ct, String[] menuFood, String[] discription, int[] img){
        context = ct;
        menufood = menuFood;
        discrip = discription;
        image = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_quiz,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(menufood[position]);
        holder.myText2.setText(discrip[position]);
        holder.myImage.setImageResource(image[position]);
    }

    @Override
    public int getItemCount() {
        return image.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView myText1, myText2;
        public ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.menuFood_txt);
            myText2 = itemView.findViewById(R.id.discription_txt);
            myImage = itemView.findViewById(R.id.imageViewFood);
        }
    }
}
