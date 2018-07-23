package np.com.amir.apptest.util

import io.reactivex.Scheduler


interface SchedulerProvider{
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}