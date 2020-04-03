package com.example.easyrequisition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.newOrderFab)
    FloatingActionButton mNewOrderFab;
    @BindView(R.id.orderRecyclerView)
    RecyclerView mOrderRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
}
