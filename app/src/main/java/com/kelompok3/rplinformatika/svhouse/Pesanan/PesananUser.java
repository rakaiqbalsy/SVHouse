package com.kelompok3.rplinformatika.svhouse.Pesanan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelompok3.rplinformatika.svhouse.Model.Pemesan;
import com.kelompok3.rplinformatika.svhouse.Model.User;
import com.kelompok3.rplinformatika.svhouse.R;

import java.util.ArrayList;

public class PesananUser extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference table_user;

    private ListView listView;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    Pemesan pemesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_user);

        pemesan = new Pemesan();

        listView = findViewById(R.id.ListViewPesanan);
        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("pesanan");

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.pesanan_info, R.id.textViewPesanan, list);

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    pemesan =  ds.getValue(Pemesan.class);
                    list.add(pemesan.getTextNamaRumah().toString()+" "+pemesan.getTextNamaPemesan().toString());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}