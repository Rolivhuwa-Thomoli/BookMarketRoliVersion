package com.example.bookmarket;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmarket.Data.AppDatabase;
import com.example.bookmarket.Data.DatabaseInstance;
import com.example.bookmarket.Data.TextbookEntity;

public class AddTextbookActivity extends AppCompatActivity {

    private EditText titleEditText, authorEditText, priceEditText, sellerEditText, copiesEditText, bankingInfoEditText;
    private Button submitButton;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_textbook);

        // Initialize database
        db = DatabaseInstance.getInstance(this);

        // Initialize Views
        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        priceEditText = findViewById(R.id.priceEditText);
        sellerEditText = findViewById(R.id.sellerEditText);
        copiesEditText = findViewById(R.id.copiesEditText);
        bankingInfoEditText = findViewById(R.id.bankingInfoEditText);
        submitButton = findViewById(R.id.submitButton);

        // Handle Submit Button Click
        submitButton.setOnClickListener(view -> {
            String title = titleEditText.getText().toString().trim();
            String author = authorEditText.getText().toString().trim();
            String priceStr = priceEditText.getText().toString().trim();
            String sellerName = sellerEditText.getText().toString().trim();
            String copiesString = copiesEditText.getText().toString().trim();
            String bankingInfo = bankingInfoEditText.getText().toString().trim();

            // Validate Input
            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(author) || TextUtils.isEmpty(priceStr) ||
                    TextUtils.isEmpty(sellerName) || TextUtils.isEmpty(copiesString) || TextUtils.isEmpty(bankingInfo)) {
                Toast.makeText(AddTextbookActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            double price;
            int numberOfCopies;
            try {
                price = Double.parseDouble(priceStr);
                numberOfCopies = Integer.parseInt(copiesString);
            } catch (NumberFormatException e) {
                Toast.makeText(AddTextbookActivity.this, "Price and copies must be valid numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a new TextbookEntity object
            TextbookEntity newTextbook = new TextbookEntity();
            newTextbook.title = title;
            newTextbook.author = author;
            newTextbook.price = price;
            newTextbook.seller = sellerName;
            newTextbook.copies = numberOfCopies;
            newTextbook.bankInfo = bankingInfo;

            // Insert into database using a background thread
            new Thread(() -> {
                try {//Exception handling

                    // Insert the textbook
                    db.textbookDao().insert(newTextbook);

                    // Show success on UI thread
                    runOnUiThread(() -> {
                        new AlertDialog.Builder(AddTextbookActivity.this)
                                .setTitle("Success")
                                .setMessage("The textbook has been added successfully!")
                                .setPositiveButton("Go to List", (dialog, which) -> {
                                    Intent intent = new Intent(AddTextbookActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                })
                                .setNegativeButton("Add Another Book", (dialog, which) -> {
                                    titleEditText.setText("");
                                    authorEditText.setText("");
                                    priceEditText.setText("");
                                    sellerEditText.setText("");
                                    copiesEditText.setText("");
                                    bankingInfoEditText.setText("");
                                })
                                .setCancelable(false)
                                .show();
                    });
                } catch (Exception e) {
                    runOnUiThread(() ->
                            Toast.makeText(AddTextbookActivity.this, "Error saving textbook: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                }
            }).start();
        });
    }
}