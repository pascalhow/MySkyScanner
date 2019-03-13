package com.pascalhow.myskyscanner.activities.flights

interface FlightDetailsContract {

    interface View{
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun startPresenting()
        fun loadTrips()
        fun stopPresenting()
    }
}
