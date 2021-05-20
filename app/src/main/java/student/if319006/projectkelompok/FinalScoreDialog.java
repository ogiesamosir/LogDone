package student.if319006.projectkelompok;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalScoreDialog {
    private Context mContext;
    private Dialog finalScoreDialog;

    private TextView textViewFInalScore;

    public FinalScoreDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void finalScoreDialog(int correctAns , int wrongAns , int totalSizeofQuiz){

        finalScoreDialog = new Dialog(mContext);

        finalScoreDialog.setContentView(R.layout.activity_final_score);
        final Button btnFinalScoreDialog = (Button)  finalScoreDialog.findViewById(R.id.btnFinalDialog);

        finalScore(correctAns,wrongAns, totalSizeofQuiz);

        btnFinalScoreDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalScoreDialog.dismiss();
                Intent intent = new Intent( mContext, KuisCovidActivity.class);
                mContext.startActivity(intent);
            }
        });

        finalScoreDialog.show();
        finalScoreDialog.setCancelable(false);
        finalScoreDialog.setCanceledOnTouchOutside(false);


    }

    private void finalScore(int correctAns , int wrongAns,int totalSizeOfQuiz) {

        int tempScore = 0;
        textViewFInalScore = (TextView)finalScoreDialog.findViewById(R.id.textView_final_Score);

        if (correctAns == totalSizeOfQuiz){
            tempScore = (correctAns * 20 ) - (wrongAns * 5);
            textViewFInalScore.setText("Nilai kuis : "+ String.valueOf(tempScore));
        }else if(wrongAns == totalSizeOfQuiz){
            tempScore = 0 ;
            textViewFInalScore.setText("Nilai kuis : "+ String.valueOf(tempScore));
        }else if (correctAns > wrongAns){
            tempScore = (correctAns * 20 ) - (wrongAns * 5);
            textViewFInalScore.setText("Nilai kuis : "+ String.valueOf(tempScore));
        }else if (wrongAns > correctAns){
            tempScore = (wrongAns * 5) - (correctAns * 20 )   ;
            textViewFInalScore.setText("Nilai kuis : "+ String.valueOf(tempScore));
        }else if (correctAns == wrongAns){
            tempScore = (correctAns * 20 ) - (wrongAns * 5);
            textViewFInalScore.setText("Nilai kuis : "+ String.valueOf(tempScore));
        }
    }

}
