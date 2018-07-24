package np.com.amir.apptest.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import np.com.amir.apptest.data.local.daos.UserDao
import np.com.amir.apptest.data.local.daos.VenueDao
import np.com.amir.apptest.data.model.User
import np.com.amir.apptest.data.model.Venue

@Database(
        entities = [
            User.Response::class,
            Venue.Response::class

        ], version = 1, exportSchema = false)

abstract class InAppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun venueDao(): VenueDao

}