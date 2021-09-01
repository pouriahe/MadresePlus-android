package ir.madreseplus.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import ir.madreseplus.data.db.dao.ContentDao;
import ir.madreseplus.data.db.dao.StudentDao;
import ir.madreseplus.data.model.entity.Content;
import ir.madreseplus.data.model.entity.Student;
import ir.madreseplus.utilities.DateTableConverter;
import ir.madreseplus.utilities.EnumTableConverter;
import ir.madreseplus.utilities.ListTableConverter;

@Database(entities = {Student.class, Content.class}, version = 1)
@TypeConverters({DateTableConverter.class , ListTableConverter.class, EnumTableConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract StudentDao studentDao();

    public abstract ContentDao contentDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            AppDatabase.class, "mdpdb")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
