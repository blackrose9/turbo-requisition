package com.example.easyrequisition.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easyrequisition.R;
import com.example.easyrequisition.model.Order;
import com.example.easyrequisition.util.FirebaseOrderViewHolder;
import com.example.easyrequisition.util.ItemTouchHelperAdapter;
import com.example.easyrequisition.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseOrderListAdapter extends FirebaseRecyclerAdapter<Order, FirebaseOrderViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference getReference;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private OnStartDragListener mStartDragListener;

    private ArrayList<Order> mOrders = new ArrayList<>();

    public FirebaseOrderListAdapter(@NonNull FirebaseRecyclerOptions<Order> options,
                                    Query reference,
                                    OnStartDragListener onStartDragListener,
                                    Context context) {
        super(options);
        getReference = reference.getRef();
        mStartDragListener = onStartDragListener;
        this.mContext = context;

        mChildEventListener = getReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mOrders.add(dataSnapshot.getValue(Order.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebaseOrderViewHolder firebaseOrderViewHolder, int position, @NonNull Order order) {
        firebaseOrderViewHolder.bindOrders(order);
        firebaseOrderViewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mStartDragListener.onStartDrag(firebaseOrderViewHolder);
                }
                return false;
            }
        });
    }

    @NonNull
    @Override
    public FirebaseOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item, parent, false);
        return new FirebaseOrderViewHolder(view);
    }

    @Override
    public void onItemDismiss(int position) {
        mOrders.remove(position);
        getRef(position).removeValue();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mOrders, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        setIndexInFirebase();
        return false;
    }

    private void setIndexInFirebase() {
        for (Order order : mOrders) {
            int index = mOrders.indexOf(order);
            DatabaseReference reference = getRef(index);
            order.setIndex(Integer.toString(index));
            reference.setValue(order);
        }
    }

    @Override
    public void stopListening() {
        getReference.removeEventListener(mChildEventListener);
    }
}
