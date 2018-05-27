package com.kelompok3.rplinformatika.svhouse.Model;

/**
 * Created by rakaiqbalsy on 5/25/18.
 */

public class Order {

    private String Kode;
    private String Nama;
    private String Jenis;
    private String Alamat;
    private String Harga;
    private String Cash;
    private String Kredit;

    public Order() {
    }

    public Order(String kode, String nama, String jenis, String alamat, String harga, String cash, String kredit) {
        Kode = kode;
        Nama = nama;
        Jenis = jenis;
        Alamat = alamat;
        Harga = harga;
        Cash = cash;
        Kredit = kredit;
    }

    public String getKode() {
        return Kode;
    }

    public void setKode(String kode) {
        Kode = kode;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getJenis() {
        return Jenis;
    }

    public void setJenis(String jenis) {
        Jenis = jenis;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getCash() {
        return Cash;
    }

    public void setCash(String cash) {
        Cash = cash;
    }

    public String getKredit() {
        return Kredit;
    }

    public void setKredit(String kredit) {
        Kredit = kredit;
    }
}
