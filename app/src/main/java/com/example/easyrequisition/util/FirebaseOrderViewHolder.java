package com.example.easyrequisition.util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyrequisition.R;
import com.example.easyrequisition.model.Order;

public class FirebaseOrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mView = itemView;
        this.mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindOrders(Order order) {
        TextView customerName = mView.findViewById(R.id.customerName);
        TextView orderContent = mView.findViewById(R.id.orderContent);

        customerName.setText(order.getmCustomerName());
        orderContent.setText(order.getmOrderContent());
    }

    @Override
    public void onClick(View v) {
//        new MaterialAlertDialogBuilder(mContext)
//                .setTitle("Boo")
//                .setMessage("Yaka")
//                .setPositiveButton("Yaaas", null)
//                .show();
    }
}
