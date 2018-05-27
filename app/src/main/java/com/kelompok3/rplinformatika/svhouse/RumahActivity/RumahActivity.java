package com.kelompok3.rplinformatika.svhouse.RumahActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelompok3.rplinformatika.svhouse.Developer.RumahAdapter;
import com.kelompok3.rplinformatika.svhouse.Developer.Upload;
import com.kelompok3.rplinformatika.svhouse.R;

import java.util.ArrayList;
import java.util.List;

public class RumahActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RumahAdapter mAdapter;

    private ProgressBar mProggresBar;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rumah);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();

        mProggresBar = findViewById(R.id.progress_circle);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapsot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapsot.getValue(Upload.class);
                    mUploads.add(upload);
                }

                mAdapter = new RumahAdapter(RumahActivity.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
                mProggresBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RumahActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProggresBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}