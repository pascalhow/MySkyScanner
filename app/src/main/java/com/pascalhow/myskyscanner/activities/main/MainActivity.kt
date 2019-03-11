package com.pascalhow.myskyscanner.activities.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pascalhow.myskyscanner.R
import com.pascalhow.myskyscanner.activities.flights.FlightDetailsFragment
import com.pascalhow.myskyscanner.activities.search.FlightsSearch
import com.pascalhow.myskyscanner.rest.SkyScannerApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.flights_search_btn as flightSearchButton

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    private val skyScannerApiService by lazy {
        SkyScannerApiService.create()
    }

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

        flightSearchButton.text = "Search Flights!"
        flightSearchButton.setOnClickListener {
            beginSearch()
        }
    }

    private fun beginSearch() {
        val flightsSearch = FlightsSearch()

        disposable = skyScannerApiService.createSession(
            flightsSearch.country,
            flightsSearch.currency,
            flightsSearch.locale,
            flightsSearch.originPlace,
            flightsSearch.destinationPlace,
            flightsSearch.outboundDate,
            flightsSearch.inboundDate,
            flightsSearch.adults,
            flightsSearch.apiKey,
            flightsSearch.locationSchema
        ).flatMap { response ->
            val sessionUrl = response.headers().get("Location")
            skyScannerApiService.request(sessionUrl!!, flightsSearch.apiKey)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
