package au.edu.cat.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import au.edu.cat.Data;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void coll(Data... data);

    @Query("SELECT * FROM data")
    public  List<Data> getAll();
}
