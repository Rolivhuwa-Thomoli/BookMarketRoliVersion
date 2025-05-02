package com.example.bookmarket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        Button btnSeller = findViewById(R.id.btnSeller);
        Button btnBuyer = findViewById(R.id.btnBuyer);

        btnSeller.setOnClickListener(v -> {
            startActivity(new Intent(SelectorActivity.this, MainActivity.class));
            finish();
        });

        btnBuyer.setOnClickListener(v -> {
            startActivity(new Intent(SelectorActivity.this, BuyerActivity.class));
            finish();
        });
    }
}