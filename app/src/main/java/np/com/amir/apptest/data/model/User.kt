package np.com.amir.apptest.data.model

class User {

    data class Request(val id: Int)

    data class Response(val id: Int,
                        val name:String,
                        val birthDate: String)


}