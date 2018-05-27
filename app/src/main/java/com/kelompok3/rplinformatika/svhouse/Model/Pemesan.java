package com.kelompok3.rplinformatika.svhouse.Model;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by rakaiqbalsy on 5/26/18.
 */

public class Pemesan {
    private String textNamaRumah;
    private String textHarga;
    private String textNamaPemesan;
    private String textNoHp;
    private String textEmail;
    private String textPekerjaan;
    private String gajiPemesan;
    private String cowok;
    private String cewek;
    private String cash;
    private String kredit;
    private String check;

    public Pemesan() {
    }

    public Pemesan(String textNamaRumah, String textHarga, String textNamaPemesan, String textNoHp, String textEmail, String textPekerjaan, String gajiPemesan, String cowok, String cewek, String cash, String kredit, String check) {
        this.textNamaRumah = textNamaRumah;
        this.textHarga = textHarga;
        this.textNamaPemesan = textNamaPemesan;
        this.textNoHp = textNoHp;
        this.textEmail = textEmail;
        this.textPekerjaan = textPekerjaan;
        this.gajiPemesan = gajiPemesan;
        this.cowok = cowok;
        this.cewek = cewek;
        this.cash = cash;
        this.kredit = kredit;
        this.check = check;
    }

    public String getTextNamaRumah() {
        return textNamaRumah;
    }

    public void setTextNamaRumah(String textNamaRumah) {
        this.textNamaRumah = textNamaRumah;
    }

    public String getTextHarga() {
        return textHarga;
    }

    public void setTextHarga(String textHarga) {
        this.textHarga = textHarga;
    }

    public String getTextNamaPemesan() {
        return textNamaPemesan;
    }

    public void setTextNamaPemesan(String textNamaPemesan) {
        this.textNamaPemesan = textNamaPemesan;
    }

    public String getTextNoHp() {
        return textNoHp;
    }

    public void setTextNoHp(String textNoHp) {
        this.textNoHp = textNoHp;
    }

    public String getTextEmail() {
        return textEmail;
    }

    public void setTextEmail(String textEmail) {
        this.textEmail = textEmail;
    }

    public String getTextPekerjaan() {
        return textPekerjaan;
    }

    public void setTextPekerjaan(String textPekerjaan) {
        this.textPekerjaan = textPekerjaan;
    }

    public String getGajiPemesan() {
        return gajiPemesan;
    }

    public void setGajiPemesan(String gajiPemesan) {
        this.gajiPemesan = gajiPemesan;
    }

    public String getCowok() {
        return cowok;
    }

    public void setCowok(String cowok) {
        this.cowok = cowok;
    }

    public String getCewek() {
        return cewek;
    }

    public void setCewek(String cewek) {
        this.cewek = cewek;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getKredit() {
        return kredit;
    }

    public void setKredit(String kredit) {
        this.kredit = kredit;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
