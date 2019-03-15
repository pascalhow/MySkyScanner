package com.pascalhow.myskyscanner.activities.toolbar

import com.pascalhow.myskyscanner.activities.flights.FlightsCriteria
import org.joda.time.DateTime
import org.joda.time.LocalDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class ToolBarPresenter(private var view: ToolbarContract.View?) : ToolbarContract.Presenter {

    var flightsCriteria = FlightsCriteria()
    private var today = LocalDate.now()
    private var nextMonday: Date? = null
    private var followingDay: Date? = null

    override fun startPresenting() {
        view?.displayToolbarTitle()
        view?.displayToolbarSubtitle()
    }

    override fun buildTitle(): String = "${flightsCriteria.originPlace} - ${flightsCriteria.destinationPlace}"

    override fun buildSubtitle(): String {
        updateFlightCriteria()

        val nextMondayDate = nextMonday!!.format(TOOLBAR_DATE_FORMAT)
        val followingDayDate = followingDay!!.format(TOOLBAR_DATE_FORMAT)

        return "$nextMondayDate - $followingDayDate, ${flightsCriteria.adults} adults, economy"
    }

    override fun stopPresenting() {
        view = null
    }

    private fun refreshSearchDates() {
        val now = Calendar.getInstance()
        val weekday = now.get(Calendar.DAY_OF_WEEK)
        if (weekday != Calendar.MONDAY) {
            val days = (Calendar.SATURDAY - weekday + DAYS_BETWEEN_SATURDAY_AND_MONDAY) % NUMBER_OF_DAYS_IN_WEEK
            now.add(Calendar.DAY_OF_YEAR, days)
        }
        nextMonday = now.time

        now.add(Calendar.DAY_OF_YEAR, 1)
        followingDay = now.time
    }

    private fun updateFlightCriteria() {
        refreshSearchDates()
        flightsCriteria.run {
            outboundDate = nextMonday!!.format(SEARCH_DATE_FORMAT)
            inboundDate = followingDay!!.format(SEARCH_DATE_FORMAT)
        }
    }

    private fun Date.format(format: String): String {
        val formatter = SimpleDateFormat(format, Locale.ENGLISH)
        return formatter.format(this)
    }

    companion object {
        private const val TOOLBAR_DATE_FORMAT = "dd MMM"
        private const val SEARCH_DATE_FORMAT = "yyyy-MM-dd"
        private const val DAYS_BETWEEN_SATURDAY_AND_MONDAY = 2
        private const val NUMBER_OF_DAYS_IN_WEEK = 7
    }
}
