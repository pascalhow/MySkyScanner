package com.pascalhow.myskyscanner.activities.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pascalhow.myskyscanner.R
import com.pascalhow.myskyscanner.activities.flights.FlightDetailsFragment
import com.pascalhow.myskyscanner.activities.search.FlightsSearch
import com.pascalhow.myskyscanner.activities.utils.SchedulersProvider
import com.pascalhow.myskyscanner.rest.RestClient
import com.pascalhow.myskyscanner.rest.SkyScannerApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.flights_search_btn as flightSearchButton

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null
    private lateinit var schedulersProvider: SchedulersProvider
    private lateinit var restClient: RestClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.root_layout, FlightDetailsFragment.newInstance(),
                    FRAGMENT_FLIGHT_DETAILS_LIST
                )
                .commit()
        }

        restClient = RestClient
        schedulersProvider = SchedulersProvider()

        flightSearchButton.text = "Search Flights!"
        flightSearchButton.setOnClickListener {
            searchFlights()
        }
    }

    private fun searchFlights() {
        val flightsSearch = FlightsSearch()

        val flightSearchMap = mutableMapOf(
            "country" to flightsSearch.country,
            "currency" to flightsSearch.currency,
            "locale" to flightsSearch.locale,
            "originPlace" to flightsSearch.originPlace,
            "destinationPlace" to flightsSearch.destinationPlace,
            "outboundDate" to flightsSearch.outboundDate,
            "inboundDate" to flightsSearch.inboundDate,
            "adults" to flightsSearch.adults,
            "apiKey" to flightsSearch.apiKey,
            "locationSchema" to flightsSearch.locationSchema
        )

        restClient.beginSearch(flightSearchMap)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.mainThread())
            .subscribe(
                { response ->
                    val location = response.currencies
                    Log.d("Success Session Token", location.toString())
                },
                { error ->
                    Log.d("Error Session Token", error.message)
                }
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    companion object {
        const val FRAGMENT_FLIGHT_DETAILS_LIST = "fragment flight details list"
    }
}
