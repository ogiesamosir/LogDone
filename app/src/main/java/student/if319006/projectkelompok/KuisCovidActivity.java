package student.if319006.projectkelompok;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class KuisCovidActivity extends AppCompatActivity {

    TextView txtQuestion, textViewScore, textViewQuestionCount, textViewCountdownTimer;
    TextView textViewCorrect, textViewWrong;

    RadioButton rb1,rb2,rb3,rb4;
    RadioGroup rbGroup;
    Button btnNext;

    boolean answered = false;

    List<Question> quesList;
    Question currentQ;

    private int questionCounter = 0 , questionTotalCount;

    private QuestionViewModel questionViewModel;

    private ColorStateList textColorOfBUttons;

    private Handler handler = new Handler();

    private int correctAns = 0 , wrongAns = 0;

    private int score = 0;

    private FinalScoreDialog finalScoreDialog;

    private WrongDialog wrongDialog;

    private CorrectDialog correctDialog;

    private int totalSizeOfQuiz;

    private static final long COUNTDOWN_IN_MILIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeftinMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis_covid);
        setupUI();

        textColorOfBUttons = rb1.getTextColors();

        finalScoreDialog = new FinalScoreDialog(this);
        wrongDialog = new WrongDialog(this);
        correctDialog = new CorrectDialog(this);

        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        questionViewModel.getmAllQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                Toast.makeText(KuisCovidActivity.this , "Selamat Mengerjakan!" , Toast.LENGTH_LONG).show();

                fetchContent(questions);
            }
        });
    }


    void setupUI(){
        textViewCorrect = findViewById(R.id.txtCorrect);
        textViewCountdownTimer = findViewById(R.id.txtTimer);
        textViewWrong = findViewById(R.id.txtWrong);
        textViewScore = findViewById(R.id.txtScore);
        textViewQuestionCount =  findViewById(R.id.txtQuestionNum);
        txtQuestion = findViewById(R.id.txtQuestionContainer);

        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);

        btnNext = findViewById(R.id.button_confirm);

    }


    private void fetchContent(List<Question> questions) {
        quesList = questions;


        startQuiz();
    }

    // SetQuestionView() method


    private void setQuestionView(){
        rbGroup.clearCheck();

        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));
        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));

        rb1.setTextColor(Color.WHITE);
        rb2.setTextColor(Color.WHITE);
        rb3.setTextColor(Color.WHITE);
        rb4.setTextColor(Color.WHITE);

        questionTotalCount = quesList.size();
        //Collections.shuffle(quesList);
        if (questionCounter < questionTotalCount ){
            currentQ = quesList.get(questionCounter);

            txtQuestion.setText(currentQ.getQuestion());
            rb1.setText(currentQ.getOptA());
            rb2.setText(currentQ.getOptB());
            rb3.setText(currentQ.getOptC());
            rb4.setText(currentQ.getOptD());
            questionCounter++;

            answered = false;


            btnNext.setText("Confirm");

            textViewQuestionCount.setText("Questions: " + questionCounter + "/" +questionTotalCount);

            timeLeftinMillis = COUNTDOWN_IN_MILIS;
            startCountDown();

        }else{
            Toast.makeText(this, "Kuis selesai", Toast.LENGTH_SHORT).show();



            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    resultData();
                }
            },3000);
        }
    }


    private void startQuiz() {
        setQuestionView();

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.radioButton1:
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.when_answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));

                        break;

                    case R.id.radioButton2:
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.when_answer_selected));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));

                        break;

                    case R.id.radioButton3:
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.when_answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));

                        break;

                    case R.id.radioButton4:
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.when_answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.default_option_bg));

                        break;

                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered){
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked() ){
                        quizOperation();

                    }else{
                        Toast.makeText(KuisCovidActivity.this, "Please select answer!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void quizOperation() {
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());

        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        checkSolution(answerNr , rbSelected);

    }

    private void checkSolution(int answerNr, RadioButton rbSelected) {

        switch (currentQ.getAnswer()){

            case 1:
                if (currentQ.getAnswer() == answerNr) {
                    rb1.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.when_answer_correct));
                    rb1.setTextColor(Color.BLACK);

                    correctAns++;
                    textViewCorrect.setText("Correct : " + String.valueOf(correctAns));

                    score += 10;
                    textViewScore.setText("Score : " +String.valueOf(score));

                    correctDialog.correctDialog(score);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionView();
                        }
                    },500);


                }else{
                    changeToIncorrectColor(rbSelected);
                    wrongAns++;
                    textViewWrong.setText("Wrong : " + String.valueOf(wrongAns));

                    final String correctAnswer = (String) rb1.getText();
                    wrongDialog.wrongDialog(correctAnswer);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionView();
                        }
                    },500);
                }
                break;

            case 2:
                if (currentQ.getAnswer() == answerNr) {
                    rb2.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.when_answer_correct));
                    rb2.setTextColor(Color.BLACK);

                    correctAns++;
                    textViewCorrect.setText("Correct : " + String.valueOf(correctAns));

                    score += 10;
                    textViewScore.setText("Score : " +String.valueOf(score));

                    correctDialog.correctDialog(score);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionView();
                        }
                    },500);


                }else{
                    changeToIncorrectColor(rbSelected);
                    wrongAns++;
                    textViewWrong.setText("Wrong : " + String.valueOf(wrongAns));

                    final String correctAnswer = (String) rb2.getText();
                    wrongDialog.wrongDialog(correctAnswer);


                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionView();
                        }
                    },500);
                }
                break;

            case 3:
                if (currentQ.getAnswer() == answerNr) {
                    rb3.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.when_answer_correct));
                    rb3.setTextColor(Color.BLACK);

                    correctAns++;
                    textViewCorrect.setText("Correct : " + String.valueOf(correctAns));

                    correctDialog.correctDialog(score);

                    score += 10;
                    textViewScore.setText("Score : " +String.valueOf(score));

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionView();
                        }
                    },500);


                }else{
                    changeToIncorrectColor(rbSelected);

                    wrongAns++;
                    textViewWrong.setText("Wrong : " + String.valueOf(wrongAns));

                    score += 10;
                    textViewScore.setText("Score : " +String.valueOf(score));

                    final String correctAnswer = (String) rb3.getText();
                    wrongDialog.wrongDialog(correctAnswer);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionView();
                        }
                    },500);
                }
                break;

            case 4:
                if (currentQ.getAnswer() == answerNr) {
                    rb4.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.when_answer_correct));
                    rb4.setTextColor(Color.BLACK);

                    correctAns++;
                    textViewCorrect.setText("Correct : " + String.valueOf(correctAns));


                    score += 10;
                    textViewScore.setText("Score : " +String.valueOf(score));

                    correctDialog.correctDialog(score);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionView();
                        }
                    },500);

                }else{
                    changeToIncorrectColor(rbSelected);

                    wrongAns++;
                    textViewWrong.setText("Wrong : " + String.valueOf(wrongAns));

                    final String correctAnswer = (String) rb4.getText();
                    wrongDialog.wrongDialog(correctAnswer);


                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionView();
                        }
                    },500);
                }
                break;
        }

        if (questionCounter == questionTotalCount ){
            btnNext.setText("Confirm and Finish");
        }

    }

    private void changeToIncorrectColor(RadioButton rbSelected) {
        rbSelected.setBackground(ContextCompat.getDrawable(getApplicationContext() , R.drawable.when_answer_wrong));
        rbSelected.setTextColor(Color.BLACK);
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftinMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                timeLeftinMillis = 0;
                updateCountDownText();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftinMillis/1000) / 60;
        int seconds = (int) (timeLeftinMillis/1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d" , minutes , seconds);
        textViewCountdownTimer.setText(timeFormatted);

        if (timeLeftinMillis == 0){
            textViewCountdownTimer.setTextColor(Color.RED);
        }

        if (timeLeftinMillis == 0){
            Toast.makeText(this , "Waktu Habis" , Toast.LENGTH_SHORT).show();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(),KuisCovidActivity.class);
                    startActivity(intent);
                }
            },2000);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    private void resultData(){
        Intent resultofQuiz = new Intent(KuisCovidActivity.this , ResultActivity.class);
        resultofQuiz.putExtra("UserScore", score);
        resultofQuiz.putExtra("TotalQuizQuestions", questionTotalCount );
        resultofQuiz.putExtra("CorrectQuestions", correctAns);
        resultofQuiz.putExtra("WrongQuestions", wrongAns);
        startActivity(resultofQuiz);
    }



}