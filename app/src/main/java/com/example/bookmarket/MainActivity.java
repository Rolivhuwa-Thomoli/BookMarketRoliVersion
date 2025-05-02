package com.example.bookmarket;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Textbook> textbookList = new ArrayList<>();
    private TextbookAdapter adapter;
    private List<Textbook> filteredList = new ArrayList<>(); // To store search results

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        EditText searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);
        Button addTextbookButton = findViewById(R.id.addTextbookButton); // Initialize "List Button"

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TextbookAdapter(filteredList);
        recyclerView.setAdapter(adapter);

        // Load sample data
        loadSampleData();
        filteredList.addAll(textbookList); // Show all books initially
        adapter.notifyDataSetChanged();

        // Handle Search Button Click
        searchButton.setOnClickListener(view -> {
            String query = searchEditText.getText().toString().trim();
            if (TextUtils.isEmpty(query)) {
                filteredList.clear();
                filteredList.addAll(textbookList); // Show all books if no search query
            } else {
                searchBooks(query);
            }
            adapter.notifyDataSetChanged();
        });

        // Handle "List Button" Click
        addTextbookButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddTextbookActivity.class);
            startActivity(intent);
        });
    }

    private void searchBooks(String query) {
        filteredList.clear();
        for (Textbook textbook : textbookList) {
            if (textbook.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    textbook.getSellerName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(textbook);
            }
        }
    }

    private void loadSampleData() {
        textbookList.add(new Textbook("Introduction to Algorithms", "Thomas H. Cormen", "R50", "Alice", 5, "123456789"));
        textbookList.add(new Textbook("Clean Code", "Robert C. Martin", "R40", "Bob", 3, "987654321"));
        textbookList.add(new Textbook("Artificial Intelligence: A Modern Approach", "Stuart Russell", "R60", "Charlie", 2, "456789123"));
    }
}