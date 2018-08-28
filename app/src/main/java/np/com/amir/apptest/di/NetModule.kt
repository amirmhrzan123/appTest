package np.com.amir.apptest.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.disposables.CompositeDisposable
import np.com.amir.apptest.BuildConfig
import np.com.amir.apptest.data.remote.ApiService
import np.com.amir.apptest.data.remote.ApiServiceHolder
import np.com.amir.apptest.data.remote.TokenAuthenticator
import np.com.amir.apptest.data.sharePreference.IPreferenceHelper
import np.com.amir.apptest.data.sharePreference.PreferenceHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val netModules = module {
    single{ provideGson() }

    single { provideOkHttpBuilder(get(),get())}

    single{provideLoggingInterceptor()}

    single{provideApiService(get(),get())}

    single{ createWebService<ApiService>(get())}

    single{ provideTokenService(get(),get(),get())}

    single{ provideCompositeDisposable()}

}


fun provideOkHttpBuilder(httpLoggingInterceptor: HttpLoggingInterceptor, tokenAuthenticator: TokenAuthenticator): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.connectTimeout(30, TimeUnit.SECONDS)
    builder.readTimeout(1, TimeUnit.MINUTES)
    builder.writeTimeout(2, TimeUnit.MINUTES)
    builder.authenticator(tokenAuthenticator)
    if (BuildConfig.DEBUG) {
        builder.addInterceptor(httpLoggingInterceptor)
    }
    return builder.build()
}



fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}

fun provideApiService(retrofit: Retrofit, apiServiceHolder: ApiServiceHolder): ApiService {
    val apiService = retrofit.create(ApiService::class.java)
    apiServiceHolder.apiService = apiService
    return apiService
}

fun provideTokenService(context: Context, apiServiceHolder: ApiServiceHolder, preferenceHelper: IPreferenceHelper): TokenAuthenticator = TokenAuthenticator(context, apiServiceHolder, preferenceHelper)


fun provideGson(): Gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

fun provideCompositeDisposable(): CompositeDisposable {
    return CompositeDisposable()
}







const val SERVER_URL = "http://13.211.109.111:8092/api/"

