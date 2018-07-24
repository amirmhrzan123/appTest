package np.com.amir.apptest.data.repository

import io.reactivex.disposables.CompositeDisposable
import np.com.amir.apptest.data.local.databaseHelper.IDatabaseHelper
import np.com.amir.apptest.data.remote.ApiService
import np.com.amir.apptest.data.sharePreference.IPreferenceHelper

class AppDataManager(private val apiService: ApiService,
                     private val iPreferenceHelper: IPreferenceHelper,
                     private val iDatabaseHelper: IDatabaseHelper,
                     private val compositeDisposable: CompositeDisposable):IAppDataManager {


}