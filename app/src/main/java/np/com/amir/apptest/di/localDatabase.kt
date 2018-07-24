package np.com.amir.apptest.di

import io.reactivex.disposables.CompositeDisposable
import np.com.amir.apptest.data.local.databaseHelper.IDatabaseHelper
import np.com.amir.apptest.data.remote.ApiService
import np.com.amir.apptest.data.repository.AppDataManager
import np.com.amir.apptest.data.sharePreference.IPreferenceHelper
import org.koin.dsl.module.applicationContext

val dataModule = applicationContext {

    bean {
        AppDataManager(get() as ApiService,get() as IPreferenceHelper,get() as IDatabaseHelper ,get() as CompositeDisposable)
    }


}



