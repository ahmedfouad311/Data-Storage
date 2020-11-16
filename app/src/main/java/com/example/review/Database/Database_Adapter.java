package com.example.review.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database_Adapter {
    static DatabaseHelper databaseHelper;

    public Database_Adapter(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    // de method 3l4an 22dar 2adef beh name w password entry
    public long addEntry(Name_Pass_DB entry){
        // keda 2na ba-open el data base bt3ti mn 5lal el writableDatabase w mnha 22dar 23ml insert w delete w kol 7aga
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); // dah el row bta3i
        contentValues.put(DatabaseHelper.COL_NAME,entry.getName()); // 2na hna bageb el value el goa el row el fe el col name
        contentValues.put(DatabaseHelper.COL_PASS,entry.getPassword());  // 2na hna bageb el value el goa el row el fe el col name

        long id = db.insert(DatabaseHelper.TABLE_NAME_PASS,null,contentValues); // dah el id bta3 el row el ba3mlo insert delwa2ty
        return id;
    }

    public Name_Pass_DB getEntry(){
        Name_Pass_DB entry = null;
        Cursor cursor;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COL_NAME,DatabaseHelper.COL_PASS};
        cursor = db.query(DatabaseHelper.TABLE_NAME_PASS,columns,null,null,null,null,null);
        while (cursor.moveToNext()){
            entry = new Name_Pass_DB(cursor.getString(0),cursor.getString(1));
        }
        return entry;
    }

    static class DatabaseHelper extends SQLiteOpenHelper{
        // dah el creation bta3 el helper bta3i el bn3ml mn 5lalo create le el database bta3ti
        // w 2na ba3araf el values el db be el 4akl dah 3l4an law 7abet 2a8ayar 7aga tb2a mn hna 3ala toll
        private static final int DATABASE_VERSION = 1; // el version bta3 el database
        private static final String DATABASE_NAME = "NAME_PASSWORD.DB"; // 2sm el database bta3ti
        private static final String TABLE_NAME_PASS = "NAME_PASSWORD"; // 2sm el table bta3i
        private static final String COL_UID = "ID"; // el col el hayt5azen feh el id
        private static final String COL_NAME = "NAME"; // el col el hayt5azen feh el name
        private static final String COL_PASS = "PASS"; // el col el hayt5azen feh el pass

        // hna ba3araf el statement bta3ti (creation statement) de el ba7ot feha el query bta3ti
        private static final String CREATE_NAME_PASSWORD_TABLE = "CREATE TABLE " + TABLE_NAME_PASS +
                " (" + COL_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME +" TEXT, " + COL_PASS + " TEXT );";


        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_NAME_PASSWORD_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            // hna 2na ba3ml drop le el tables el mawgoda 3andi w 23mla create mn tany
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PASS);
            onCreate(db);
        }
    }
}
