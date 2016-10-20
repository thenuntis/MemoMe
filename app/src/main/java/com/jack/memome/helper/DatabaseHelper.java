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
    static final String WORDS_TABLE = "foreignwords";
    static final String WORD_COLUMN = "word";
    static final String TRANSLATE_WORD_COLUMN = "translate";
    static final String COMMENT_WORD_COLUMN = "comment";
    static final String ID_COLUMN = "_id";
    static final String DATE_COLUMN = "edit_date";
    static final String TEST_MARK_COLUMN = "useintest";

    private String originalWordSql = "create table " + WORDS_TABLE + "("
            + ID_COLUMN + " integer primary key autoincrement, "
            + WORD_COLUMN + " text not null, "
            + TRANSLATE_WORD_COLUMN + " text not null, "
            + COMMENT_WORD_COLUMN + " text not null, "
            + TEST_MARK_COLUMN + " boolean, "
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public Cursor createCursor(String query) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(query, null);
    }
}
