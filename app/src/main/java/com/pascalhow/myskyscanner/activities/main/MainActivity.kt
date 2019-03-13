package com.pascalhow.myskyscanner.activities.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.pascalhow.myskyscanner.R
import com.pascalhow.myskyscanner.activities.flights.FlightDetailsFragment
import com.pascalhow.myskyscanner.activities.flights.TripsPresenter
import com.pascalhow.myskyscanner.activities.flights.TripsViewModel
import com.pascalhow.myskyscanner.activities.search.FlightsSearch
import com.pascalhow.myskyscanner.rest.FlightResultsDataMapper
import com.pascalhow.myskyscanner.rest.RestClient
import com.pascalhow.myskyscanner.utils.SchedulersProvider
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.flights_search_btn as flightSearchButton


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private var disposable: Disposable? = null
    private lateinit var schedulersProvider: SchedulersProvider
    private lateinit var restClient: RestClient
    private lateinit var flightsSearchParameters: MutableMap<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

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

        flightsSearchParameters = mutableMapOf(
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
            searchFlights(flightsSearch)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return when (id) {
            R.id.action_search -> {
                Toast.makeText(this@MainActivity, "Action clicked", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun searchFlights(flightSearch: FlightsSearch) {
        restClient.getSessionUrl(flightsSearchParameters)
            .flatMap { url -> restClient.search(url, flightSearch) }
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.mainThread())
            .subscribe(
                { response ->
                    val dataMapper = FlightResultsDataMapper(response)
                    val tripsPresenter = TripsPresenter(dataMapper)

                    val tripViewModelList = ArrayList<TripsViewModel>()
                    response.itineraries?.forEachIndexed { index, _ ->
                        tripViewModelList.add(
                            tripsPresenter.getTripViewModel(
                                response.itineraries?.get(index)?.outboundLegId,
                                response.itineraries?.get(index)?.inboundLegId
                            )
                        )
                    }

                    Log.d("Flight Results", tripViewModelList[0].outboundFlight.toString())
                },
                { error ->
                    Log.e("Error Fetching Trips!!!", error.message)
                },
                {
                    Log.d("Updates Complete", "Finished Fetching Trips")
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
