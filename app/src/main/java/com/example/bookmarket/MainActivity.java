package com.example.bookmarket;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmarket.Data.AppDatabase;
import com.example.bookmarket.Data.DatabaseInstance;
import com.example.bookmarket.Data.TextbookEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextbookAdapter adapter;
    private final List<TextbookEntity> filteredList = new ArrayList<>();
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database
        db = DatabaseInstance.getInstance(this);

        // Initialize Views
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        EditText searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);
        Button addTextbookButton = findViewById(R.id.addTextbookButton);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TextbookAdapter(filteredList);
        recyclerView.setAdapter(adapter);

        // Observe textbooks from database
        db.textbookDao().getAllTextbooksLiveData().observe(this, textbooks -> {
            filteredList.clear();
            if (textbooks != null) {
                filteredList.addAll(textbooks);
            }
            adapter.notifyDataSetChanged();
        });

        // Handle Search Button Click
        searchButton.setOnClickListener(view -> {
            String query = searchEditText.getText().toString().trim();
            if (TextUtils.isEmpty(query)) {
                // Refresh with all books
                db.textbookDao().getAllTextbooksLiveData().observe(this, textbooks -> {
                    filteredList.clear();
                    if (textbooks != null) {
                        filteredList.addAll(textbooks);
                    }
                    adapter.notifyDataSetChanged();
                });
            } else {
                searchBooks(query);
            }
        });

        // Handle "Add Textbook" Button Click
        addTextbookButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddTextbookActivity.class);
            startActivity(intent);
        });
    }

    private void searchBooks(String query) {
        new Thread(() -> {
            List<TextbookEntity> searchResults = db.textbookDao().searchTextbooks("%" + query + "%");
            runOnUiThread(() -> {
                filteredList.clear();
                filteredList.addAll(searchResults);
                adapter.notifyDataSetChanged();
            });
        }).start();
    }
}