package com.example.bookmarket.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "textbooks")
public class TextbookEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String author;
    private String price;
    private String sellerName;
    private int quantity;
    private String contactNumber;

    // Constructors
    public TextbookEntity(String title, String author, String price, String sellerName, int quantity, String contactNumber) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.sellerName = sellerName;
        this.quantity = quantity;
        this.contactNumber = contactNumber;
    }

    public TextbookEntity() {

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}