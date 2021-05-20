package student.if319006.projectkelompok;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CorrectDialog {
    private Context mContext;
    private Dialog correctDialog;

    public CorrectDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void correctDialog(int score){

        correctDialog = new Dialog(mContext);

        correctDialog.setContentView(R.layout.activity_correct_dialog);
        final Button btncorrectDialog = (Button) correctDialog.findViewById(R.id.btnScoreDialog);

        Score(score);

        btncorrectDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctDialog.dismiss();

            }
        });

        correctDialog.show();
        correctDialog.setCancelable(false);
        correctDialog.setCanceledOnTouchOutside(false);


    }

    private void Score(int score) {
        TextView textScore = (TextView) correctDialog.findViewById(R.id.textView_Score);
        textScore.setText("Skor : "+String.valueOf(score));

    }

}
