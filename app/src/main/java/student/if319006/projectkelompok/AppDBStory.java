package student.if319006.projectkelompok;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Story.class}, version = 1)
public abstract class AppDBStory extends RoomDatabase {
    public abstract StoryDao storyDao();
}

