package np.com.amir.apptest.data.repository

import np.com.amir.apptest.data.local.databaseHelper.IDatabaseHelper
import np.com.amir.apptest.data.remote.ApiService
import np.com.amir.apptest.data.sharePreference.IPreferenceHelper

interface IAppDataManager:ApiService,IPreferenceHelper,IDatabaseHelper {
}