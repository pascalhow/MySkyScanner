package com.pascalhow.myskyscanner.activities.main

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import com.pascalhow.myskyscanner.R
import com.pascalhow.myskyscanner.activities.flights.FlightDetailsContract
import com.pascalhow.myskyscanner.activities.flights.FlightDetailsInteractor
import com.pascalhow.myskyscanner.activities.flights.FlightDetailsPresenter
import com.pascalhow.myskyscanner.activities.flights.TripViewModel
import com.pascalhow.myskyscanner.activities.search.FlightsCriteria
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
        flightDetailsInteractor = FlightDetailsInteractor()
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

        flightDetailsCountTextView.text = "365 or 366 results"
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

    override fun loadFlightsList(tripsList: List<TripViewModel>) {
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

    class TripsAdapter : RecyclerView.Adapter<TripsAdapter.TripsViewHolder>() {

        private var tripDataSet: List<TripViewModel> = ArrayList()

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TripsViewHolder {
            val flightDetailsItemView = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_item_trips_details, viewGroup, false)

            return TripsViewHolder(
                flightDetailsItemView
            )
        }

        fun setItemList(tripsList: List<TripViewModel>) {
            tripDataSet = tripsList
            notifyDataSetChanged()
        }

        override fun onBindViewHolder(viewHolder: TripsViewHolder, position: Int) {
            val trips = tripDataSet[position]
            viewHolder.apply {
                outboundTime.text = trips.outboundTime
                outboundAirline.text = trips.outboundAirline
                outboundFlightType.text = trips.outboundFlightType
                outboundFlightDuration.text = trips.outboundFlightDuration
                inboundTime.text = trips.inboundTime
                inboundAirline.text = trips.inboundAirline
                inboundFlightType.text = trips.inboundFlightType
                inboundFlightDuration.text = trips.inboundFlightDuration
                rating.text = trips.rating
                price.text = trips.price
                airlineUrl.text = trips.airlineUrl
            }
        }

        override fun getItemCount(): Int {
            return tripDataSet.size
        }

        class TripsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var outboundTime: TextView = itemView.findViewById(R.id.outbound_flight_time)
            var outboundAirline: TextView = itemView.findViewById(R.id.outbound_flight_airline)
            var outboundFlightType: TextView = itemView.findViewById(R.id.outbound_flight_type)
            var outboundFlightDuration: TextView = itemView.findViewById(R.id.outbound_flight_duration)
            var inboundTime: TextView = itemView.findViewById(R.id.inbound_flight_time)
            var inboundAirline: TextView = itemView.findViewById(R.id.inbound_flight_airline)
            var inboundFlightType: TextView = itemView.findViewById(R.id.inbound_flight_type)
            var inboundFlightDuration: TextView = itemView.findViewById(R.id.inbound_flight_duration)
            var rating: TextView = itemView.findViewById(R.id.flight_rating)
            var price: TextView = itemView.findViewById(R.id.price)
            var airlineUrl: TextView = itemView.findViewById(R.id.airline_url)
        }
    }

}
