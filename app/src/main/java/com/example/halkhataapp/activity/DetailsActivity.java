package com.example.halkhataapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halkhataapp.R;
import com.example.halkhataapp.adapter.HistoryAdapter;
import com.example.halkhataapp.dialogbox.DepositDialog;
import com.example.halkhataapp.dialogbox.UpdateDialog;
import com.example.halkhataapp.entity.Customer;
import com.example.halkhataapp.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    public static TextView detailsName,balanceTV;
    private ImageView backImage,refresh;
    private Button dueButton,depositButton;

    public static RecyclerView customerHistoryRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private HistoryAdapter historyAdapter;
    private List<Transaction> transactions = new ArrayList<>();
    public static Customer objCustomer;

    public static int balance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailsName = findViewById(R.id.tv_detailsName);
        balanceTV = findViewById(R.id.tv_balance);
        backImage = findViewById(R.id.iv_back);
        refresh = findViewById(R.id.iv_refresh);
        dueButton = findViewById(R.id.btn_due);
        depositButton = findViewById(R.id.btn_deposit);


        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsActivity.super.onBackPressed();
            }
        });


        customerHistoryRecycler = findViewById(R.id.rv_customer_history);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        customerHistoryRecycler.setLayoutManager(layoutManager);
        customerHistoryRecycler.setHasFixedSize(true);

        objCustomer = (Customer) getIntent().getSerializableExtra("CDetails");


        detailsName.setText("Name: "+objCustomer.getName());


        Log.i("Transactions", "" + transactions);

        dueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDueTransaction();
            }
        });

        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDepositTransaction();
            }
        });


        transactions = MainActivity.customerDatabase.customerDAO().findDetails(objCustomer.getId());
        historyAdapter = new HistoryAdapter(DetailsActivity.this, transactions);
        historyAdapter.notifyDataSetChanged();
        customerHistoryRecycler.setAdapter(historyAdapter);

        Log.i("RV", "RV implemented");

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transactions = MainActivity.customerDatabase.customerDAO().findDetails(objCustomer.getId());
                historyAdapter = new HistoryAdapter(DetailsActivity.this, transactions);
                historyAdapter.notifyDataSetChanged();
                customerHistoryRecycler.setAdapter(historyAdapter);
                getBalance();
            }
        });

        getBalance();
    }

    public void getBalance() {
        int totalDeposit = 0;

        for (int i=0;i<transactions.size();i++){

            totalDeposit += transactions.get(i).getDeposit();
        }
        Log.d("Total Deposit ",""+totalDeposit);

        int totalDue = 0;

        for (int i=0;i<transactions.size();i++){

            totalDue += transactions.get(i).getDue();
        }

        Log.d("Total Due ",""+totalDue);

        balance = totalDeposit - totalDue;

        balanceTV.setText("Balance: "+balance);
    }

    public void openDueTransaction(){

        UpdateDialog updateDialog = new UpdateDialog();
        updateDialog.show(getSupportFragmentManager(),"update dialog");
    }

    public void openDepositTransaction(){

        DepositDialog depositDialog = new DepositDialog();
        depositDialog.show(getSupportFragmentManager(),"deposit dialog");
    }
}
