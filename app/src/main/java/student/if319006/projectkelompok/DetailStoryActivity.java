package student.if319006.projectkelompok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailStoryActivity extends AppCompatActivity {

    private AppDBStory appDBStory;
    private TextView MyTextView;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_story);
        appDBStory = Room.databaseBuilder(getApplicationContext(),AppDBStory.class,
                "dbStory").build();

        TextView btnCourse1 = findViewById(R.id.btnCourses1);
        MyTextView = (TextView) findViewById(R.id.cCourses);
        btnCourse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertStory();
            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailStoryActivity();
            }
        });
    }

    private void insertStory(){
        new AsyncTask<Void, Void, Boolean>(){
            @Override
            protected Boolean doInBackground(Void... voids){
                Story pam = new Story(11319037, "Maudy Ayunda sempat mengungkap kegalauannya pascaditerima di dua universitas ternama dunia, yakni Harvard University dan Stanford University. Kini, pemilik nama asli Ayunda Faza Maudya itu telah menentukan pilihannya.\n" +
                        "Maudy Ayunda akan melanjutkan program magisternya (S2) di Stanford University. Kabar ini bagikan Maudy lewat postingan di Instagramnya, Senin (25/3). Di fotonya, Maudy Ayunda membagikan fotonya dengan mengenakan hoodie bertuliskan Stanford sambil memamerkan senyum bahagianya.\n" +
                        "\n" +
                        "Maudy Ayunda mengungkap banyak alasan yang membuatnya memilih untuk belajar di Stanford, di antaranya Stanford dekat dengan Silicon Valley dan yang terpenting Maudy Ayunda bisa mendapat gelar gabungan yakni MBA dan pendidikan.\n" +
                        "\n" +
                        "\"Ada begitu banyak alasan di balik pilihan ini: Stanford dekat dengan Silicon Valley, dekat pembelajarannya, sumber dayanya ... yang paling penting, di Stanford saya akan dapat gelar gabungan, MBA dan Pendidikan - yang merupakan kombinasi sempurna yang selalu menjadi hasrat saya untuk belajar,\" sambungnya.\n" +
                        "\n" +
                        "\n" +
                        "\"Terakhir, hati saya mengatakan itu akan menjadi tempat yang tepat untuk saya. Saya bisa membayangkan banyaknya pengetahuan dan pengalaman yang akan saya serap. Saya selamanya bersyukur dan akan menggunakan kesempatan ini untuk memperbaiki diri saya dan semoga masyarakat dan juga dunia. Doain ya teman2,\" pungkas Maudy Ayunda.");
                appDBStory.storyDao().hapus(pam);

                appDBStory.storyDao().insertStory(pam);
                return true;
            }
            @Override
            protected  void onPostExecute(Boolean stat){
                showStory();
            }
        }.execute();
    }

    private void showStory() {
        new AsyncTask<Void, Void,String>(){
            @Override
            protected String doInBackground(Void... voids){
                String cor = appDBStory.storyDao().readStory().toString();
                return cor;
            }
            @Override
            protected void onPostExecute(String bio){
                MyTextView.setText(bio);

            }

        }.execute();
    }

    public void openDetailStoryActivity(){
        Intent intent = new Intent(this, StoryActivity.class);
        startActivity(intent);
    }
}