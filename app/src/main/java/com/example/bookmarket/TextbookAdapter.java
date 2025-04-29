package com.example.bookmarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TextbookAdapter extends RecyclerView.Adapter<TextbookAdapter.TextbookViewHolder> {

    private final List<Textbook> textbookList;

    public TextbookAdapter(List<Textbook> textbookList) {
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
        Textbook textbook = textbookList.get(position);
        holder.titleTextView.setText(textbook.getTitle());
        holder.authorTextView.setText("Author: " + textbook.getAuthor());
        holder.priceTextView.setText("Price: " + textbook.getPrice());
        holder.sellerTextView.setText("Seller: " + textbook.getSellerName());
        holder.copiesTextView.setText("Copies: " + textbook.getNumberOfCopies());
    }

    @Override
    public int getItemCount() {
        return textbookList.size();
    }

    static class TextbookViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, authorTextView, priceTextView, sellerTextView, copiesTextView;

        public TextbookViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            sellerTextView = itemView.findViewById(R.id.sellerTextView);
            copiesTextView = itemView.findViewById(R.id.copiesTextView);
        }
    }
}