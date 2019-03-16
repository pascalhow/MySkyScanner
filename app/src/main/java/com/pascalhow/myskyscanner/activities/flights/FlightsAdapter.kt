package com.pascalhow.myskyscanner.activities.flights

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.pascalhow.myskyscanner.R
import com.squareup.picasso.Picasso
import java.util.ArrayList

class FlightsAdapter : RecyclerView.Adapter<FlightsAdapter.TripsViewHolder>() {

    private var tripDataSet = ArrayList<TripViewModel>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TripsViewHolder {
        val flightDetailsItemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_item_trips_details, viewGroup, false)

        return TripsViewHolder(flightDetailsItemView)
    }

    fun setItemList(tripsList: ArrayList<TripViewModel>) {
        tripDataSet = tripsList
        notifyDataSetChanged()
    }

    fun clearItemList() {
        tripDataSet.clear()
    }

    override fun onBindViewHolder(viewHolder: TripsViewHolder, position: Int) {
        val trips = tripDataSet[position]

        viewHolder.apply {
            Picasso.get().load(trips.outboundImageUrl).resize(200, 200).centerInside().into(viewHolder.outboundImageLogo)
            outboundTime.text = trips.outboundTime
            outboundAirline.text = trips.outboundAirline
            outboundFlightType.text = trips.outboundFlightType
            outboundFlightDuration.text = trips.outboundFlightDuration
            Picasso.get().load(trips.inboundImageUrl).resize(200, 200).centerInside().into(viewHolder.inboundImageLogo)
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
        var outboundImageLogo: ImageView = itemView.findViewById(R.id.outbound_flight_logo)
        var outboundTime: TextView = itemView.findViewById(R.id.outbound_flight_time)
        var outboundAirline: TextView = itemView.findViewById(R.id.outbound_flight_airline)
        var outboundFlightType: TextView = itemView.findViewById(R.id.outbound_flight_type)
        var outboundFlightDuration: TextView = itemView.findViewById(R.id.outbound_flight_duration)
        var inboundImageLogo: ImageView = itemView.findViewById(R.id.inbound_flight_logo)
        var inboundTime: TextView = itemView.findViewById(R.id.inbound_flight_time)
        var inboundAirline: TextView = itemView.findViewById(R.id.inbound_flight_airline)
        var inboundFlightType: TextView = itemView.findViewById(R.id.inbound_flight_type)
        var inboundFlightDuration: TextView = itemView.findViewById(R.id.inbound_flight_duration)
        var rating: TextView = itemView.findViewById(R.id.flight_rating)
        var price: TextView = itemView.findViewById(R.id.price)
        var airlineUrl: TextView = itemView.findViewById(R.id.airline_url)
    }
}
