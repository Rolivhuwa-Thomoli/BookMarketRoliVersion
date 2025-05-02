// TextbookEntity.java
package com.example.bookmarket.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "textbooks")
public class TextbookEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String author;
    public double price;
    public String seller;
    public int copies;
    public String bankInfo;

    // Add constructor, getters and setters as needed
}