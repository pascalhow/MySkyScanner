package com.pascalhow.myskyscanner.activities.flights

interface FlightDetailsContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showFlightsList()
        fun hideFlightsList()
        fun loadFlightsList(tripsList: List<TripViewModel>)
    }

    interface Presenter {
        fun startPresenting()
        fun search(flightCriteriaParameters: MutableMap<String, String>)
        fun loadTrips()
        fun stopPresenting()
    }
}
