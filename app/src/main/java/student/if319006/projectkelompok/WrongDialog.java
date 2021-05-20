package student.if319006.projectkelompok;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class WrongDialog {

    private Context mContext;
    private Dialog wrongAnswerDialog;
    ;

    private TextView textViewFInalScore;

    WrongDialog(Context mContext) {
        this.mContext = mContext;
    }

    void wrongDialog(String correctAnswer){

        wrongAnswerDialog = new Dialog(mContext);
        wrongAnswerDialog.setContentView(R.layout.activity_wrong_dialog);

        final Button btnWrongAnswerDialog = (Button) wrongAnswerDialog.findViewById(R.id.btnWrongDialog);
        TextView textView = (TextView) wrongAnswerDialog.findViewById(R.id.textView_correct_Answer);

        textView.setText("Kunci : " + correctAnswer);

        btnWrongAnswerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongAnswerDialog.dismiss();

            }
        });

        wrongAnswerDialog.show();
        wrongAnswerDialog.setCancelable(false);
        wrongAnswerDialog.setCanceledOnTouchOutside(false);


    }

}
