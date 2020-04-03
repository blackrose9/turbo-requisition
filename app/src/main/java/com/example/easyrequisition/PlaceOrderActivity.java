package com.example.easyrequisition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.easyrequisition.model.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceOrderActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.addCustomerName)
    EditText mAddCustomerName;
    @BindView(R.id.addOrderContent)
    EditText mAddOrderContent;
    @BindView(R.id.placeOrderBtn)
    Button mPlaceOrderBtn;
    //    Bind add_item_layout
    @BindView(R.id.itemName)
    EditText mItemName;
    @BindView(R.id.itemQty)
    EditText mItemQty;
    @BindView(R.id.addItemBtn)
    ImageButton mAddItemBtn;

    private DatabaseReference mOrderReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mOrderReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Orders");

        mPlaceOrderBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mPlaceOrderBtn) {
            String customerName = mAddCustomerName.getText().toString();
            String orderContent = mAddOrderContent.getText().toString();

            saveOrderToFirebase(customerName, orderContent);

            Toast.makeText(PlaceOrderActivity.this, "Submitted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(PlaceOrderActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void saveOrderToFirebase(String customerName, String orderContent) {
        mOrderReference.push().setValue(new Order(customerName, orderContent));
    }
}
