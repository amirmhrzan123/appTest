package np.com.amir.apptest.data.model

class Venue {
    data class Request(val id: Int)

    data class Response(val id: Int,
                        val name:String,
                        val birthDate: String)


}