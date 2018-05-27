package com.kelompok3.rplinformatika.svhouse.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.kelompok3.rplinformatika.svhouse.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rakaiqbalsy on 5/25/18.
 */

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "DataRumah.db";
    private static final int DB_VER=1;

    public Database(Context context) {
        super(context, DB_NAME,null, DB_VER);
    }

    public List<Order> getCarts() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Kode", "Nama", "Jenis", "Alamat", "Harga", "Cash", "Kredit"};
        String sqlTable ="DataRumah";

        qb.setTables(sqlTable);

        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null, null, null);

        final List<Order> result = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                result.add(new Order(c.getString(c.getColumnIndex("Kode")),
                        c.getString(c.getColumnIndex("Nama")),
                        c.getString(c.getColumnIndex("Jenis")),
                        c.getString(c.getColumnIndex("Alamat")),
                        c.getString(c.getColumnIndex("Harga")),
                        c.getString(c.getColumnIndex("Cash")),
                        c.getString(c.getColumnIndex("Kredit"))
                ));
            } while (c.moveToNext());
        }
        return  result;
    }

    public void addToCart(Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("Insert Into OrderDetail(Kode, Nama, Jenis, Alamat, Harga, Cash, Kredit) VALUES('%s', '%s','%s', '%s', '%s', '%s', '%s');",
                order.getKode(),
                order.getNama(),
                order.getJenis(),
                order.getAlamat(),
                order.getHarga(),
                order.getCash(),
                order.getKredit());
        db.execSQL(query);
    }

    public void cleanCart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("Delete From OrderDetail");
        db.execSQL(query);
    }
}