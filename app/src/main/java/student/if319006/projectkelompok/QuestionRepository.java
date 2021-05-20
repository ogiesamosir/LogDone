package student.if319006.projectkelompok;
import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionRepository {

    private QuestionDao mQuestionDao;
    private LiveData<List<Question>> mAllQuestions;

    public QuestionRepository(Application application){

        QuestionRoomDatabase db = QuestionRoomDatabase.getInstance(application);
        mQuestionDao = db.questionDao();
        mAllQuestions = mQuestionDao.getAllQuestions();
    }

    public LiveData<List<Question>> getmAllQuestions(){

        return mAllQuestions;
    }



}

