package student.if319006.projectkelompok;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StoryDao {
    @Query("SELECT * FROM story")
    Story readStory();

    @Insert
    void insertStory(Story story);

    @Delete
    void hapus(Story story);
}
