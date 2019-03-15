package com.pascalhow.myskyscanner.activities.toolbar

import com.nhaarman.mockito_kotlin.whenever
import com.pascalhow.myskyscanner.activities.flights.FlightsCriteria
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.modules.junit4.PowerMockRunner
import java.time.LocalDate
import java.time.Month
import java.util.Calendar
import java.util.Date

@RunWith(PowerMockRunner::class)
class ToolBarPresenterTest {

    @Mock
    lateinit var view: ToolbarContract.View

    private lateinit var calendar: Calendar
    private lateinit var flightsCriteria: FlightsCriteria
    private lateinit var toolBarPresenter: ToolBarPresenter
    private lateinit var today: Date

    private inline fun <reified T : Any> powerMock(): T = PowerMockito.mock(T::class.java)

    @Before
    fun setUp() {
        initMocks(this)

        mockStatic(Calendar::class.java)
        this.calendar = powerMock()

        flightsCriteria = FlightsCriteria()
        toolBarPresenter = ToolBarPresenter(view)


    }

    @Test
    fun `GIVEN activity created WHEN start presenting THEN display toolbar title and toolbar subtitle`() {
        toolBarPresenter.startPresenting()

        verify(view).displayToolbarTitle()
        verify(view).displayToolbarSubtitle()
    }

    @Test
    fun `GIVEN date is today WHEN build subtitle THEN subtitle shows next Monday and following day`() {
        val nextMonday = LocalDate.of(NEXT_MONDAY_YEAR, NEXT_MONDAY_MONTH, NEXT_MONDAY_DAY)
        val followingDay = LocalDate.of(FOLLOWING_DAY_YEAR, FOLLOWING_DAY_MONTH, FOLLOWING_DAY_DAY)

        calendar.run {
            whenever(get(Calendar.DAY_OF_WEEK)).thenReturn(TODAY_DAY)
        }


        toolBarPresenter.buildSubtitle()
    }

    companion object {
        private const val TODAY_YEAR = 2019
        private val TODAY_MONTH = Month.MARCH
        private const val TODAY_DAY = 2

        private const val NEXT_MONDAY_YEAR = 2019
        private val NEXT_MONDAY_MONTH = Month.MARCH
        private const val NEXT_MONDAY_DAY = 2

        private const val FOLLOWING_DAY_YEAR = 2019
        private val FOLLOWING_DAY_MONTH = Month.MARCH
        private const val FOLLOWING_DAY_DAY = 3
    }
}
