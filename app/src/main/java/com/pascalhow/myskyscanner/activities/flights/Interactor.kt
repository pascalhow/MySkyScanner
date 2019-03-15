package com.pascalhow.myskyscanner.activities.flights

import io.reactivex.Observable

interface Interactor {
    fun getDataModelList(parameters: MutableMap<String, String>): Observable<List<TripDataModel>>
}
