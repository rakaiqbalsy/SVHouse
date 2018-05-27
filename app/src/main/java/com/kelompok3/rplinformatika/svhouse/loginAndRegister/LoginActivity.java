package com.kelompok3.rplinformatika.svhouse.loginAndRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelompok3.rplinformatika.svhouse.Common.Common;
import com.kelompok3.rplinformatika.svhouse.DashboardActivity;
import com.kelompok3.rplinformatika.svhouse.Developer.UploadActivity;
import com.kelompok3.rplinformatika.svhouse.Model.User;
import com.kelompok3.rplinformatika.svhouse.R;
import com.kelompok3.rplinformatika.svhouse.RumahActivity.RumahActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG="LoginActivity";

    private EditText pLoginNumber, pLoginPassword, pLoginEmail;
    private Button btnSign1, btnSignUp1;


    private FirebaseDatabase database;
    private DatabaseReference table_user;
    private FirebaseAuth firebaseAuth;


    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        pLoginEmail = findViewById(R.id.loginEmail);
        pLoginPassword = findViewById(R.id.loginPassword);
        btnSign1 = findViewById(R.id.btnLogin1);
        btnSignUp1 = findViewById(R.id.btnDaftar1);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(i);
        }

//        database = FirebaseDatabase.getInstance();
  //      table_user = database.getReference("user");

        btnSign1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = pLoginEmail.getText().toString().trim();
                String password  = pLoginPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Input Email Anda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Input Password Anda", Toast.LENGTH_SHORT).show();
                }


                mProgressDialog = new ProgressDialog(LoginActivity.this);
                mProgressDialog.setMessage("Masuk ....");
                mProgressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task){
                                FirebaseUser data = firebaseAuth.getCurrentUser();

                                if (task.isSuccessful()) {
                                    mProgressDialog.dismiss();
                                    finish();
                                    Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                                    startActivity(i);
                                }

                            }
                        });

                /*table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(pLoginNumber.getText().toString()).exists()) {

                            mProgressDialog.dismiss();
                            User user = dataSnapshot.child(pLoginNumber.getText().toString()).getValue(User.class);

                            if (user.getPassword().equals(pLoginPassword.getText().toString())) {
                                //Intent i = new Intent(LoginActivity.this, RumahActivity.class);
                                Common.currentUser = user;
                                //startActivity(i);
                                //finish();
                                //Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            mProgressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "No Handphone tidak terdaftar !!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/
            }
        });

        btnSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }
}
