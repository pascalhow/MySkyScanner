package com.pascalhow.myskyscanner.activities.flights

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.pascalhow.myskyscanner.R
import com.pascalhow.myskyscanner.activities.toolbar.ToolBarPresenter
import com.pascalhow.myskyscanner.activities.toolbar.ToolbarContract
import com.pascalhow.myskyscanner.rest.FlightSearchRestClient
import com.pascalhow.myskyscanner.utils.FlightsSchedulersProvider
import com.pascalhow.myskyscanner.utils.SchedulersProvider
import com.pascalhow.myskyscanner.utils.setColour
import kotlinx.android.synthetic.main.activity_main.toolbar
import java.util.*
import kotlinx.android.synthetic.main.activity_main.flight_details_recycler_view as flightsRecyclerView
import kotlinx.android.synthetic.main.activity_main.flight_details_results_count as flightDetailsCountTextView
import kotlinx.android.synthetic.main.activity_main.progress_bar as progressBar
import kotlinx.android.synthetic.main.activity_main.sort_and_filter as sortAndFilterTextView


class FlightSearchActivity : AppCompatActivity(), FlightDetailsContract.View, ToolbarContract.View {

    private lateinit var flightsCriteriaParameters: MutableMap<String, String>
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var flightsAdapter: FlightsAdapter
    private lateinit var toolbarPresenter: ToolBarPresenter
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

        toolbarPresenter = ToolBarPresenter(this)
        toolbarPresenter.startPresenting()

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        schedulersProvider = FlightsSchedulersProvider()
        flightDetailsInteractor = FlightDetailsInteractor(FlightSearchRestClient)
        flightDetailsPresenter = FlightDetailsPresenter(this, flightDetailsInteractor, schedulersProvider)

        linearLayoutManager = LinearLayoutManager(this)
        flightsAdapter = FlightsAdapter()
        flightsRecyclerView.layoutManager = linearLayoutManager
        flightsRecyclerView.adapter = flightsAdapter

        progressBar.setColour(this, R.color.colorPrimary)

        flightDetailsCountTextView.text = getString(R.string.flight_details_count)
        sortAndFilterTextView.text = getString(R.string.flight_details_sort_and_filter)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return when (id) {
            R.id.action_search -> {
                flightsAdapter.clearItemList()

                flightsCriteriaParameters = mutableMapOf(
                    "country" to toolbarPresenter.flightsCriteria.country,
                    "currency" to toolbarPresenter.flightsCriteria.currency,
                    "locale" to toolbarPresenter.flightsCriteria.locale,
                    "originPlace" to toolbarPresenter.flightsCriteria.originPlace,
                    "destinationPlace" to toolbarPresenter.flightsCriteria.destinationPlace,
                    "outboundDate" to toolbarPresenter.flightsCriteria.outboundDate,
                    "inboundDate" to toolbarPresenter.flightsCriteria.inboundDate,
                    "adults" to toolbarPresenter.flightsCriteria.adults,
                    "apiKey" to toolbarPresenter.flightsCriteria.apiKey,
                    "locationSchema" to toolbarPresenter.flightsCriteria.locationSchema
                )

                flightDetailsPresenter.search(flightsCriteriaParameters)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun displayToolbarTitle() {
        toolbar.title = toolbarPresenter.buildTitle()
    }

    override fun displayToolbarSubtitle() {
        val calendar = Calendar.getInstance()
        toolbar.subtitle = toolbarPresenter.buildSubtitle(calendar)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun loadFlightsList(tripsList: ArrayList<TripViewModel>) {
        flightsAdapter.setItemList(tripsList)
    }

    override fun onPause() {
        super.onPause()
        toolbarPresenter.stopPresenting()
        flightDetailsPresenter.stopPresenting()
    }

}
