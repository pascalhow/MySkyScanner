package com.pascalhow.myskyscanner.mapper

import com.pascalhow.myskyscanner.activities.flights.TripDataModel
import com.pascalhow.myskyscanner.activities.flights.TripViewModel

interface TripViewModelDataMapper {

    fun transform(tripDataModelList: List<TripDataModel>): ArrayList<TripViewModel>
}
