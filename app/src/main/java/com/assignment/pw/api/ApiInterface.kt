package com.headway.mybzb.api

import com.assignment.pw.model.ListModel
import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {

    @GET("character")
    fun listAPI(@Query("page") page: Int): Deferred<JsonObject>

    @GET
    fun detailAPI(
        @Url url: String
    ): Deferred<JsonObject>
}