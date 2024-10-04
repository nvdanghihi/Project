package com.example.json;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "khohang.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "khohang";
    public static final String COLUMN_ID = "Mahang";
    public static final String COLUMN_NAME = "Tenhang";
    public static final String COLUMN_CATEGORY = "Chungloai";
    public static final String COLUMN_QUANTITY = "Soluong";
    public static final String COLUMN_ORIGIN = "Xuatxu";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_CATEGORY + " TEXT, " +
                    COLUMN_QUANTITY + " INTEGER, " +
                    COLUMN_ORIGIN + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
