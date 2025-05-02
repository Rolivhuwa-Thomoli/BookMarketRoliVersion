package com.example.bookmarket.Data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.bookmarket.Data.TextbookEntity;

@Database(entities = {TextbookEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract TextbookDao textbookDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "book_market_database")
                            .fallbackToDestructiveMigration() // Ensure migration doesn't block
                            .build();
                    System.out.println("Database initialized");
                }
            }
        }
        return INSTANCE;
    }
}