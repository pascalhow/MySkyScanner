package com.pascalhow.myskyscanner.activities.toolbar

import com.pascalhow.myskyscanner.activities.flights.FlightsCriteria
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import java.time.LocalDate
import java.time.Month
import java.util.*

class ToolBarPresenterTest {

    @Mock
    lateinit var view: ToolbarContract.View

    private val calendar = Calendar.getInstance()
    private lateinit var flightsCriteria: FlightsCriteria
    private lateinit var toolBarPresenter: ToolBarPresenter
    private lateinit var today: Date

    @Before
    fun setUp() {
        initMocks(this)

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

        val actualSubtitle = toolBarPresenter.buildSubtitle(calendar)
        val expectedSubtitle = "$nextMonday - $followingDay, 1 adults, economy"

        assertEquals(actualSubtitle, expectedSubtitle)
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
