package au.edu.cat.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import au.edu.cat.Data;
import au.edu.cat.WeightConverter;

@Database(entities = {Data.class}, version=1)
@TypeConverters({WeightConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "cat")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract AppDao dao();
}