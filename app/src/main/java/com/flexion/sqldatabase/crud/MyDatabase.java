package com.flexion.sqldatabase.crud;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(@Nullable Context context) {
        super(context, "Student", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String qry = "create table contactbook (id integer primary key autoincrement , name TEXT, contact TEXT)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void insertData(String name, String contact) {
        SQLiteDatabase db = getWritableDatabase();
        String qry = "insert into contactbook values(null, '" + name + "', '" + contact + "')";
        db.execSQL(qry);
    }

    Cursor selectData() {

        SQLiteDatabase db = getReadableDatabase();
        String qry = "select * from contactbook";

        Cursor cursor = db.rawQuery(qry, null);

        return cursor;
    }

    public void deleteData(int userid) {

        SQLiteDatabase db = getWritableDatabase();
        String qry = "delete from contactbook where id='"+userid+"'";
        db.execSQL(qry);
    }

    public void updateData(int userid, String newname, String newcontact) {
        SQLiteDatabase db = getWritableDatabase();
        String qry = "update contactbook set name= '"+newname+"', contact='"+newcontact+"' where id='"+userid+"'";
        db.execSQL(qry);
    }
}
