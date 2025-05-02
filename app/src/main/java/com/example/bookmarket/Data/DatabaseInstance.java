package com.example.bookmarket.Data;
import android.content.Context;
import androidx.room.Room;

public class DatabaseInstance {
    private static AppDatabase instance;

    public static AppDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "app_database"
            ).build();
        }
        return instance;
    }
}