package student.if319006.projectkelompok;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="info")

public class Info {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "judul")
    public String judul;
    @ColumnInfo(name = "isi")
    public String isi;

    public Info(int id, String judul, String isi) {
        this.id = id;
        this.judul = judul;
        this.isi= isi;
    }

    @Override
    public String toString(){
        return "\n"+
                "\n"+
                ""+isi+"\n";

    }

}
