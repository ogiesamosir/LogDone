package student.if319006.projectkelompok;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface InfoDao {
    @Query("SELECT * FROM info")
    Info readInfo();

    @Insert
    void insertInfo(Info info);

    @Delete
    void hapus(Info info);
}
