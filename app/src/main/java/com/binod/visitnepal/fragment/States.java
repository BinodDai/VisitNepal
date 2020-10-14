package com.binod.visitnepal.fragment;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.binod.visitnepal.R;
import com.binod.visitnepal.adaptor.StatesAdaptor;
import com.binod.visitnepal.model.StatesModelling;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class States extends Fragment {

    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter statesAdaptor;
    //private StatesAdaptor statesAdaptor;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<StatesModelling> statesModellingArrayList;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_states,null);

        recyclerView=v.findViewById(R.id.nav_states_recycleView);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        //array list
        statesModellingArrayList=new ArrayList<>();

        //check internet connection 
        /////ConnectivityManager connectivityManager=

        /* clear Array list */
        clearAll();

        //get data from firebase
        getDataFromFirebase();
        return v;
    }


    private void getDataFromFirebase()
    {

        Query query=databaseReference.child("stateImages");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                   // clearAll();
                    StatesModelling statesModelling=new StatesModelling();
                    statesModelling.setProvinceImage(snapshot.child("provinceImage").getValue().toString());
                    statesModelling.setProvinceName(snapshot.child("provinceName").getValue().toString());
                    statesModellingArrayList.add(statesModelling);

                }

                statesAdaptor=new StatesAdaptor(statesModellingArrayList,getContext());
                recyclerView.setAdapter(statesAdaptor);
                statesAdaptor.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void clearAll() {
        if (statesModellingArrayList !=null)
        {
            statesModellingArrayList.clear();
            if (statesAdaptor !=null)
            {
                statesAdaptor.notifyDataSetChanged();
            }
        }

        statesModellingArrayList=new ArrayList<>();
    }
}
