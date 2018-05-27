package com.kelompok3.rplinformatika.svhouse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelompok3.rplinformatika.svhouse.Pemesanan.PemesananActivity;
import com.kelompok3.rplinformatika.svhouse.Pesanan.PesananUser;

public class KeranjangActivity extends AppCompatActivity {

    private Button btnCek;
    private EditText Ktp;
    private FirebaseDatabase database;
    private DatabaseReference table_user;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        Ktp = findViewById(R.id.noKTPUser);
        btnCek =  findViewById(R.id.cekUser);
        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("pesanan");

        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressDialog = new ProgressDialog(KeranjangActivity.this);
                mProgressDialog.setMessage("Memesan Pilihan ....");
                mProgressDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(Ktp.getText().toString()).exists()) {
                            mProgressDialog.dismiss();
                            Toast.makeText(KeranjangActivity.this, "User Telah Melakukan Pemesanan", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(KeranjangActivity.this,NotifyActivity.class);
                            startActivity(i);
                        }
                        else {
                            mProgressDialog.dismiss();
                            Toast.makeText(KeranjangActivity.this, "User Belum Melakukan Pemesanan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });


    }
}
