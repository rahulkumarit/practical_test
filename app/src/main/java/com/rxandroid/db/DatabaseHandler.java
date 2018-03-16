package com.rxandroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.rxandroid.intrefaces.CurdOperatation;
import com.rxandroid.models.Employee;
import java.util.List;

/**
 * Created by Devrepublic-14 on 3/13/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper implements CurdOperatation {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "company_manage";
    // Contacts table name
    private static final String TABLE_COMPANY = "COMPANY";
    private static final String TABLE_DEPARTMENT = "DEPARTMENT";
    // Contacts Table Columns names
    private static final String KEY_EMP_ID = "emp_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_SALARY = "salary";
    private static final String KEY_DEP_ID = "dep_id";
    private static final String KEY_DEPT = "dept";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE_1 =
                "CREATE TABLE " + TABLE_COMPANY + "("
                        + KEY_EMP_ID + " INTEGER PRIMARY KEY,"
                        + KEY_NAME + " TEXT,"
                        + KEY_AGE + " TEXT,"
                        + KEY_ADDRESS + " TEXT,"
                        + KEY_SALARY + " INTEGER"
                        + ")";
        String CREATE_CONTACTS_TABLE_2 = "CREATE TABLE " + TABLE_DEPARTMENT + "("
                + KEY_DEP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + KEY_DEPT + " TEXT,"
                + KEY_EMP_ID + " INTEGER"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE_1);
        db.execSQL(CREATE_CONTACTS_TABLE_2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
        // Create tables again
        onCreate(db);
    }


    @Override
    public void addContact(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMP_ID, employee.EMP_ID);
        values.put(KEY_NAME, employee.NAME);
        values.put(KEY_AGE, employee.AGE);
        values.put(KEY_ADDRESS, employee.ADDRESS);
        values.put(KEY_SALARY, employee.SALARY);
        db.insert(TABLE_COMPANY, null, values);

        //Added in scond table
        ContentValues valuesDep = new ContentValues();
        valuesDep.put(KEY_DEPT, employee.DEPT);
        valuesDep.put(KEY_EMP_ID, employee.EMP_ID);
        // Inserting Row in department
        db.insert(TABLE_DEPARTMENT, null, valuesDep);
        db.close();

    }

    @Override
    public Employee getContact(int id) {
        return null;
    }

    @Override
    public List<Employee> getAllContacts() {

          /*String selectQuery = "SELECT "
                + KEY_EMP_ID + ","
                + KEY_NAME + ","
                + KEY_DEPT + " FROM "
                + TABLE_COMPANY + " INNER JOIN "
                + TABLE_DEPARTMENT + " ON "
                + KEY_EMP_ID + " = "
                + KEY_DEP_ID;
          */

        String testQuary = "SELECT *FROM " + TABLE_COMPANY;

        String MY_QUERY = "SELECT name,dept FROM "
                    + TABLE_COMPANY + " C" +","
                + TABLE_DEPARTMENT + " D "
                + "WHERE C." + KEY_EMP_ID +" = " + "D."+KEY_EMP_ID;

        Log.e("query", MY_QUERY);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(MY_QUERY, null);
        cursor.moveToFirst();
        Log.e("cursor (employee)", "" + cursor.getCount());
        return null;


            }


    @Override
    public int getContactsCount() {
        return 0;
    }

    @Override
    public int updateContact(Employee employee) {
        return 0;
    }

    @Override
    public void deleteContact(Employee employee) {

    }


}
