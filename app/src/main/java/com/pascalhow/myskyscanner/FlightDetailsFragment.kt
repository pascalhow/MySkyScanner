package com.pascalhow.myskyscanner

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pascalhow.myskyscanner.model.FlightDetails

class FlightDetailsFragment : Fragment() {

    lateinit var flightDetailsCount: TextView
    lateinit var sortAndFilter: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_flight_details, container, false)
        val activity = activity

        flightDetailsCount = view.findViewById(R.id.flight_details_results_count)
        flightDetailsCount.text = "365 or 366 results"

        sortAndFilter = view.findViewById(R.id.sort_and_filter)
        sortAndFilter.text = "SORT & FILTER"

        (view.findViewById(R.id.flight_details_recycler_view) as RecyclerView).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = FlightDetailsAdapter(
                listOf(FlightDetails(), FlightDetails(), FlightDetails(),FlightDetails(), FlightDetails(), FlightDetails(),FlightDetails(), FlightDetails(), FlightDetails())
            )
        }

        return view
    }

    companion object {
        fun newInstance(): FlightDetailsFragment {
            return FlightDetailsFragment()
        }
    }

    class FlightDetailsAdapter(
        private val flightDataSet: List<FlightDetails>
    ) : RecyclerView.Adapter<FlightDetailsAdapter.FlightDetailsViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FlightDetailsViewHolder {
            val flightDetailsItemView = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.recycler_item_flight_details, viewGroup, false)

            return FlightDetailsViewHolder(flightDetailsItemView)
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

}
