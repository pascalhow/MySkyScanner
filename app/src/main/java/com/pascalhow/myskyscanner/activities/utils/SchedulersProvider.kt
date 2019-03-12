package com.pascalhow.myskyscanner.activities.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SchedulersProvider {

    fun io() = Schedulers.io()!!
    fun mainThread() = AndroidSchedulers.mainThread()!!

}
