package com.kelompok3.rplinformatika.svhouse.Developer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kelompok3.rplinformatika.svhouse.R;
import com.kelompok3.rplinformatika.svhouse.RumahActivity.PesanActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by rakaiqbalsy on 5/24/18.
 */

public class RumahAdapter extends RecyclerView.Adapter<RumahAdapter.RumahViewHolder> {

    private Context mContext;
    private List<Upload> mUpload;

    public RumahAdapter(Context mContext, List<Upload> mUpload) {
        this.mContext = mContext;
        this.mUpload = mUpload;
    }

    @Override
    public RumahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rumah_row, parent, false);

        final RumahViewHolder viewHolder = new RumahViewHolder(v);

        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, PesanActivity.class);
                i.putExtra("home_name",mUpload.get(viewHolder.getAdapterPosition()).getmName());
                i.putExtra("home_jenis",mUpload.get(viewHolder.getAdapterPosition()).getmJenis());
                i.putExtra("home_harga",mUpload.get(viewHolder.getAdapterPosition()).getmHarga());
                i.putExtra("home_alamat",mUpload.get(viewHolder.getAdapterPosition()).getmAlamat());
                i.putExtra("home_image",mUpload.get(viewHolder.getAdapterPosition()).getmImageUrl());

                mContext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RumahViewHolder holder, int position) {

        Upload uploadCurrent = mUpload.get(position);
        holder.aRumah.setText(uploadCurrent.getmName());
        holder.aJenisRumah.setText(uploadCurrent.getmJenis());
        holder.aHargaRumah.setText(uploadCurrent.getmHarga());
        holder.aAlamatRumah.setText(uploadCurrent.getmAlamat());
        Picasso.with(mContext)
                .load(uploadCurrent.getmImageUrl())
                .fit()
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(holder.aImage);
    }

    @Override
    public int getItemCount() {
        return mUpload.size();
    }

    public class RumahViewHolder extends RecyclerView.ViewHolder{

        public TextView aRumah;
        public ImageView aImage;
        public TextView aRating;
        public TextView aJenisRumah;
        public TextView aHargaRumah;
        public TextView aAlamatRumah;
        public LinearLayout view_container;

        public RumahViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.kontener);
            aRumah = itemView.findViewById(R.id.aaRumah);
            aImage = itemView.findViewById(R.id.aaImage);
            aRating = itemView.findViewById(R.id.aaRating);
            aJenisRumah = itemView.findViewById(R.id.aaJenisRumah);
            aHargaRumah = itemView.findViewById(R.id.aaHargaRumah);
            aAlamatRumah = itemView.findViewById(R.id.aaAlamatRumah);

        }
    }
}
