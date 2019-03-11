package com.pascalhow.myskyscanner.rest

import com.google.gson.GsonBuilder
import com.pascalhow.myskyscanner.activities.search.FlightsSearch
import com.pascalhow.myskyscanner.rest.model.Currencies
import com.pascalhow.myskyscanner.rest.model.FlightsResult
import com.pascalhow.myskyscanner.rest.model.Itineraries
import com.pascalhow.myskyscanner.rest.model.ServiceQuery
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




interface SkyScannerApiService {

    @FormUrlEncoded
    @POST("pricing/v1.0")
    fun request(
        @Field("country") country: String,
        @Field("currency") currency: String,
        @Field("locale") locale: String,
        @Field("originPlace") originPlace: String,
        @Field("destinationPlace") destinationPlace: String,
        @Field("outboundDate") outboundDate: String,
        @Field("inboundDate") inboundDate: String,
        @Field("adults") adults: String,
        @Field("apiKey") apiKey: String,
        @Field("locationSchema") locationSchema: String
    ): Observable<Response<Unit>>

    companion object {

        private const val SKYSCANNER_BASE_URL = "http://partners.api.skyscanner.net/apiservices/"

        fun create(): SkyScannerApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SKYSCANNER_BASE_URL)
                .build()

            return retrofit.create(SkyScannerApiService::class.java)
        }
    }
}
