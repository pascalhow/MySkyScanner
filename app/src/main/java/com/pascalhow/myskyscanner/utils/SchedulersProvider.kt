package com.pascalhow.myskyscanner.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

interface SchedulersProvider {
    fun io() : Scheduler
    fun mainThread() : Scheduler
    fun testScheduler() : Scheduler
}
