package com.pascalhow.myskyscanner.activities.main

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.pascalhow.myskyscanner.R
import com.pascalhow.myskyscanner.activities.flights.*
import com.pascalhow.myskyscanner.activities.search.FlightsCriteria
import com.pascalhow.myskyscanner.rest.RestClient
import com.pascalhow.myskyscanner.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_main.flight_details_recycler_view as flightsRecyclerView
import kotlinx.android.synthetic.main.activity_main.flight_details_results_count as flightDetailsCountTextView
import kotlinx.android.synthetic.main.activity_main.progress_bar as progressBar
import kotlinx.android.synthetic.main.activity_main.sort_and_filter as sortAndFilterTextView

class MainActivity : AppCompatActivity(), FlightDetailsContract.View {

    private val flightsCriteria = FlightsCriteria()
    private lateinit var flightsCriteriaParameters: MutableMap<String, String>
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var tripsAdapter: TripsAdapter
    private lateinit var schedulersProvider: SchedulersProvider
    private lateinit var flightDetailsInteractor: FlightDetailsInteractor
    private lateinit var flightDetailsPresenter: FlightDetailsPresenter

    override fun onResume() {
        super.onResume()
        flightDetailsPresenter.startPresenting()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        flightsCriteriaParameters = mutableMapOf(
            "country" to flightsCriteria.country,
            "currency" to flightsCriteria.currency,
            "locale" to flightsCriteria.locale,
            "originPlace" to flightsCriteria.originPlace,
            "destinationPlace" to flightsCriteria.destinationPlace,
            "outboundDate" to flightsCriteria.outboundDate,
            "inboundDate" to flightsCriteria.inboundDate,
            "adults" to flightsCriteria.adults,
            "apiKey" to flightsCriteria.apiKey,
            "locationSchema" to flightsCriteria.locationSchema
        )

        schedulersProvider = SchedulersProvider()
        flightDetailsInteractor = FlightDetailsInteractor(RestClient)
        flightDetailsPresenter = FlightDetailsPresenter(this, flightDetailsInteractor, schedulersProvider)

        linearLayoutManager = LinearLayoutManager(this)
        tripsAdapter = TripsAdapter()
        flightsRecyclerView.layoutManager = linearLayoutManager
        flightsRecyclerView.adapter = tripsAdapter

        progressBar.indeterminateDrawable
            .setColorFilter(
                ContextCompat.getColor(this, R.color.colorPrimary),
                PorterDuff.Mode.SRC_IN
            )

        flightDetailsCountTextView.text = "365 or 365 results"
        sortAndFilterTextView.text = "SORT & FILTER"

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return when (id) {
            R.id.action_search -> {
                tripsAdapter.clearItemList()
                flightDetailsPresenter.search(flightsCriteriaParameters)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun loadFlightsList(tripsList: ArrayList<TripViewModel>) {
        tripsAdapter.setItemList(tripsList)
    }

    override fun showFlightsList() {
        flightsRecyclerView.visibility = View.VISIBLE
    }

    override fun hideFlightsList() {
        flightsRecyclerView.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        flightDetailsPresenter.stopPresenting()
    }

}
