package com.jack.memome.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created on 07.10.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "memomedb.db";
    private static final int SCHEMA = 1;
    private static DatabaseHelper sDatabaseHelper;
    static final String ORIGINAL_WORDS_TABLE = "originalword";
    static final String TRANSLATED_WORDS_TABLE = "translatedword";
    static final String COMMENT_WORDS_TABLE = "commentword";
    static final String WORD_COLUMN = "word";
    static final String ID_COLUMN = "_id";
    static final String ORIGINAL_ID_COLUMN = "original_id";
    static final String DATE_COLUMN = "edit_date";
    static final String TEST_MARK_COLUMN = "useintest";
    private String originalWordSql = "create table " + ORIGINAL_WORDS_TABLE + "("
            + ID_COLUMN + " integer primary key autoincrement, "
            + WORD_COLUMN + " text not null, "
            + TEST_MARK_COLUMN + " boolean, "
            + DATE_COLUMN + " datetime" + ")";
    private String translateWordSql = "create table " + TRANSLATED_WORDS_TABLE + "("
            + ID_COLUMN + " integer primary key autoincrement, "
            + ORIGINAL_ID_COLUMN + " integer not null "
            + "REFERENCES " + ORIGINAL_WORDS_TABLE + " (" + ID_COLUMN + "), "
            + WORD_COLUMN + " text not null, "
            + DATE_COLUMN + " datetime" + ")";
    private String commentWordSql = "create table " + COMMENT_WORDS_TABLE + "("
            + ID_COLUMN + " integer primary key autoincrement, "
            + ORIGINAL_ID_COLUMN + " integer not null "
            + "REFERENCES " + ORIGINAL_WORDS_TABLE + " (" + ID_COLUMN + "), "
            + WORD_COLUMN + " text not null, "
            + DATE_COLUMN + " datetime" + ")";

    private DatabaseHelper (Context context) {
       super(context,DATABASE_NAME,null,SCHEMA);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (null == sDatabaseHelper) {
            sDatabaseHelper = new DatabaseHelper(context);
        }
        return sDatabaseHelper;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly())
            db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(originalWordSql);
        db.execSQL(translateWordSql);
        db.execSQL(commentWordSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public Cursor createCursor(String query) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(query, null);
    }
}
