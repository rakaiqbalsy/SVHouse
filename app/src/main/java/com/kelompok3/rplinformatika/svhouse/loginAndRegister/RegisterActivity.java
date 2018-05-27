package com.kelompok3.rplinformatika.svhouse.loginAndRegister;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelompok3.rplinformatika.svhouse.Model.User;
import com.kelompok3.rplinformatika.svhouse.R;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    private EditText daftarNumber, daftarEmail, daftarNama, daftarPassword;
    private Button btnDaftar;
    private TextView textViewMasuk;

    private FirebaseDatabase database;
    private DatabaseReference table_user;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        daftarNumber = findViewById(R.id.daftarNumber);
        daftarEmail = findViewById(R.id.daftarEmail);
        daftarNama = findViewById(R.id.daftarNama);
        daftarPassword = findViewById(R.id.daftarPassword);

        btnDaftar = findViewById(R.id.btnSignUp2);
        textViewMasuk = findViewById(R.id.textViewMasuk);


        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("user");

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mProgressDialog = new ProgressDialog(RegisterActivity.this);
                mProgressDialog.setMessage("Daftar Akun ....");
                mProgressDialog.show();

                final String email = daftarEmail.getText().toString().trim();
                final String password = daftarPassword.getText().toString().trim();
                final String nama = daftarNama.getText().toString().trim();
                final String number = daftarNumber.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this, "Input Email Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Input Password Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(nama)) {
                    Toast.makeText(RegisterActivity.this, "Input Nama Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(RegisterActivity.this, "Input NO HP  Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Berhasil Daftar", Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(i);
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, "Input Email Anda", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(number).exists()) {
                            mProgressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Number Handphone Telah Terdaftar", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mProgressDialog.dismiss();
                            User user = new User(email, nama, password);
                            table_user.child(number).setValue(user);
                            Toast.makeText(RegisterActivity.this, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        textViewMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
