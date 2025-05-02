package com.example.bookmarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmarket.Data.TextbookEntity;

import java.util.List;

public class TextbookAdapter extends RecyclerView.Adapter<TextbookAdapter.TextbookViewHolder> {

    private final List<TextbookEntity> textbookList;
    private final OnDeleteClickListener deleteListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(TextbookEntity textbook);
    }

    public TextbookAdapter(List<TextbookEntity> textbookList, OnDeleteClickListener deleteListener) {
        this.textbookList = textbookList;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public TextbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_textbook, parent, false);
        return new TextbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextbookViewHolder holder, int position) {
        TextbookEntity textbook = textbookList.get(position);
        holder.titleTextView.setText("Title: " + textbook.title);
        holder.authorTextView.setText("Author: " + textbook.author);
        holder.priceTextView.setText("Price: R" + textbook.price);
        holder.sellerTextView.setText("Seller: " + textbook.seller);
        holder.copiesTextView.setText("Copies: " + textbook.copies);
        holder.bankingInfoTextView.setText("Bank Info: " + textbook.bankInfo);

        holder.deleteButton.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDeleteClick(textbook);
            }
        });
    }

    @Override
    public int getItemCount() {
        return textbookList.size();
    }

    static class TextbookViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, authorTextView, priceTextView,
                sellerTextView, copiesTextView, bankingInfoTextView;
        Button deleteButton;

        public TextbookViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            sellerTextView = itemView.findViewById(R.id.sellerTextView);
            copiesTextView = itemView.findViewById(R.id.copiesTextView);
            bankingInfoTextView = itemView.findViewById(R.id.bankingInfoTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}