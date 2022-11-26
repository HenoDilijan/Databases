package club.aborigen.database.ui.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EmployeeEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();
}
