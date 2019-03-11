package com.pascalhow.myskyscanner.activities.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pascalhow.myskyscanner.R
import com.pascalhow.myskyscanner.activities.flights.FlightDetailsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.root_layout, FlightDetailsFragment.newInstance(),
                    FRAGMENT_FLIGHT_DETAILS_LIST
                )
                .commit()
        }
    }

    companion object {
        const val FRAGMENT_FLIGHT_DETAILS_LIST = "fragment flight details list"
    }
}
