package com.pascalhow.myskyscanner.rest

import com.pascalhow.myskyscanner.rest.model.FlightsResult
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface SkyScannerApiService {

    @FormUrlEncoded
    @POST("pricing/v1.0")
    fun createSession(
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
    ): Observable<Response<Any>>

    @GET
    fun request(@Url sessionUrl: String, @Query("apiKey") apiKey: String): Observable<FlightsResult>

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
