package com.example.bookmarket;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmarket.Data.AppDatabase;
import com.example.bookmarket.Data.DatabaseInstance;
import com.example.bookmarket.Data.TextbookEntity;

import java.util.ArrayList;
import java.util.List;

public class BuyerActivity extends AppCompatActivity {

    private TextbookAdapter adapter;
    private final List<TextbookEntity> filteredList = new ArrayList<>();
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);

        // Initialize database
        db = DatabaseInstance.getInstance(this);

        // Initialize Views
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        EditText searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);

        // Set up RecyclerView with a simplified adapter (no delete functionality)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TextbookAdapter(filteredList, null); // Pass null for deleteListener
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