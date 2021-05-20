package student.if319006.projectkelompok;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Info.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract InfoDao infoDao();
}
