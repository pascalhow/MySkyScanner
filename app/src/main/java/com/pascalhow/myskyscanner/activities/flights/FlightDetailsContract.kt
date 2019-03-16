package com.pascalhow.myskyscanner.activities.flights

interface FlightDetailsContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun loadFlightsList(tripsList: ArrayList<TripViewModel>)
    }

    interface Presenter {
        fun startPresenting()
        fun search(flightCriteriaParameters: MutableMap<String, String>)
        fun stopPresenting()
    }
}
