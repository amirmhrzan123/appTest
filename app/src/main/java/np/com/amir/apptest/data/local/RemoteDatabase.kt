package np.com.amir.apptest.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(
        entities = [

        ], version = 1, exportSchema = false)

abstract class RemoteDatabase : RoomDatabase() {
    abstract fun daoAccess(): DaoAccess
}