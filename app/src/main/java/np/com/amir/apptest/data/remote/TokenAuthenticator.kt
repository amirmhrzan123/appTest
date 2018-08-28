package np.com.amir.apptest.data.remote

import android.content.Context
import android.content.Intent
import android.util.Log

import io.reactivex.schedulers.Schedulers
import np.com.amir.apptest.data.sharePreference.IPreferenceHelper
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * Created by Nabin Shrestha on 8/13/2018.
 */
class TokenAuthenticator
constructor(private val context: Context, private val apiServiceHolder: ApiServiceHolder, private val preferenceHelper: IPreferenceHelper) : Authenticator {
    private var request: Request? = null
    private var newAccessToken: String? = null

    override fun authenticate(route: Route, response: Response): Request? {

       // val accessToken = refreshAccessToken()
        val accessToken = ""
        if (accessToken != null) {
           // preferenceHelper.setBearerToken(accessToken)

//            request = response.request().newBuilder()
//                    .header(AUTHORIZATION, preferenceHelper.getBearerToken())
//                    .build()

        } else {
            request = null
            sendTokenRefreshFailedBroadCast()
        }


        return request

    }

    private fun sendTokenRefreshFailedBroadCast() {
        val intent = Intent()
       // intent.action = REFRESH_FAILED
        context.sendBroadcast(intent)
    }

/*
    private fun refreshAccessToken(): String? {
        val request = TokenModel.Request(
                refreshToken = preferenceHelper.getRefreshToken()
        )
       apiServiceHolder.apiService!!.refreshToken(request)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        { t: retrofit2.Response<BaseResponse<TokenModel.Response.Data>>? ->
                            t?.let {
                                Log.d("ResponseCode", it.headers().get(ACCESS_TOKEN)!! + "")
                                newAccessToken = it.headers().get(ACCESS_TOKEN)!!
                            }
                        },
                        { error ->
                            Log.d("ResponseError", error.message)
                        }
                )

        return newAccessToken
    }
*/
}