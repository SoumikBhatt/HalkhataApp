package com.example.halkhataapp.dialogbox;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.halkhataapp.R;
import com.example.halkhataapp.activity.DetailsActivity;
import com.example.halkhataapp.activity.MainActivity;
import com.example.halkhataapp.adapter.HistoryAdapter;
import com.example.halkhataapp.entity.Transaction;

import java.util.ArrayList;
import java.util.List;

import static com.example.halkhataapp.activity.DetailsActivity.balance;
import static com.example.halkhataapp.activity.DetailsActivity.balanceTV;
import static com.example.halkhataapp.activity.DetailsActivity.customerHistoryRecycler;
import static com.example.halkhataapp.activity.DetailsActivity.objCustomer;

public class DepositDialog extends AppCompatDialogFragment {

    private EditText idET, depoET, dateET;
    private Button addBTN;
    private List<Transaction> transactions = new ArrayList<>();
    private HistoryAdapter historyAdapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_deposit, null);
        builder.setView(view).setTitle("");

//        idET = view.findViewById(R.id.et_up_depoID);
        depoET = view.findViewById(R.id.et_up_deposit_amount);
        dateET = view.findViewById(R.id.et_deposit_date);
        addBTN = view.findViewById(R.id.btn_up_depo_add_customer);

        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(depoET.getText()) || TextUtils.isEmpty(dateET.getText())) {
                    Toast.makeText(getContext(), "Please Fill up all Field", Toast.LENGTH_SHORT).show();
                } else {

//                int id = Integer.parseInt(idET.getText().toString());
                    int deposit = Integer.parseInt(depoET.getText().toString());
                    String date = dateET.getText().toString();

                    int cid = objCustomer.getId();

                    Transaction transaction = new Transaction();
//                transaction.setTransactionID(id);
                    transaction.setDeposit(deposit);
                    transaction.setDate(date);

                    transaction.setCustomerID(cid);

                    Log.i("objid", "" + objCustomer.getId());
                    Log.i("objid", "" + cid);
                    Log.i("tid", "" + transaction.getCustomerID());
                    Log.i("depo", "" + transaction.getDeposit());
                    Log.i("date", "" + transaction.getDate());

                    MainActivity.customerDatabase.customerDAO().addCustomerTransaction(transaction);

                    Toast.makeText(getActivity(), "Customer Transaction Added", Toast.LENGTH_SHORT).show();

//                idET.setText("");
                    depoET.setText("");
                    dateET.setText("");


                    dismiss();
                }
            }
        });

        return builder.create();
    }
}
