package np.com.amir.apptest.di

import android.arch.persistence.room.Room
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.disposables.CompositeDisposable
import np.com.amir.apptest.data.local.InAppDatabase
import np.com.amir.apptest.data.local.databaseHelper.IDatabaseHelper
import np.com.amir.apptest.data.remote.ApiService
import np.com.amir.apptest.data.repository.AppDataManager
import np.com.amir.apptest.data.sharePreference.IPreferenceHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit




fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
}


inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}


fun provideGson(): Gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

fun provideCompositeDisposable(): CompositeDisposable {
    return CompositeDisposable()
}

fun provideRemoteDatabase(context: Context): InAppDatabase {
    return Room.databaseBuilder(context, InAppDatabase::class.java, "AppTest").addMigrations().build()
}
fun provideAppRepository(apiService: ApiService,iPreferenceHelper: IPreferenceHelper,iDatabaseHelper: IDatabaseHelper,compositeDisposable: CompositeDisposable): AppDataManager {
    return AppDataManager(apiService,iPreferenceHelper,iDatabaseHelper,compositeDisposable)
}





const val SERVER_URL = "http://13.211.109.111:8092/api/"

