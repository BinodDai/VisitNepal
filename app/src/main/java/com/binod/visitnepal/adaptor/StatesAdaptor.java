package com.binod.visitnepal.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.binod.visitnepal.R;
import com.binod.visitnepal.model.StatesModelling;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class StatesAdaptor extends RecyclerView.Adapter<StatesAdaptor.StatesViewHolder>  {

    private DatabaseReference databaseReference;
    private Context context;
    private List<StatesModelling> statesModellingList;

    public StatesAdaptor(List<StatesModelling> statesModellingList,Context context) {
        this.statesModellingList = statesModellingList;
        this.context = context;
    }

    @NonNull
    @Override
    public StatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.state_item,parent,false);
        StatesViewHolder svh=new StatesViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull StatesViewHolder holder, int position) {
       //holder.imageView.setImageURI(statesModellingList.get(position).getProvinceImage());
       holder.textView.setText(statesModellingList.get(position).getProvinceName());
        Glide.with(context)
                .load(statesModellingList.get(position).getProvinceImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return statesModellingList.size();
    }

    public static class StatesViewHolder extends RecyclerView.ViewHolder {


        public ImageView imageView;
        public TextView textView;
        public StatesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.stateImage);
            textView=itemView.findViewById(R.id.stateName);
        }
    }



}

