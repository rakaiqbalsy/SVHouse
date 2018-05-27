package com.kelompok3.rplinformatika.svhouse.Developer;

/**
 * Created by rakaiqbalsy on 5/22/18.
 */

public class Upload {

    private String mName;
    private String mHarga;
    private String mJenis;
    private String mAlamat;
    private String mImageUrl;

    public Upload() {
    }

    public Upload(String mName, String mHarga, String mJenis, String mAlamat,  String mImageUrl) {
        if(mName.trim().equals("")){
            mName = "No Name";
        }

        this.mName = mName;
        this.mHarga = mHarga;
        this.mJenis = mJenis;
        this.mAlamat = mAlamat;
        this.mImageUrl = mImageUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmHarga() {
        return mHarga;
    }

    public void setmHarga(String mHarga) {
        this.mHarga = mHarga;
    }

    public String getmJenis() {
        return mJenis;
    }

    public void setmJenis(String mJenis) {
        this.mJenis = mJenis;
    }

    public String getmAlamat() {
        return mAlamat;
    }

    public void setmAlamat(String mAlamat) {
        this.mAlamat = mAlamat;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
