package com.example.halkhataapp.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.halkhataapp.entity.Customer;
import com.example.halkhataapp.entity.Transaction;

import java.util.List;

@Dao
public interface CustomerDAO {

    @Insert
    public void addCustomer(Customer customer);

    @Insert
    public void addCustomerTransaction(Transaction transaction);

    @Query("select * from Customer")
    public List<Customer> viewCustomer();


    @Query("select * from `Transaction` where customerID = :id")
    List<Transaction> findDetails(int id);
}
