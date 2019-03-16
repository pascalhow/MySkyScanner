package com.pascalhow.myskyscanner.activities.flights

import com.nhaarman.mockito_kotlin.whenever
import com.pascalhow.myskyscanner.rest.FlightResultsDataMapper
import com.pascalhow.myskyscanner.rest.FlightSearchRestClient.ItinerariesMap
import com.pascalhow.myskyscanner.rest.RestClient
import com.pascalhow.myskyscanner.rest.model.Itineraries
import com.pascalhow.myskyscanner.utils.SchedulersProvider
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class FlightDetailsInteractorTest {

    @Mock
    private lateinit var schedulersProvider: SchedulersProvider

    @Mock
    private lateinit var restClient: RestClient

    private lateinit var flightsCriteria: FlightsCriteria
    private lateinit var itinerariesMap: ItinerariesMap
    private val dataMapper: FlightResultsDataMapper = FlightResultsDataMapper()
    private lateinit var testScheduler: TestScheduler
    private lateinit var flightsCriteriaParameters: MutableMap<String, String>
    private lateinit var interactor: FlightDetailsInteractor

    private var outboundFlight = Flight(
        FLIGHT_IMAGEURL,
        FLIGHT_DEPARTURE_TIME,
        FLIGHT_ARRIVAL_TIME,
        FLIGHT_ORIGIN,
        FLIGHT_DESTINATION,
        FLIGHT_CARRIER,
        FLIGHT_STOPS,
        FLIGHT_DURATION
    )

    private var inboundFlight = Flight(
        FLIGHT_IMAGEURL,
        FLIGHT_DEPARTURE_TIME,
        FLIGHT_ARRIVAL_TIME,
        FLIGHT_ORIGIN,
        FLIGHT_DESTINATION,
        FLIGHT_CARRIER,
        FLIGHT_STOPS,
        FLIGHT_DURATION
    )

    @Before
    fun setUp() {
        initMocks(this)

        testScheduler = TestScheduler()
        whenever(schedulersProvider.io()).thenReturn(testScheduler)
        whenever(schedulersProvider.mainThread()).thenReturn(testScheduler)

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

        itinerariesMap = ItinerariesMap(listOf(Itineraries()), dataMapper)
        interactor = FlightDetailsInteractor(restClient)
    }

    @Test
    fun `GIVEN flight parameters WHEN restClient is queried THEN output list of trip data models`() {
        givenSessionUrl(flightsCriteriaParameters)

        interactor.getDataModelList(flightsCriteriaParameters)
            .test()
            .assertValue(listOf(TripDataModel(outboundFlight, inboundFlight, FLIGHT_PRICE)))


    }

    private fun givenSessionUrl(parameters: MutableMap<String, String>) {
        whenever(restClient.getSessionUrl(parameters)).thenReturn(Observable.just(SESSION_URL))
        whenever(restClient.search(SESSION_URL, API_KEY)).thenReturn(Observable.just(itinerariesMap))
    }

    companion object {
        private const val SESSION_URL = "session url"
        private const val FLIGHT_IMAGEURL = "imageUrl"
        private const val FLIGHT_DEPARTURE_TIME = "2019-05-30T09:35:00"
        private const val FLIGHT_ARRIVAL_TIME = "2019-05-30T11:20:00"
        private const val FLIGHT_ORIGIN = "EDI"
        private const val FLIGHT_DESTINATION = "LOND"
        private const val FLIGHT_CARRIER = "carrier"
        private const val FLIGHT_STOPS = "stops"
        private const val FLIGHT_DURATION = "90"
        private const val FLIGHT_PRICE = "price"
        private const val API_KEY = "api key"
    }
}
