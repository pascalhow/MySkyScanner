package com.pascalhow.myskyscanner.activities.flights

import com.pascalhow.myskyscanner.utils.SchedulersProvider
import io.reactivex.Observable

class FlightDetailsInteractor(
    private val repository: Repository,
    schedulersProvider: SchedulersProvider
) : Interactor<List<TripDataModel>>(schedulersProvider) {

    override fun buildInteractorObservables(params: MutableMap<String, String>): Observable<List<TripDataModel>> {
        return repository.getTrips(params)
    }

}
