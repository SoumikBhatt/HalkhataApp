package com.example.halkhataapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.halkhataapp.entity.Customer;
import com.example.halkhataapp.entity.Transaction;
import com.example.halkhataapp.interfaces.CustomerDAO;

@Database(entities = {Customer.class, Transaction.class},version = 1)
public abstract class CustomerDatabase extends RoomDatabase {

    public abstract CustomerDAO customerDAO();
}
