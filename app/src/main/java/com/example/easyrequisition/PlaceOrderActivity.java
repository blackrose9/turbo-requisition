package com.example.easyrequisition;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceOrderActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.addCustomerName)
    EditText mAddCustomerName;
    @BindView(R.id.addOrderContent)
    EditText mAddOrderContent;
    @BindView(R.id.placeOrderBtn)
    Button mPlaceOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mPlaceOrderBtn) {
            Toast.makeText(PlaceOrderActivity.this, "Submitted", Toast.LENGTH_LONG).show();
        }
    }

    private void saveOrderToFirebase(String customerName, String orderContent) {
//        mEntryReference.push().setValue(new Order(title, entry));
    }
}
