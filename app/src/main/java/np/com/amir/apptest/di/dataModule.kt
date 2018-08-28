package np.com.amir.apptest.di

import android.arch.persistence.room.Room
import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import np.com.amir.apptest.data.local.InAppDatabase
import np.com.amir.apptest.data.local.dbHelper.DbHelper
import np.com.amir.apptest.data.local.dbHelper.IDbHelper
import np.com.amir.apptest.data.local.handlers.DbThread
import np.com.amir.apptest.data.remote.ApiService
import np.com.amir.apptest.data.remote.ApiServiceHolder
import np.com.amir.apptest.data.repository.AppDataManager
import np.com.amir.apptest.data.repository.IAppDataManager
import np.com.amir.apptest.data.sharePreference.IPreferenceHelper
import org.koin.dsl.module.module

val dataModule = module {

    single { provideRoomDatabase(get())}
    single{ provideDbThread()}
    single{ provideDataManager(get(),get(),get(),get())}
    single{ provideDbHelper(get())}
    single{ provideApiServiceHolder()}

}

fun provideRoomDatabase(context: Context): InAppDatabase {
    return Room.databaseBuilder(context, InAppDatabase::class.java, "BingoDatabase").addMigrations().fallbackToDestructiveMigration().build()
}

fun provideDbThread(): DbThread {
    lateinit var mDbWorkerThread: DbThread
    mDbWorkerThread = DbThread("dbWorkerThread")
    mDbWorkerThread.start()
    return mDbWorkerThread
}

fun provideDataManager(iApiService: ApiService, appDbHelper: IDbHelper, preferenceHelper: IPreferenceHelper, compositeDisposable: CompositeDisposable): IAppDataManager = AppDataManager(iApiService, preferenceHelper, appDbHelper, compositeDisposable)


fun provideDbHelper(appDatabase: InAppDatabase): DbHelper = DbHelper(appDatabase)

fun provideApiServiceHolder(): ApiServiceHolder = ApiServiceHolder()



