package com.kelompok3.rplinformatika.svhouse.Developer;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kelompok3.rplinformatika.svhouse.DashboardActivity;
import com.kelompok3.rplinformatika.svhouse.Model.User;
import com.kelompok3.rplinformatika.svhouse.R;
import com.kelompok3.rplinformatika.svhouse.RumahActivity.RumahActivity;
import com.kelompok3.rplinformatika.svhouse.loginAndRegister.RegisterActivity;
import com.squareup.picasso.Picasso;

public class UploadActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button pilihGambar;
    private Button uploadGambar;
    private Button ViewShowUpload;
    private EditText namaRumah;
    private EditText hargaRumah;
    private EditText kodeRumah;
    private EditText alamatRumah;
    private EditText jenisRumah;
    private ProgressBar mProgressBar;
    private ImageView mImageView;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private FirebaseDatabase database;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        database = FirebaseDatabase.getInstance();
        mDatabaseRef = database.getReference("uploads");


        kodeRumah = findViewById(R.id.txtkodeRumah);
        pilihGambar = findViewById(R.id.btnPilihGambar);
        uploadGambar = findViewById(R.id.btn_upload);
        ViewShowUpload = findViewById(R.id.lihatGambar);
        namaRumah = findViewById(R.id.txtNamaRumah);
        hargaRumah = findViewById(R.id.txtHarga);
        alamatRumah = findViewById(R.id.txtAlamat);
        jenisRumah = findViewById(R.id.txtJenisRumah);
        mImageView = findViewById(R.id.imageView);
        mProgressBar = findViewById(R.id.proggresView);

        pilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openFileChooser();

            }
        });

        uploadGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadData();

            }
        });

        ViewShowUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRumahActivity();
            }
        });

    }

    private void openRumahActivity() {
        Intent i = new Intent(UploadActivity.this, RumahActivity.class);
        startActivity(i);
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadData() {

        if(mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."
                    +getFileExtension(mImageUri));

            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 5000);

                            mDatabaseRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.child(kodeRumah.getText().toString()).exists()) {
                                        //mProgressDialog.dismiss();
                                        Toast.makeText(UploadActivity.this, "Code Harus Unique", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            Toast.makeText(UploadActivity.this, "Berhasil Mengunggah", Toast.LENGTH_SHORT).show();
                            Upload upload = new Upload(namaRumah.getText().toString().trim(),
                                    hargaRumah.getText().toString().trim(),
                                    jenisRumah.getText().toString().trim(),
                                    alamatRumah.getText().toString().trim(),
                                    taskSnapshot.getDownloadUrl().toString());

                            mDatabaseRef.push().getKey();
                            mDatabaseRef.child(kodeRumah.getText().toString()).setValue(upload);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int)progress);
                        }
                    });
        }
        else {
            Toast.makeText(this, "File Tidak dipilih", Toast.LENGTH_SHORT).show();
        }
        Intent i = new Intent(UploadActivity.this, RumahActivity.class);
        startActivity(i);
    }

    private void openFileChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }
}