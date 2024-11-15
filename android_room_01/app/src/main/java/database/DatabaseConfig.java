package database;

public class DatabaseConfig {
    private static final String ROOM_DB_NAME = "app_database";
    private static final String USER_TABLE = "user_table";

    public String getDbName(){
        return ROOM_DB_NAME;
    }
}
