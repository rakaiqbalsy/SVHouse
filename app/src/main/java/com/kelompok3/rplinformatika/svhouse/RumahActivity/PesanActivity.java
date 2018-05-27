package com.kelompok3.rplinformatika.svhouse.RumahActivity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kelompok3.rplinformatika.svhouse.Database.Database;
import com.kelompok3.rplinformatika.svhouse.Developer.Upload;
import com.kelompok3.rplinformatika.svhouse.Model.Order;
import com.kelompok3.rplinformatika.svhouse.Pemesanan.PemesananActivity;
import com.kelompok3.rplinformatika.svhouse.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class PesanActivity extends AppCompatActivity {

    private Button btnCart;

    private FirebaseDatabase database;
    private DatabaseReference uploads;
    private  TextView tv_name, tv_jenis, tv_harga, tv_alamat, tv_rating, tv_cash, tv_kredit;
    private ImageView tv_image;
    CollapsingToolbarLayout collapsingToolbarLayout;

    String kodeId ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        btnCart = findViewById(R.id.det_btnPesan);

/*     uploads = database.getReference("uploads");


        btnCart = findViewById(R.id.det_btnPesan);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart(new Order(

                ));
            }
        }); */

        //receive Data
        Intent i = getIntent();
        final String name = i.getStringExtra("home_name");
        String jenis = i.getStringExtra("home_jenis");
        final String harga = i.getStringExtra("home_harga");
        String alamat = i.getStringExtra("home_alamat");
        final String img_url = i.getStringExtra("home_image");

        tv_name = findViewById(R.id.det_namaRumah);
        tv_jenis = findViewById(R.id.det_jenisRumah);
        tv_harga = findViewById(R.id.det_harga);
        tv_alamat = findViewById(R.id.det_alamat);
        tv_image = findViewById(R.id.det_image);

        //setting values
        tv_name.setText(name);
        tv_jenis.setText(jenis);
        tv_harga.setText(harga);
        tv_alamat.setText(alamat);

        Picasso.with(this).load(img_url).fit()
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(tv_image);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PesanActivity.this, PemesananActivity.class);
                i.putExtra("name", name);
                i.putExtra("harga", harga);
                i.putExtra("img,", img_url);
                startActivity(i);
            }
        });

    }
}
