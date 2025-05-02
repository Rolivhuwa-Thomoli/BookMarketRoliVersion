// DatabaseInstance.java
package com.example.bookmarket.Data;

import android.content.Context;
import androidx.room.Room;

public class DatabaseInstance {
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "book_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}