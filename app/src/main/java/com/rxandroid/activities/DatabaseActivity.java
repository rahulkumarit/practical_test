package com.rxandroid.activities;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rxandroid.R;
import com.rxandroid.db.DatabaseHandler;
import com.rxandroid.models.Employee;

/**
 * Created by Devrepublic-14 on 3/13/2018.
 */

public class DatabaseActivity extends BaseActivity implements View.OnClickListener {

    private EditText edtName, edtAge, edtAddress, edtSalary, edtDept;
    private Button btnSave, btnRetrive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        //edtName, edtAge, edtAddress, edtSalary, edtDept;
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtAddress = findViewById(R.id.edtAddress);
        edtSalary = findViewById(R.id.edtSalary);
        edtDept = findViewById(R.id.edtDept);
        //btnSave,btnRetrive
        btnSave = findViewById(R.id.btnSave);
        btnRetrive = findViewById(R.id.btnRetrive);
        btnSave.setOnClickListener(this);
        btnRetrive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRetrive:
                    retriveDatabase();
                break;
            case R.id.btnSave:
                saveinDB();
                break;
            default:
                break;
        }
    }

    private void saveinDB() {
        //save data here
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        Employee employee = new Employee();
        employee.EMP_ID = (int) SystemClock.currentThreadTimeMillis();
        employee.NAME = edtName.getText().toString().trim();
        employee.AGE = edtAge.getText().toString().trim();
        employee.ADDRESS = edtAddress.getText().toString().trim();
        employee.DEPT = edtDept.getText().toString().trim();
        employee.SALARY = edtSalary.getText().toString().trim();
        databaseHandler.addContact(employee);
    }

    private void retriveDatabase() {
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        databaseHandler.getAllContacts();
      }

}
