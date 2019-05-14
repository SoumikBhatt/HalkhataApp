package com.example.halkhataapp.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.halkhataapp.entity.Customer;
import com.example.halkhataapp.entity.Transaction;

import java.util.List;

@Dao
public interface CustomerDAO {

    @Insert
    void addCustomer(Customer customer);

    @Insert
    void addCustomerTransaction(Transaction transaction);

    @Query("select * from Customer")
    List<Customer> viewCustomer();


    @Query("select * from `Transaction` where customerID = :id")
    List<Transaction> findDetails(int id);

    @Delete
    void deleteCustomer(Customer customer);

    @Delete
    void deleteCustomerTransaction(Transaction transaction);
}
