package com.pascalhow.myskyscanner.activities.flights

import android.util.Log
import com.pascalhow.myskyscanner.utils.SchedulersProvider
import com.pascalhow.myskyscanner.utils.formatDuration
import com.pascalhow.myskyscanner.utils.formatTime
import io.reactivex.disposables.Disposable

class FlightDetailsPresenter(
    var view: FlightDetailsContract.View?,
    private val interactor: FlightDetailsInteractor,
    private val schedulersProvider: SchedulersProvider
) : FlightDetailsContract.Presenter {

    private var disposable: Disposable? = null

    override fun startPresenting() {
        view?.hideLoading()
    }

    override fun search(flightCriteriaParameters: MutableMap<String, String>) {
        view?.showLoading()

        disposable = interactor.getTripDataModelList(flightCriteriaParameters)
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

    private fun getTripViewModelList(tripDataModelList: List<TripDataModel>): ArrayList<TripViewModel> {
        val tripViewModelList = ArrayList<TripViewModel>()

        tripDataModelList.forEach { dataModel ->
            val outBoundImageUrl = dataModel.outboundFlight.imageUrl

            val outboundDepartureTime = dataModel.outboundFlight.departureTime?.formatTime(TIME_FORMAT)
            val outboundArrivalTime = dataModel.outboundFlight.arrivalTime?.formatTime(TIME_FORMAT)
            val outboundTime = "$outboundDepartureTime - $outboundArrivalTime"

            val outboundOrigin = dataModel.outboundFlight.origin
            val outboundDestination = dataModel.outboundFlight.destination
            val outboundAirline = "$outboundOrigin-$outboundDestination, ${dataModel.outboundFlight.carrier}"

            val outboundFlightType = dataModel.outboundFlight.stops ?: "Direct"
            val outboundFlightDuration = dataModel.outboundFlight.duration?.formatDuration(DURATION_FORMAT)

            val inBoundImageUrl = dataModel.inboundFlight.imageUrl

            val inboundDepartureTime = dataModel.inboundFlight.departureTime?.formatTime(TIME_FORMAT)
            val inboundArrivalTime = dataModel.inboundFlight.arrivalTime?.formatTime(TIME_FORMAT)
            val inboundTime = "$inboundDepartureTime - $inboundArrivalTime"

            val inboundOrigin = dataModel.inboundFlight.origin
            val inboundDestination = dataModel.inboundFlight.destination
            val inboundAirline = "$inboundOrigin-$inboundDestination, ${dataModel.inboundFlight.carrier}"

            val inboundFlightType = dataModel.inboundFlight.stops ?: "Direct"
            val inboundFlightDuration = dataModel.inboundFlight.duration?.formatDuration(DURATION_FORMAT)
            val rating = "10"
            val price = dataModel.price
            val airlineUrl = "airline.com"

            val tripViewModel = TripViewModel(
                outBoundImageUrl,
                outboundTime,
                outboundAirline,
                outboundFlightType,
                outboundFlightDuration,
                inBoundImageUrl,
                inboundTime,
                inboundAirline,
                inboundFlightType,
                inboundFlightDuration,
                rating,
                price,
                airlineUrl
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

    companion object {
        private const val TIME_FORMAT = "HH:mm"
        private const val DURATION_FORMAT = "%dh %02d"
    }

}
