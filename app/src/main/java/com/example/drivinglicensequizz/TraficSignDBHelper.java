package com.example.drivinglicensequizz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class TraficSignDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BANGLAIXE.db";
    private final static String DATABASE_PATH = "/data/data/com.example.drivinglicensequizz/databases/";
    private Context mContext;

    public TraficSignDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public SQLiteDatabase openDatabase() throws IOException {
        if (!isExistDatabase()) {
            createDataBase();
        }
        String path = DATABASE_PATH + DATABASE_NAME;
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    }

    public boolean isExistDatabase() {
        String path = DATABASE_PATH + DATABASE_NAME;
        File file = new File(path);
        return file.exists();
    }

    public SQLiteDatabase getWriteable() throws IOException {
        openDatabase();
        return getWritableDatabase();
    }

    private void copyDataBase() throws IOException {
        InputStream inputStream = mContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream outputStream = new FileOutputStream(outFileName);
        byte[] buffer = new byte[5000];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void createDataBase() throws IOException {
        this.getReadableDatabase();
        copyDataBase();
    }

    public void deleteDatabase(){
        File file = new File(DATABASE_PATH, DATABASE_NAME);
        if (file.exists()){
            file.delete();
        }
    }

    public List<TrafficSign> getAllTrafficSigns() {
        List<TrafficSign> signs = new ArrayList<>();
        SQLiteDatabase db = null;
        try {
            db = openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.rawQuery("SELECT anh, noidung, loaibien FROM BIENBAO", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int anh = cursor.getInt(0);
            String noidung = cursor.getString(1);
            int loaibien = cursor.getInt(2);
            signs.add(new TrafficSign(anh, noidung, loaibien));
            cursor.moveToNext();
        }
        cursor.close();
        return signs;
    }
}


