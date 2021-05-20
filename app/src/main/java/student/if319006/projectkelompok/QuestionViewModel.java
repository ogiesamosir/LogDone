package student.if319006.projectkelompok;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepository mRepository;

    private LiveData<List<Question>> mAllQuestions;

    public QuestionViewModel(Application application){
        super(application);
        mRepository = new QuestionRepository(application);
        mAllQuestions = mRepository.getmAllQuestions();

    }

    LiveData<List<Question>> getmAllQuestions(){
        return mAllQuestions;
    }


}
