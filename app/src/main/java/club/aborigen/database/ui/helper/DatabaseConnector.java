package club.aborigen.database.ui.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseConnector {

    private static final String DATABASE_NAME = "helper.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "employees";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SALARY = "salary";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_NAME = 1;
    private static final int NUM_COLUMN_SALARY = 2;

    private SQLiteDatabase database;

    public DatabaseConnector(Context context) {
        OpenHelper openHelper = new OpenHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public long insert(String name, Integer salary) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_SALARY, salary);
        return database.insert(TABLE_NAME, null, cv);
    }

    public void deleteAll() {
        database.delete(TABLE_NAME, null, null);
    }

    public Employee select(long id) {
        Cursor mCursor = database.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        mCursor.moveToFirst();
        String name = mCursor.getString(NUM_COLUMN_NAME);
        Integer salary = mCursor.getInt(NUM_COLUMN_SALARY);
        return new Employee(id, name, salary);
    }

    public ArrayList<Employee> selectAll() {
        Cursor mCursor = database.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Employee> arr = new ArrayList<Employee>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                String name = mCursor.getString(NUM_COLUMN_NAME);
                Integer salary = mCursor.getInt(NUM_COLUMN_SALARY);
                arr.add(new Employee(id, name, salary));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME+ " TEXT, " +
                    COLUMN_SALARY + " INTEGER)";

            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}