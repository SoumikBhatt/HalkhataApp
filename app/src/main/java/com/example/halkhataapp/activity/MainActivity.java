package com.example.halkhataapp.activity;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.halkhataapp.R;
import com.example.halkhataapp.adapter.CustomerAdapter;
import com.example.halkhataapp.database.CustomerDatabase;
import com.example.halkhataapp.dialogbox.AddDialog;
import com.example.halkhataapp.entity.Customer;
import com.example.halkhataapp.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView addIcon, showIcon;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CustomerAdapter customerAdapter;
    private List<Customer> customerList;
    public static CustomerDatabase customerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerDatabase = Room.databaseBuilder(getApplicationContext(), CustomerDatabase.class, "CustomerDB").allowMainThreadQueries().build();



        addIcon = findViewById(R.id.iv_addIcon);
        showIcon = findViewById(R.id.iv_showIcon);
        recyclerView = findViewById(R.id.rv_customersList);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openAddDialog();
            }
        });

        customerList = customerDatabase.customerDAO().viewCustomer();

        customerAdapter = new CustomerAdapter(MainActivity.this, customerList);
        customerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(customerAdapter);

        showIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerList = customerDatabase.customerDAO().viewCustomer();
                customerAdapter = new CustomerAdapter(MainActivity.this, customerList);
                customerAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(customerAdapter);
            }
        });


    }

    public void openAddDialog() {

        AddDialog addDialog = new AddDialog();
        addDialog.show(getSupportFragmentManager(), "add dialog");
    }
}
