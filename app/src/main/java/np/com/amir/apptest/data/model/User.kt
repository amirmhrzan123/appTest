package np.com.amir.apptest.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

class User {

    data class Request(val id: Int)

    @Entity(tableName = "User")
    class Response(@PrimaryKey @ColumnInfo(name = "ID") val ID: Int,
                        @ColumnInfo(name = "name") val name: String?)


}