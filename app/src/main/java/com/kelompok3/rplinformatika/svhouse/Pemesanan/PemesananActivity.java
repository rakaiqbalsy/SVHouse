package com.kelompok3.rplinformatika.svhouse.Pemesanan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kelompok3.rplinformatika.svhouse.Model.Pemesan;
import com.kelompok3.rplinformatika.svhouse.Pesanan.PesananUser;
import com.kelompok3.rplinformatika.svhouse.R;

public class PemesananActivity extends AppCompatActivity {

    private EditText textNamaRumah, textHarga, textNamaPemesan, textNoHp, textEmail, textPekerjaan, gajiPemesan, noKtp;
    private RadioButton cowok, cewek, cash, kredit;
    private CheckBox check;
    private Button btn_pesan;

    private FirebaseDatabase database;
    private DatabaseReference table_user;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        noKtp = findViewById(R.id.textNoKtp);
        textNamaPemesan = findViewById(R.id.textNamaPemesan);
        btn_pesan = findViewById(R.id.datapesanan);
        textNamaRumah =findViewById(R.id.textNamaRumah);
        textHarga = findViewById(R.id.textHarga);
        textNoHp = findViewById(R.id.textNoHP);
        textEmail = findViewById(R.id.textEmail);
        textPekerjaan = findViewById(R.id.textPekerjaan);
        gajiPemesan = findViewById(R.id.gajiPemesan);

        cowok = findViewById(R.id.cowok);
        cewek = findViewById(R.id.cewek);
        cash = findViewById(R.id.cash);
        kredit = findViewById(R.id.kredit);

        check = findViewById(R.id.checkbox_persetujuan);

        Bundle dataExtra = getIntent().getExtras();
        String name = dataExtra.getString("name");
        String harga = dataExtra.getString("harga");

        textNamaRumah.setText(name);
        textHarga.setText(harga);

        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("pesanan");

        btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadData();

            }
        });
    }

    private void uploadData() {

        mProgressDialog = new ProgressDialog(PemesananActivity.this);
        mProgressDialog.setMessage("Memesan Pilihan ....");
        mProgressDialog.show();


        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(noKtp.getText().toString()).exists()) {
                    mProgressDialog.dismiss();
                    Toast.makeText(PemesananActivity.this, "ANDA TELAH MEMESAN !! Mohon Tunggu Konfrimasi", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(PemesananActivity.this, PesananUser.class);
                    startActivity(i);
                }
                else {
                    mProgressDialog.dismiss();
                    Pemesan pemesan = new Pemesan(textNamaRumah.getText().toString(), textHarga.getText().toString(),
                            textNamaPemesan.getText().toString(),textNoHp.getText().toString(), textEmail.getText().toString(),
                            textPekerjaan.getText().toString(), gajiPemesan.getText().toString(),
                            cowok.getText().toString().toString(), cewek.getText().toString(), cash.getText().toString(),
                            kredit.getText().toString(), check.getText().toString());
                    table_user.child(noKtp.getText().toString()).setValue(pemesan);
                    Toast.makeText(PemesananActivity.this, "Berhasil Memesan", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(PemesananActivity.this, PesananUser.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
