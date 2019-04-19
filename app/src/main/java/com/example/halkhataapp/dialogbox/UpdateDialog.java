package com.example.halkhataapp.dialogbox;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.halkhataapp.R;
import com.example.halkhataapp.activity.DetailsActivity;
import com.example.halkhataapp.activity.MainActivity;
import com.example.halkhataapp.adapter.HistoryAdapter;
import com.example.halkhataapp.entity.Transaction;

import java.util.GregorianCalendar;

import static com.example.halkhataapp.activity.DetailsActivity.balance;
import static com.example.halkhataapp.activity.DetailsActivity.balanceTV;
import static com.example.halkhataapp.activity.DetailsActivity.customerHistoryRecycler;
import static com.example.halkhataapp.activity.DetailsActivity.getBalance;
import static com.example.halkhataapp.activity.DetailsActivity.historyAdapter;
import static com.example.halkhataapp.activity.DetailsActivity.objCustomer;
import static com.example.halkhataapp.activity.DetailsActivity.totalDeposit;
import static com.example.halkhataapp.activity.DetailsActivity.transactions;

public class UpdateDialog extends AppCompatDialogFragment {

    private EditText upID, upDue, upDate;
    private Button upButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_update, null);
        builder.setView(view).setTitle("");

        upDue = view.findViewById(R.id.et_up_due_amount);
        upDate = view.findViewById(R.id.et_up_date);
        upButton = view.findViewById(R.id.btn_up_add_customer);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateDueFields();

                transactions = MainActivity.customerDatabase.customerDAO().findDetails(objCustomer.getId());
                historyAdapter = new HistoryAdapter(getActivity(), transactions);
                historyAdapter.notifyDataSetChanged();
                customerHistoryRecycler.setAdapter(historyAdapter);
                getBalance();
            }
        });




        return builder.create();
    }

    public void validateDueFields() {
        if (TextUtils.isEmpty(upDue.getText())|| TextUtils.isEmpty(upDate.getText())){
            Toast.makeText(getContext(),"Please Fill up all Field",Toast.LENGTH_SHORT).show();
        } else {


            dueTransaction();
        }
    }

    public void dueTransaction() {
        int due =  Integer.parseInt(upDue.getText().toString()) ;
        String date = upDate.getText().toString();


        int cid = objCustomer.getId();

        Transaction transaction = new Transaction();
        transaction.setDue(due);
        transaction.setDate(date);

        transaction.setCustomerID(cid);

        Log.i("objid", "" + objCustomer.getId());
        Log.i("objid", "" + cid);
        Log.i("tid", "" + transaction.getCustomerID());
        Log.i("due", "" + transaction.getDue());
        Log.i("DateDue", "" + transaction.getDate());

        MainActivity.customerDatabase.customerDAO().addCustomerTransaction(transaction);

        Toast.makeText(getActivity(), "Customer Transaction Added", Toast.LENGTH_SHORT).show();

        upDue.setText("");
        upDate.setText("");

        dismiss();
    }


}
