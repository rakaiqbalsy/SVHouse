package com.kelompok3.rplinformatika.svhouse;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kelompok3.rplinformatika.svhouse.Developer.Upload;
import com.kelompok3.rplinformatika.svhouse.Developer.UploadActivity;
import com.kelompok3.rplinformatika.svhouse.Pesanan.PesananUser;
import com.kelompok3.rplinformatika.svhouse.RumahActivity.RumahActivity;
import com.kelompok3.rplinformatika.svhouse.loginAndRegister.LoginActivity;

public class DashboardActivity extends AppCompatActivity {

    private Button upload, cari, pesananUser, pesananAdmin, logout;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        firebaseAuth = FirebaseAuth.getInstance();

        upload = findViewById(R.id.btnUploadImage);
        cari  = findViewById(R.id.btnCariRumah);
        pesananUser = findViewById(R.id.btnPesananUser);
        logout = findViewById(R.id.btnLogout);
        pesananAdmin = findViewById(R.id.btnPesananAdmin);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, RumahActivity.class);
                startActivity(i);
            }
        });

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, UploadActivity.class);
                startActivity(i);
            }
        });

        pesananUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, PesananUser.class);
                startActivity(i);
            }
        });

        pesananAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(DashboardActivity.this, KeranjangActivity.class);
                startActivity(j);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                Intent i = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });


    }
}
