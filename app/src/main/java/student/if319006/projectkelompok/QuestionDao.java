package student.if319006.projectkelompok;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface QuestionDao {

    @Query("SELECT * FROM question_table")
    LiveData<List<Question>> getAllQuestions();

    @Insert
    void insert(Question questions);


}
