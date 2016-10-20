package com.jack.memome.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.jack.memome.models.WordItem;

import java.util.Date;
import java.util.List;

/**
 * Created on 19.10.2016.
 */
public class WordItemDatabaseHelper {
    private DatabaseHelper db;


    public WordItemDatabaseHelper(Context context) {
            this.db = DatabaseHelper.getInstance(context);
    }

    // getting words from database
    public List<WordItem> getWords (long wordId){
        return null;
    }

    public List<WordItem> getTestWords () {
        return getWords(-1);
    }

    public void addWordIntoDB (WordItem item) {
        SQLiteDatabase helper = db.getReadableDatabase();
        helper.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.ID_COLUMN,item.getWordId());
        values.put(DatabaseHelper.WORD_COLUMN,item.getWord());
        values.put(DatabaseHelper.DATE_COLUMN,new Date().getTime());
        int rows = helper.update(DatabaseHelper.ORIGINAL_WORDS_TABLE, values,
                DatabaseHelper.ID_COLUMN + "= ?", new String[]{String.valueOf(item.getWordId())});
        if(rows !=1) {

        }

    }

    public void delWordFromDB (WordItem item) {

    }

    public void clearAllTestWords () {

    }

    public void addWordToTest () {

    }

    public  void removeWordFromTest () {

    }
}
