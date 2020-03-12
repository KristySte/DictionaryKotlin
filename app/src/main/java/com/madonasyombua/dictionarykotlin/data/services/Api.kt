package com.madonasyombua.dictionarykotlin.data.services

import com.madonasyombua.dictionarykotlin.BuildConfig
import com.madonasyombua.dictionarykotlin.data.response.BaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * @author madona
 * The Headers generally carry information for: Request and Response Body. Request Authorization.
 * The GET method is used to **read** (or retrieve) a representation of a resource. In the “happy” (or non-error) path,
 * GET returns a representation in XML or JSON and an HTTP
 * response code of 200 (OK). In an error case, it most often returns a 404 (NOT FOUND) or 400 (BAD REQUEST).
 * It is also important to note the Api keys need to be hidden
 * */

interface Api {

    //the api key should be secret
    @Headers(
        "x-rapidapi-key:${BuildConfig.API_KEY}",
        "Content-type:application/json"
    )

    @GET("/define")
    fun getDefinitionAsync(@Query("term") word: String): Deferred<BaseResponse>




}