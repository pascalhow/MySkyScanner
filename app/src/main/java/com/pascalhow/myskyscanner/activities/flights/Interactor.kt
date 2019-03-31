package com.pascalhow.myskyscanner.activities.flights

import com.pascalhow.myskyscanner.utils.SchedulersProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class Interactor<T>(private val schedulersProvider: SchedulersProvider) {

    private val disposables = CompositeDisposable()

    abstract fun buildInteractorObservables(params: MutableMap<String, String>): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: MutableMap<String, String>) {
        val observable = buildInteractorObservables(params)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.mainThread())

        disposables.add(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}
