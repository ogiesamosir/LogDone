package student.if319006.projectkelompok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView txtHighscore, txtTotalQuestion,txtCorrect,txtWrong;
    Button btnPlayAgain, btnMainMenu;

    int highScore = 0;

    private static final String SHARED_PREFERENCE = "shared_preferences";
    private static final String SHARED_PREFERENCE_HIGH_SCORE = "shared_preferences_high_score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtHighscore = findViewById(R.id.tv_Highscore);
        txtTotalQuestion = findViewById(R.id.tv_Question);
        txtCorrect = findViewById(R.id.tv_correct);
        txtWrong = findViewById(R.id.tv_wrong);

        btnMainMenu = findViewById(R.id.btnMainMenu);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this , KuisCovidActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loadHighScore();

        Intent intent = getIntent();
        int score = intent.getIntExtra("UserScore", 0);
        int totalQuestions = intent.getIntExtra("TotalQuizQuestions", 0);
        int correctAnswer = intent.getIntExtra("CorrectQuestions", 0);
        int wrongAnswer = intent.getIntExtra("WrongQuestions", 0);

        txtTotalQuestion.setText("Total Soal : "+String.valueOf(totalQuestions));
        txtCorrect.setText("Benar : "+String.valueOf(correctAnswer));
        txtWrong.setText(" Salah : "+String.valueOf(wrongAnswer));

        if (score > highScore){
            updateScore(score);
        }

    }

    private void updateScore(int score) {
        highScore = score;

        txtHighscore.setText("Skor tertinggi : " + String.valueOf(highScore));

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFERENCE_HIGH_SCORE,highScore);
        editor.apply();


    }

    private void loadHighScore() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(SHARED_PREFERENCE_HIGH_SCORE , 0);

        txtHighscore.setText("Skor tertinggi : " + String.valueOf(highScore));
    }
}