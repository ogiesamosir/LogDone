package student.if319006.projectkelompok;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="story")

public class Story {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "isi")
    public String isi;

    public Story(int id, String isi) {
        this.id = id;
        this.isi= isi;
    }

    @Override
    public String toString(){
        return "\n"+
                ""+isi+"\n";

    }

}