package com.pascalhow.myskyscanner.activities.flights

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.pascalhow.myskyscanner.utils.SchedulersProvider
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class FlightDetailsPresenterTest {

    @Mock
    private lateinit var view: FlightDetailsContract.View

    @Mock
    private lateinit var interactor: Interactor

    @Mock
    private lateinit var schedulersProvider: SchedulersProvider

    private lateinit var testScheduler: TestScheduler
    private lateinit var flightsCriteria: FlightsCriteria
    private lateinit var presenter: FlightDetailsPresenter

    private lateinit var flightsCriteriaParameters: MutableMap<String, String>

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

    private var tripDataModel = TripDataModel(outboundFlight, inboundFlight, FLIGHT_PRICE)

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

        presenter = FlightDetailsPresenter(view, interactor, schedulersProvider)
    }

    @Test
    fun `GIVEN search triggered created WHEN start presenting THEN hide progressbar loading`() {
        givenTripData()

        presenter.search(flightsCriteriaParameters)
        testScheduler.triggerActions()

        verify(view).loadFlightsList(any())
    }

    private fun givenTripData() {
        whenever(interactor.getDataModelList(flightsCriteriaParameters)).thenReturn(Observable.just(listOf(tripDataModel)))
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
