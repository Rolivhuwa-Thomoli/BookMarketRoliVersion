package com.example.bookmarket;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AddTextbookActivity extends AppCompatActivity {

    private EditText titleEditText, authorEditText, priceEditText, sellerEditText, copiesEditText, bankingInfoEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_textbook);

        // Initialize Views
        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        priceEditText = findViewById(R.id.priceEditText);
        sellerEditText = findViewById(R.id.sellerEditText);
        copiesEditText = findViewById(R.id.copiesEditText);
        bankingInfoEditText = findViewById(R.id.bankingInfoEditText); // New field for banking information
        submitButton = findViewById(R.id.submitButton);

        // Handle Submit Button Click
        submitButton.setOnClickListener(view -> {
            String title = titleEditText.getText().toString().trim();
            String author = authorEditText.getText().toString().trim();
            String price = priceEditText.getText().toString().trim();
            String sellerName = sellerEditText.getText().toString().trim();
            String copiesString = copiesEditText.getText().toString().trim();
            String bankingInfo = bankingInfoEditText.getText().toString().trim(); // Get banking information

            // Validate Input
            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(author) || TextUtils.isEmpty(price) ||
                    TextUtils.isEmpty(sellerName) || TextUtils.isEmpty(copiesString) || TextUtils.isEmpty(bankingInfo)) {
                Toast.makeText(AddTextbookActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            int numberOfCopies;
            try {
                numberOfCopies = Integer.parseInt(copiesString);
            } catch (NumberFormatException e) {
                Toast.makeText(AddTextbookActivity.this, "Number of copies must be a valid number", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a new Textbook object
            Textbook newTextbook = new Textbook(title, author, price, sellerName, numberOfCopies, bankingInfo);

            // Check for duplicates
            if (MainActivity.textbookList.contains(newTextbook)) {
                Toast.makeText(AddTextbookActivity.this, "This textbook already exists!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Add the textbook to the list
            MainActivity.textbookList.add(newTextbook);

            // Show confirmation dialog
            new AlertDialog.Builder(AddTextbookActivity.this)
                    .setTitle("Success")
                    .setMessage("The textbook has been added successfully!")
                    .setPositiveButton("Go to List", (dialog, which) -> {
                        // Navigate to MainActivity
                        Intent intent = new Intent(AddTextbookActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Close AddTextbookActivity
                    })
                    .setNegativeButton("Add Another Book", (dialog, which) -> {
                        // Clear the input fields for new entry
                        titleEditText.setText("");
                        authorEditText.setText("");
                        priceEditText.setText("");
                        sellerEditText.setText("");
                        copiesEditText.setText("");
                        bankingInfoEditText.setText("");
                    })
                    .setCancelable(false) // Prevent dismissing by tapping outside
                    .show();
        });
    }
}