// AppDatabase.java
package com.example.bookmarket.Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TextbookEntity.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TextbookDao textbookDao();
    public abstract UserDao userDao();
}