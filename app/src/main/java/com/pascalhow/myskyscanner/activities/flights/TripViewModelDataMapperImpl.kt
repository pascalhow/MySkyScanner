package com.pascalhow.myskyscanner.activities.flights

import com.pascalhow.myskyscanner.mapper.TripViewModelDataMapper
import com.pascalhow.myskyscanner.utils.changeFormat
import com.pascalhow.myskyscanner.utils.formatDuration

class TripViewModelDataMapperImpl : TripViewModelDataMapper {

    override fun transform(tripDataModelList: List<TripDataModel>): ArrayList<TripViewModel> {
        val tripViewModelList = ArrayList<TripViewModel>()

        tripDataModelList.forEach { dataModel ->
            val outBoundImageUrl = dataModel.outboundFlight.imageUrl

            val outboundDepartureTime = dataModel.outboundFlight.departureTime?.changeFormat(
                OLD_TIME_FORMAT,
                NEW_TIME_FORMAT
            )
            val outboundArrivalTime = dataModel.outboundFlight.arrivalTime?.changeFormat(
                OLD_TIME_FORMAT,
                NEW_TIME_FORMAT
            )
            val outboundTime = "$outboundDepartureTime - $outboundArrivalTime"

            val outboundOrigin = dataModel.outboundFlight.origin
            val outboundDestination = dataModel.outboundFlight.destination
            val outboundAirline = "$outboundOrigin-$outboundDestination, ${dataModel.outboundFlight.carrier}"

            val outboundFlightType = dataModel.outboundFlight.stops ?: DIRECT
            val outboundFlightDuration = dataModel.outboundFlight.duration?.formatDuration(DURATION_FORMAT)

            val inBoundImageUrl = dataModel.inboundFlight.imageUrl

            val inboundDepartureTime = dataModel.inboundFlight.departureTime?.changeFormat(
                OLD_TIME_FORMAT,
                NEW_TIME_FORMAT
            )
            val inboundArrivalTime = dataModel.inboundFlight.arrivalTime?.changeFormat(
                OLD_TIME_FORMAT,
                NEW_TIME_FORMAT
            )
            val inboundTime = "$inboundDepartureTime - $inboundArrivalTime"

            val inboundOrigin = dataModel.inboundFlight.origin
            val inboundDestination = dataModel.inboundFlight.destination
            val inboundAirline = "$inboundOrigin-$inboundDestination, ${dataModel.inboundFlight.carrier}"

            val inboundFlightType = dataModel.inboundFlight.stops ?: DIRECT
            val inboundFlightDuration = dataModel.inboundFlight.duration?.formatDuration(DURATION_FORMAT)
            val rating = DUMMY_RATING
            val price = dataModel.price
            val airlineUrl = DUMMY_AIRLINE_URL

            val tripViewModel = TripViewModel(
                outBoundImageUrl,
                outboundTime,
                outboundAirline,
                outboundFlightType,
                outboundFlightDuration,
                inBoundImageUrl,
                inboundTime,
                inboundAirline,
                inboundFlightType,
                inboundFlightDuration,
                rating,
                price,
                airlineUrl
            )
            tripViewModelList.add(tripViewModel)
        }
        return tripViewModelList
    }

    companion object {
        private const val OLD_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
        private const val NEW_TIME_FORMAT = "HH:mm"
        private const val DURATION_FORMAT = "%dh %02d"
        private const val DUMMY_RATING = "10.0"
        private const val DUMMY_AIRLINE_URL = "via agent.com"
        private const val DIRECT = "Direct"
    }
}
