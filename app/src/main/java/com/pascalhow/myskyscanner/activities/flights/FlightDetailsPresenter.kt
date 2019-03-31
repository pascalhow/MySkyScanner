package com.pascalhow.myskyscanner.activities.flights

import android.util.Log
import com.pascalhow.myskyscanner.mapper.TripViewModelDataMapper
import io.reactivex.observers.DisposableObserver

class FlightDetailsPresenter(
    private val dataMapper: TripViewModelDataMapper,
    private val interactor: FlightDetailsInteractor
) : FlightDetailsContract.Presenter {

    private var view: FlightDetailsContract.View? = null

    override fun setView(view: FlightDetailsContract.View) {
        this.view = view
    }

    override fun startPresenting() {
        view?.hideLoading()
    }

    override fun search(flightCriteriaParameters: MutableMap<String, String>) {
        view?.showLoading()

        interactor.execute(FlightDetailsObserver(), flightCriteriaParameters)
    }

    override fun stopPresenting() {
        interactor.dispose()
        view = null
    }

    private fun showFlightDetailsInView(dataModelList: List<TripDataModel>) {
        val tripsViewModelList = dataMapper.transform(dataModelList)
        view?.loadFlightsList(tripsViewModelList)
    }

    private fun showErrorMessage(error: Throwable) {
        view?.hideLoading()
        Log.e("Error Fetching Trips!!!", error.message)
    }

    private fun hideProgressBarLoading() {
        view?.hideLoading()
        Log.d("Updates Complete", "Finished Fetching Trips")
    }

    private inner class FlightDetailsObserver : DisposableObserver<List<TripDataModel>>() {

        override fun onComplete() {
            this@FlightDetailsPresenter.hideProgressBarLoading()
        }

        override fun onNext(tripDataModel: List<TripDataModel>) {
            this@FlightDetailsPresenter.showFlightDetailsInView(tripDataModel)
        }

        override fun onError(e: Throwable) {
            this@FlightDetailsPresenter.showErrorMessage(e)
        }

    }
}


