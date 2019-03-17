package com.pascalhow.myskyscanner.activities.toolbar

import com.pascalhow.myskyscanner.activities.flights.FlightsCriteria
import java.text.SimpleDateFormat
import java.util.*


class ToolBarPresenter(private var view: ToolbarContract.View?) : ToolbarContract.Presenter {

    var flightsCriteria = FlightsCriteria()
    private var nextMonday: Date? = null
    private var followingDay: Date? = null

    override fun startPresenting() {
        view?.displayToolbarTitle()
        view?.displayToolbarSubtitle()
    }

    override fun buildTitle(): String = "${flightsCriteria.originPlace} - ${flightsCriteria.destinationPlace}"

    override fun buildSubtitle(calendar: Calendar): String {
        updateFlightCriteria(calendar)

        val nextMondayDate = nextMonday!!.format(TOOLBAR_DATE_FORMAT)
        val followingDayDate = followingDay!!.format(TOOLBAR_DATE_FORMAT)

        return "$nextMondayDate - $followingDayDate, ${flightsCriteria.adults} adults, economy"
    }

    override fun stopPresenting() {
        view = null
    }

    private fun refreshSearchDates(calendar: Calendar) {
        val weekday = calendar.get(Calendar.DAY_OF_WEEK)
        if (weekday != Calendar.MONDAY) {
            val days = (Calendar.SATURDAY - weekday + DAYS_BETWEEN_SATURDAY_AND_MONDAY) % NUMBER_OF_DAYS_IN_WEEK
            calendar.add(Calendar.DAY_OF_YEAR, days)
        }
        nextMonday = calendar.time

        calendar.add(Calendar.DAY_OF_YEAR, 1)
        followingDay = calendar.time
    }

    private fun updateFlightCriteria(calendar: Calendar) {
        refreshSearchDates(calendar)
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
