package np.com.amir.apptest.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

class Venue {
    data class Request(val id: Int)

    @Entity(tableName = "Venue")
    class Response(@PrimaryKey
                   @ColumnInfo(name = "ID") val ID: Int,
                   @ColumnInfo(name = "name") val  name: String?)


}