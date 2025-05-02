package com.example.bookmarket.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TextbookDao {
    @Insert
    void insertTextbook(TextbookEntity textbook);

    @Query("SELECT * FROM textbooks")
    List<TextbookEntity> getAllTextbooks();
}