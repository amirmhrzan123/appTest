package np.com.amir.apptest.di

import np.com.amir.apptest.util.ApplicationSchedulerProvider
import np.com.amir.apptest.util.SchedulerProvider
import org.koin.dsl.module.applicationContext

val otherModules = applicationContext {

}

val rxModule = applicationContext {
    bean {
        ApplicationSchedulerProvider() as SchedulerProvider
    }
}

//Gather all app Modules
val appModule = listOf(otherModules, rxModule)