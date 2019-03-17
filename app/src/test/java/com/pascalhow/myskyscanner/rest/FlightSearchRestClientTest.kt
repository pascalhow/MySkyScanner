package com.pascalhow.myskyscanner.rest

import com.pascalhow.myskyscanner.activities.flights.FlightsCriteria
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class FlightSearchRestClientTest {

    @Mock
    private lateinit var skyScannerApiService: SkyScannerApiService

    private lateinit var restClient: RestClient
    private lateinit var flightsCriteria: FlightsCriteria
    private lateinit var flightsCriteriaParameters: MutableMap<String, String>


    @Before
    fun setUp() {
        initMocks(this)

        flightsCriteria = FlightsCriteria()
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

        restClient = FlightSearchRestClient
    }

    @Test
    fun `WHEN creating session THEN get session url`() {
//        whenever(skyScannerApiService.createSession(flightsCriteriaParameters)).thenReturn(Observable.just(any()))
//
//        restClient.getSessionUrl(flightsCriteriaParameters)
//            .test()
//            .assertValue(anyString())
    }

    companion object {
        private const val FLIGHT_IMAGEURL = "imageUrl"
        private const val FLIGHT_DEPARTURE_TIME = "2019-05-30T09:35:00"
        private const val FLIGHT_ARRIVAL_TIME = "2019-05-30T11:20:00"
        private const val FLIGHT_ORIGIN = "EDI"
        private const val FLIGHT_DESTINATION = "LOND"
        private const val FLIGHT_CARRIER = "carrier"
        private const val FLIGHT_STOPS = "stops"
        private const val FLIGHT_DURATION = "90"
        private const val FLIGHT_PRICE = "price"
    }

}
