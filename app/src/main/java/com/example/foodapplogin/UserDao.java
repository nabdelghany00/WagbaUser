package com.example.foodapplogin;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user) ;

    @Query("SELECT * FROM user WHERE email = :email")
    User getUser(String email);

}
