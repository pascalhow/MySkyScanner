package com.pascalhow.myskyscanner.activities.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pascalhow.myskyscanner.R
import com.pascalhow.myskyscanner.activities.flights.FlightDetailsFragment
import com.pascalhow.myskyscanner.activities.flights.TripsPresenter
import com.pascalhow.myskyscanner.activities.flights.TripsViewModel
import com.pascalhow.myskyscanner.activities.search.FlightsSearch
import com.pascalhow.myskyscanner.rest.FlightResultsDataMapper
import com.pascalhow.myskyscanner.utils.SchedulersProvider
import com.pascalhow.myskyscanner.rest.RestClient
import io.reactivex.disposables.Disposable
import timber.log.Timber
import kotlinx.android.synthetic.main.activity_main.flights_search_btn as flightSearchButton

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null
    private lateinit var schedulersProvider: SchedulersProvider
    private lateinit var restClient: RestClient
    private lateinit var flightsSearchMap: MutableMap<String, String>

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

        val flightsSearch = FlightsSearch()

        flightsSearchMap = mutableMapOf(
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

        flightSearchButton.text = "Search Flights!"
        flightSearchButton.setOnClickListener {
            searchFlights()
        }
    }

    private fun searchFlights() {
        restClient.beginSearch(flightsSearchMap)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.mainThread())
            .subscribe(
                { response ->
                    val flightResultsDataParser = FlightResultsDataMapper(response)
                    val tripsPresenter = TripsPresenter(flightResultsDataParser)

                    val tripViewModelList = ArrayList<TripsViewModel>()
                    response.itineraries?.forEachIndexed { index, _ ->
                        tripViewModelList.add(tripsPresenter.getTripViewModel(
                            response.itineraries?.get(index)?.outboundLegId,
                            response.itineraries?.get(index)?.inboundLegId)
                        )
                    }

                    Timber.d(flightResultsDataParser.toString())
                },
                { error ->
                    Timber.e(error)
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
