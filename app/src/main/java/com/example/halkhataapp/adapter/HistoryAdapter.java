package com.example.halkhataapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.halkhataapp.R;
import com.example.halkhataapp.activity.DetailsActivity;
import com.example.halkhataapp.entity.Transaction;
import java.util.List;

import static com.example.halkhataapp.activity.DetailsActivity.objCustomer;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

    private Context context;
    private List<Transaction> transactionList;

    public HistoryAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_history, null);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder historyHolder, int i) {

        if (transactionList.get(i).getCustomerID() == objCustomer.getId()) {

            Log.i("CID", "" + objCustomer.getId());
            Log.i("CIDT", "" + transactionList.get(i).getCustomerID());

            historyHolder.dueHistoryTV.setText("Due: " + transactionList.get(i).getDue());
            historyHolder.depositHistoryTV.setText("Deposit: " + transactionList.get(i).getDeposit());
            historyHolder.dateTV.setText("Date: " + transactionList.get(i).getDate());


            Log.i("Due ", "" + transactionList.get(i).getDue());
            Log.i("Deposit ", "" + transactionList.get(i).getDeposit());

        }
    }

    @Override
    public int getItemCount() {
        int size = transactionList.size();
        Log.i("SSDD", "" + size);
        return size;
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {

        TextView dueHistoryTV, depositHistoryTV, dateTV;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);

            dueHistoryTV = itemView.findViewById(R.id.tv_history_due);
            depositHistoryTV = itemView.findViewById(R.id.tv_history_deposit);
            dateTV = itemView.findViewById(R.id.tv_date);
        }
    }
}
