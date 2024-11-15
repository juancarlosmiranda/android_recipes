package database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AuthorDao {
    @Query("SELECT * FROM authors_table")
    List<Author> getAll();

    @Query("SELECT * FROM authors_table WHERE id IN (:userIds)")
    List<Author> loadAllByIds(int[] userIds);

    // for searchin, maybe an improvement could be concatenated strings
    @Query("SELECT * FROM authors_table WHERE first_name LIKE :firstNameP LIMIT 1")
    Author findByName(String firstNameP);

    @Query("SELECT * FROM authors_table WHERE id =:idP")
    Author findById(int idP);

    @Insert
    void insertAll(Author... authors);

    @Insert
    void insert(Author author);

    @Delete
    void delete(Author author);

    @Query("DELETE FROM authors_table")
    void deleteAll();

    @Query("SELECT * FROM authors_table ORDER BY authors_table.first_name ASC")
    LiveData<List<Author>> getAuthorsFirstname();
}
