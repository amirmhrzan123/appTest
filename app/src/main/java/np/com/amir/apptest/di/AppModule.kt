package np.com.amir.apptest.di

import android.app.Application

import android.content.res.Resources

import np.com.amir.apptest.util.ApplicationSchedulerProvider
import np.com.amir.apptest.util.SchedulerProvider
import org.koin.dsl.module.applicationContext
import org.koin.dsl.module.module

val viewModelModule = module {

}


val rxModule = module {
    single {
        ApplicationSchedulerProvider() as SchedulerProvider
    }

}

/*
@SuppressLint("HardwareIds")
@Named(DEVICE_ID)
fun provideDeviceId(application: Application): String = Settings.Secure.getString(application.contentResolver, Settings.Secure.ANDROID_ID)
*/

//Gather all app Modules
val appModule = listOf(viewModelModule, rxModule,dataModule,netModules)




fun provideResources(application: Application): Resources = application.resources




