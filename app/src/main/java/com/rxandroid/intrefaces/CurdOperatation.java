package com.rxandroid.intrefaces;

import com.rxandroid.models.Employee;

import java.util.List;

/**
 * Created by Devrepublic-14 on 3/13/2018.
 */

public interface CurdOperatation {

    public void addContact(Employee employee);

    public Employee getContact(int id);

    public List<Employee> getAllContacts();

    public int getContactsCount();

    public int updateContact(Employee employee);

    public void deleteContact(Employee employee);

}
