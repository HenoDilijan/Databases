package club.aborigen.database.ui.helper;

import static club.aborigen.database.ui.helper.OpenHelper.COLUMN_ID;
import static club.aborigen.database.ui.helper.OpenHelper.COLUMN_NAME;
import static club.aborigen.database.ui.helper.OpenHelper.COLUMN_SALARY;
import static club.aborigen.database.ui.helper.OpenHelper.NUM_COLUMN_ID;
import static club.aborigen.database.ui.helper.OpenHelper.NUM_COLUMN_NAME;
import static club.aborigen.database.ui.helper.OpenHelper.NUM_COLUMN_SALARY;
import static club.aborigen.database.ui.helper.OpenHelper.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseConnector {

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

}