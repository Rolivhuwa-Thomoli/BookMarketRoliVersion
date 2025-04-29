package com.example.bookmarket;

import java.util.Objects;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "textbooks")
public class Textbook {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String author;
    private String price;
    private String sellerName;
    private int numberOfCopies;
    // New field for the number of copies

    public Textbook(String title, String author, String price, String sellerName, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.sellerName = sellerName;
        this.numberOfCopies = numberOfCopies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPrice() {
        return price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    // Override equals and hashCode to prevent duplicates based on title and author
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Textbook textbook = (Textbook) o;
        return Objects.equals(title, textbook.title) &&
                Objects.equals(author, textbook.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
}