package com.kelompok3.rplinformatika.svhouse.loginAndRegister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kelompok3.rplinformatika.svhouse.R;

import info.hoang8f.widget.FButton;

public class AwalActivity extends AppCompatActivity {

    private Button btnSign,btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);

        btnSign = findViewById(R.id.btnMasuk);
        btnSignUp = findViewById(R.id.btnRegister);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AwalActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AwalActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
