package com.example.halkhataapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halkhataapp.R;
import com.example.halkhataapp.activity.DetailsActivity;
import com.example.halkhataapp.activity.MainActivity;
import com.example.halkhataapp.dialogbox.UpdateDialog;
import com.example.halkhataapp.entity.Customer;
import com.example.halkhataapp.entity.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerHolder> {

    private Context context;
    private List<Customer> customerList;


    public CustomerAdapter(Context context, List<Customer> customerList) {
        this.context = context;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public CustomerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_custom, null);
        return new CustomerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomerHolder customerHolder, final int i) {

        customerHolder.customerNameTV.setText(customerList.get(i).getName());
        Log.i("NAme", "" + customerList.get(i).getName());

        customerHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("CDetails", customerList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class CustomerHolder extends RecyclerView.ViewHolder {

        private TextView customerNameTV;
        private ImageView detailsIC;

        public CustomerHolder(@NonNull View itemView) {
            super(itemView);

            customerNameTV = itemView.findViewById(R.id.tv_customerName);
            detailsIC = itemView.findViewById(R.id.iv_go);
        }
    }
}
