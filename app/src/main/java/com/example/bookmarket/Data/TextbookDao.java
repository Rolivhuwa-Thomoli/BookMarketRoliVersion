package com.example.bookmarket.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TextbookDao {
    @Insert
    void insert(TextbookEntity textbook);

    @Update
    void update(TextbookEntity textbook);

    @Delete
    void delete(TextbookEntity textbook);

    @Query("SELECT * FROM textbooks ORDER BY title ASC")
    List<TextbookEntity> getAllTextbooks();

    @Query("SELECT * FROM textbooks ORDER BY title ASC")
    LiveData<List<TextbookEntity>> getAllTextbooksLiveData();

    @Query("SELECT * FROM textbooks WHERE title LIKE :search OR seller LIKE :search")
    List<TextbookEntity> searchTextbooks(String search);
}