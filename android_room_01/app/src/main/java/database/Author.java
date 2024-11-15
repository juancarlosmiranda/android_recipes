package database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "authors_table")
public class Author {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    //@ColumnInfo(name = "date_of_birth")
    //private LocalDate dateOfBirth;


    public Author(){
        this.firstName = null;
        this.lastName = null;
        //this.dateOfBirth = LocalDate.of(1981,01,01);
    }

    public Author(@NonNull String firstNameP, @NonNull String lastNameP, LocalDate dateOfBirthP){
        this.firstName = firstNameP;
        this.lastName = lastNameP;
        //this.dateOfBirth = dateOfBirthP;
    }

    public void setId(int idP){
        this.id = idP;
    }
    public void setFirstName(String firstNameP) {
        this.firstName = firstNameP;
    }
    public void setLastName(String lastNameP){
        this.lastName = lastNameP;
    }
    /*public void setDateOfBirth(LocalDate dateOfBirthP){
        this.dateOfBirth = dateOfBirthP;
    }*/

    @NonNull
    public int getId(){
        return id;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    /*public LocalDate getDateOfBirth(){
        return this.dateOfBirth;
    }*/
    // TODO: to solve Date data with Room

}
