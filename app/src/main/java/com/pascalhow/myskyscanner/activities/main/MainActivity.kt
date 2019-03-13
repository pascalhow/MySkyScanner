package com.pascalhow.myskyscanner.activities.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import android.widget.TextView
import com.pascalhow.myskyscanner.R
import com.pascalhow.myskyscanner.activities.flights.FlightDetails
import com.pascalhow.myskyscanner.activities.flights.TripsPresenter
import com.pascalhow.myskyscanner.activities.flights.TripsViewModel
import com.pascalhow.myskyscanner.activities.search.FlightsSearch
import com.pascalhow.myskyscanner.rest.FlightResultsDataMapper
import com.pascalhow.myskyscanner.rest.RestClient
import com.pascalhow.myskyscanner.utils.SchedulersProvider
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    private lateinit var flightsSearch: FlightsSearch
    private lateinit var toolbar: Toolbar
    private var disposable: Disposable? = null
    private lateinit var schedulersProvider: SchedulersProvider
    private lateinit var restClient: RestClient
    private lateinit var flightsSearchParameters: MutableMap<String, String>
    private lateinit var flightDetailsCount: TextView
    private lateinit var sortAndFilter: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        flightsSearch = FlightsSearch()
        restClient = RestClient
        schedulersProvider = SchedulersProvider()

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

        flightDetailsCount = findViewById(R.id.flight_details_results_count)
        flightDetailsCount.text = "365 or 366 results"

        sortAndFilter = findViewById(R.id.sort_and_filter)
        sortAndFilter.text = "SORT & FILTER"

        (findViewById<RecyclerView>(R.id.flight_details_recycler_view)).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = FlightDetailsAdapter(
                listOf(
                    FlightDetails(),
                    FlightDetails(),
                    FlightDetails(),
                    FlightDetails(),
                    FlightDetails(),
                    FlightDetails(),
                    FlightDetails(),
                    FlightDetails(),
                    FlightDetails()
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return when (id) {
            R.id.action_search -> {
                searchFlights(flightsSearch)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun searchFlights(flightSearch: FlightsSearch) {
        disposable= restClient.getSessionUrl(flightsSearchParameters)
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

    class FlightDetailsAdapter(
        private val flightDataSet: List<FlightDetails>
    ) : RecyclerView.Adapter<FlightDetailsAdapter.FlightDetailsViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FlightDetailsViewHolder {
            val flightDetailsItemView = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_item_flight_details, viewGroup, false)

            return FlightDetailsViewHolder(
                flightDetailsItemView
            )
        }

        override fun onBindViewHolder(viewHolder: FlightDetailsViewHolder, position: Int) {
            val flightDetails = flightDataSet[position]
            viewHolder.apply {
                outboundTime.text = flightDetails.outboundTime
                outboundAirline.text = flightDetails.outboundAirline
                outboundFlightType.text = flightDetails.outboundFlightType
                outboundFlightDuration.text = flightDetails.outboundFlightDuration
                inboundTime.text = flightDetails.inboundTime
                inboundAirline.text = flightDetails.inboundAirline
                inboundFlightType.text = flightDetails.inboundFlightType
                inboundFlightDuration.text = flightDetails.inboundFlightDuration
                rating.text = flightDetails.rating
                price.text = flightDetails.price
                airlineUrl.text = flightDetails.airlineUrl
            }
        }

        override fun getItemCount(): Int {
            return flightDataSet.size
        }

        class FlightDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    companion object {
        const val FRAGMENT_FLIGHT_DETAILS_LIST = "fragment flight details list"
    }
}
