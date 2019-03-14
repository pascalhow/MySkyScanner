package com.pascalhow.myskyscanner.activities.flights

import android.util.Log
import com.pascalhow.myskyscanner.utils.SchedulersProvider
import io.reactivex.disposables.Disposable

class FlightDetailsPresenter(
    var view: FlightDetailsContract.View?,
    val interactor: FlightDetailsInteractor,
    val schedulersProvider: SchedulersProvider
) : FlightDetailsContract.Presenter {

    private var disposable: Disposable? = null

    override fun startPresenting() {
        view?.hideLoading()
    }

    override fun search(flightCriteriaParameters: MutableMap<String, String>) {
        view?.showLoading()

        disposable = interactor.getTripDataModel(flightCriteriaParameters)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.mainThread())
            .subscribe(
                { dataModelList ->
                    view?.loadFlightsList(getTripViewModelList(dataModelList))
                },
                { error ->
                    view?.hideLoading()
                    Log.e("Error Fetching Trips!!!", error.message)
                },
                {
                    view?.hideLoading()
                    Log.d("Updates Complete", "Finished Fetching Trips")
                }
            )
    }

    private fun getTripViewModelList(tripDataModelList: List<TripDataModel>): List<TripViewModel> {
        val tripViewModelList = ArrayList<TripViewModel>()
        tripDataModelList.forEach { dataModel ->
            val tripViewModel = TripViewModel(
                outboundTime = "${dataModel.outboundFlight.departureTime} - ${dataModel.outboundFlight.arrivalTime}",
                outboundAirline = dataModel.outboundFlight.carrier,
                outboundFlightType = dataModel.outboundFlight.stops ?: "Direct",
                outboundFlightDuration = dataModel.outboundFlight.duration,
                inboundTime = "${dataModel.inboundFlight.departureTime} - ${dataModel.outboundFlight.arrivalTime}",
                inboundAirline = dataModel.inboundFlight.carrier,
                inboundFlightType = dataModel.inboundFlight.stops ?: "Direct",
                inboundFlightDuration = dataModel.inboundFlight.duration,
                rating = "10",
                price = dataModel.price,
                airlineUrl = "airline.com"
            )
            tripViewModelList.add(tripViewModel)
        }
        return tripViewModelList
    }

    override fun loadTrips() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopPresenting() {
        disposable?.dispose()
        view = null
    }

}
