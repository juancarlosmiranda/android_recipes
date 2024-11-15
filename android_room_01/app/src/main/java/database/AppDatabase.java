package database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {Author.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AuthorDao authorDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static DatabaseConfig dbConfig = new DatabaseConfig();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, dbConfig.getDbName())
                            //.addMigrations(AppDatabase.MIGRATION_1_2)  // Add your migration(s)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                AuthorDao dao = INSTANCE.authorDao();
                dao.deleteAll();
                /*
                Word word = new Word("Hello");
                dao.insert(word);
                word = new Word("World");
                dao.insert(word);
                */
            });
        }
    };
}
