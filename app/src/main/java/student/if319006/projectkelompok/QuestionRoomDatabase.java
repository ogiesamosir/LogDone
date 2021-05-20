package student.if319006.projectkelompok;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Question.class} , version = 1)
public abstract class QuestionRoomDatabase extends RoomDatabase {

    private static QuestionRoomDatabase INSTANCE;

    public abstract QuestionDao questionDao();

    public static synchronized QuestionRoomDatabase getInstance(final Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuestionRoomDatabase.class,"question_table")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback)
                    .build();
        }


        return INSTANCE;
    }

    private static RoomDatabase.Callback RoomDBCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{

        private QuestionDao questionDao;


        private PopulateDbAsyncTask(QuestionRoomDatabase db){
            questionDao = db.questionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            questionDao.insert(new Question(" Corona (COVID-19) yang menyerang manusia muncul di negara ... pada awal tahun ...." , "Indonesia", "Italia" , "USA","Cina",4));
            questionDao.insert(new Question("Masa inkubasi COVID-19 selama ... hari." , "2 Minggu", "12 Hari" , "14 Minggu ","14 Hari",4));
            questionDao.insert(new Question("Gejala awal orang yang terkena COVID-19 yaitu..." , "Demam, batuk, pilek, gangguan pernapasan.", "Demam, sakit tenggorokan, sakit semua." , "Gangguan pernapasan, sakit tenggorokan, letih, lesu, dan kejang-kejang.","Letih, dan lesu dan nyeri otak.",1));
            questionDao.insert(new Question("Upaya psikologis agar daya tahan tubuh kuat untuk menjaga agar tidak tertular COVID-19 adalah.." , "Banyak hiburan, bersuka cita, dan bersenang-senang", "Tidak panik, tidak stres, dan tetap waspada" , "Banyak berolah raga, bernyanyi, dan humor","Banyak berdoa, optimis, dan pantang menyerah",2));
            questionDao.insert(new Question("Di bawah ini adalah dampak sosial yang positif dari penyebaran COVID-19, kecuali...." , "Adanya kewaspadaan tertular COVID-19.", "Tidak ada lagi kerumunan warga" , "Adanya saling curiga","Komunikasi dalam keluarga menjadi lebih erat.",4));
            questionDao.insert(new Question("Jika wabah COVID-19 berlangsung lama, maka dampak negatif ekonomi yang dirasakan seperti di bawah ini, kecuali...." , "Menurunnya investasi dan turunnya pendapatan negara.", "Turunnya pendapatan dan daya beli masyarakat." , "Potensi PHK dan meningkatnya pengangguran sebagai dampak dari penutupan sementara tempat-tempat usaha.","Meningkatnya transaksi belanja secara online sehubungan masyarakat tidak boleh keluar rumah.",4));
            questionDao.insert(new Question("Untuk membangun kesadaran masyarakat terhadap bahaya COVID-19, maka hal yang perlu dilakukan adalah sebagai berikut, kecuali..." , "Melakukan sosialisasi dengan bahasa dan media yang mudah dipahami.", "Meminta masyarakat untuk membaca sendiri informasi seputar COVID-19." , "Diberikan kumpulan berita yang isinya tentang daftar korban COVID-19.","Diberikan kumpulan doa untuk terhindar dari wabah COVID-19.",4));
            questionDao.insert(new Question("Jika sebuah daerah pada awalnya termasuk zona hijau COVID-19, lalu menjadi merah karena ada yang tertular dari tempat lain, maka hal yang perlu dilakukan oleh masyarakat adalah, kecuali...." , "Memarahi orang yang telah membawa penyakit dari kota ke kampung.", "Menjauhi korban karena khawatir tertular, lalu meminta dia mengisolasi di rumahnya." , "Meminta pemerintah membangun puskesmas darurat di kampung tersebut. ","Memberikan semangat dan menghubungi petugas kesehatan setempat untuk diperiksa ditangani lebih lanjut.",1));
            questionDao.insert(new Question("Isu yang beredar mengatakan virus corona (COVID-19) dapat dibawa oleh hewan... dan menular kepada manusia." , "Burung", "Ayam" , "Itik ","Kelelawar",4));
            questionDao.insert(new Question("COVID-19 bisa masuk melalui anggota-anggota tubuh di bawah ini, kecuali..." , "Mata", "Telinga" , "Hidung ","Mulut",2));

            return null;
        }
    }



}

