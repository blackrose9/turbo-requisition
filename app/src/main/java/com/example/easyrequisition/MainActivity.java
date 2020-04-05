package com.example.easyrequisition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyrequisition.adapter.FirebaseOrderListAdapter;
import com.example.easyrequisition.model.Order;
import com.example.easyrequisition.util.ItemTouchHelperCallback;
import com.example.easyrequisition.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnStartDragListener {
    @BindView(R.id.newOrderFab)
    FloatingActionButton mNewOrderFab;
    @BindView(R.id.orderRecyclerView)
    RecyclerView mOrderRecyclerView;

    private FirebaseOrderListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();

        mNewOrderFab.bringToFront();
        mNewOrderFab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mNewOrderFab) {
            Intent intent = new Intent(MainActivity.this, PlaceOrderActivity.class);
            startActivity(intent);
        }
    }

    private void setUpFirebaseAdapter() {
        Query query = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Orders")
                .orderByChild("index");

        FirebaseRecyclerOptions<Order> options = new FirebaseRecyclerOptions.Builder<Order>()
                .setQuery(query, Order.class)
                .build();

        mFirebaseAdapter = new FirebaseOrderListAdapter(options, query, this, this);

        mOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOrderRecyclerView.setAdapter(mFirebaseAdapter);
        mOrderRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mOrderRecyclerView);
        mFirebaseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFirebaseAdapter != null) {
            mFirebaseAdapter.stopListening();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.stopListening();
    }
}
