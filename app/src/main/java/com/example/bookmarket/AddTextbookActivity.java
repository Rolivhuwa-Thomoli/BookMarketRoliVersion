package com.example.bookmarket;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTextbookActivity extends AppCompatActivity {

    private EditText titleEditText, authorEditText, priceEditText, sellerEditText, copiesEditText;
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
        submitButton = findViewById(R.id.submitButton);

        // Handle Submit Button Click
        submitButton.setOnClickListener(view -> {
            String title = titleEditText.getText().toString().trim();
            String author = authorEditText.getText().toString().trim();
            String price = priceEditText.getText().toString().trim();
            String sellerName = sellerEditText.getText().toString().trim();
            String copiesString = copiesEditText.getText().toString().trim();

            // Validate Input
            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(author) || TextUtils.isEmpty(price) || TextUtils.isEmpty(sellerName) || TextUtils.isEmpty(copiesString)) {
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
            Textbook newTextbook = new Textbook(title, author, price, sellerName, numberOfCopies);

            // Check for duplicates
            if (MainActivity.textbookList.contains(newTextbook)) {
                Toast.makeText(AddTextbookActivity.this, "This textbook already exists!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Add the textbook to the list
            MainActivity.textbookList.add(newTextbook);
            Toast.makeText(AddTextbookActivity.this, "Textbook added successfully!", Toast.LENGTH_SHORT).show();

            // Clear the input fields
            titleEditText.setText("");
            authorEditText.setText("");
            priceEditText.setText("");
            sellerEditText.setText("");
            copiesEditText.setText("");
        });
    }
}