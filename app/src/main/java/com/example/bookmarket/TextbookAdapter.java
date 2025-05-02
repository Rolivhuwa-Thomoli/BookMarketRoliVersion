package com.example.bookmarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmarket.Data.TextbookEntity;

import java.util.List;

public class TextbookAdapter extends RecyclerView.Adapter<TextbookAdapter.TextbookViewHolder> {

    private final List<TextbookEntity> textbookList;

    public TextbookAdapter(List<TextbookEntity> textbookList) {
        this.textbookList = textbookList;
    }

    @NonNull
    @Override
    public TextbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_textbook, parent, false);
        return new TextbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextbookViewHolder holder, int position) {
        // Bind the data to the ViewHolder
        TextbookEntity textbook = textbookList.get(position);
        holder.titleTextView.setText("Title: " + textbook.title);
        holder.authorTextView.setText("Author: " + textbook.author);
        holder.priceTextView.setText("Price: R" + textbook.price); // Added "R" for currency
        holder.sellerTextView.setText("Seller: " + textbook.seller);
        holder.copiesTextView.setText("Copies: " + textbook.copies);
        holder.bankingInfoTextView.setText("Bank Info: " + textbook.bankInfo);
    }

    @Override
    public int getItemCount() {
        return textbookList.size();
    }

    static class TextbookViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, authorTextView, priceTextView,
                sellerTextView, copiesTextView, bankingInfoTextView;

        public TextbookViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize all TextViews
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            sellerTextView = itemView.findViewById(R.id.sellerTextView);
            copiesTextView = itemView.findViewById(R.id.copiesTextView);
            bankingInfoTextView = itemView.findViewById(R.id.bankingInfoTextView);
        }
    }
}