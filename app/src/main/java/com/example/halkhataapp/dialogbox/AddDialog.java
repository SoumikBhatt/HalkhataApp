package com.example.halkhataapp.dialogbox;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.halkhataapp.R;
import com.example.halkhataapp.activity.MainActivity;
import com.example.halkhataapp.adapter.CustomerAdapter;
import com.example.halkhataapp.entity.Customer;
import com.example.halkhataapp.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

import static com.example.halkhataapp.activity.MainActivity.customerAdapter;
import static com.example.halkhataapp.activity.MainActivity.customerDatabase;
import static com.example.halkhataapp.activity.MainActivity.customerList;
import static com.example.halkhataapp.activity.MainActivity.recyclerView;

public class AddDialog extends AppCompatDialogFragment {

    private EditText idET, nameET, addressET, numberET;
    private Button addCustomerButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_add, null);
        builder.setView(view).setTitle("");

        nameET = view.findViewById(R.id.et_name);
        addressET = view.findViewById(R.id.et_address);
        numberET = view.findViewById(R.id.et_number);
        addCustomerButton = view.findViewById(R.id.btn_add_customer);

        addCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateAddField();
                customerList = customerDatabase.customerDAO().viewCustomer();
                customerAdapter = new CustomerAdapter(getActivity(), customerList);
                customerAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(customerAdapter);
            }
        });


        return builder.create();
    }

    public void validateAddField() {
        if (TextUtils.isEmpty(nameET.getText()) && TextUtils.isEmpty(addressET.getText()) && TextUtils.isEmpty(numberET.getText())) {
            nameET.setError("Name Field can't be blank");
        } else if (TextUtils.isEmpty(addressET.getText()) && TextUtils.isEmpty(numberET.getText())){
            addressET.setError("Address field can't be blank");
        } else if (TextUtils.isEmpty(nameET.getText())){
            nameET.setError("Name Field can't be blank");
        } else if (TextUtils.isEmpty(addressET.getText())){
            addressET.setError("Address field can't be blank");
        } else if (TextUtils.isEmpty(numberET.getText())){
            numberET.setError("Number Field can't be blank");
        } else {
            addTransaction();
        }
    }

    public void addTransaction() {
        String name = nameET.getText().toString();
        String address = addressET.getText().toString();
        long number = Long.parseLong(numberET.getText().toString());

        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setNumber(number);


        customerDatabase.customerDAO().addCustomer(customer);

        Toast.makeText(getActivity(), "Customer Added Successfully", Toast.LENGTH_SHORT).show();

        nameET.setText("");
        addressET.setText("");
        numberET.setText("");

        dismiss();
    }
}
