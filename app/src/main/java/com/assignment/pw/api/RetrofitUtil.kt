package com.headway.mybzb.api


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt


object RetrofitUtil {
    var BASE_URL =  "https://rickandmortyapi.com/api/"


    fun createBaseApiService(
        baseUrl: String = BASE_URL
    ): ApiInterface {
        val formattedBaseurl = if (baseUrl.lastOrNull().toString() == "/") {
            baseUrl.dropLast(1)
        } else {
            baseUrl
        }
        val requestTimeout = getRequestTimeout()
        return getRetrofitClient(
            getOkhttpClientBuilder(
                requestTimeout,
                requestTimeout,
                60
            ), "$formattedBaseurl/"
        ).create(ApiInterface::class.java)
    }


    fun getRequestTimeout(): Long {
        return 60L
    }

    private fun getRetrofitClient(
        okHttpClientBuilder: OkHttpClient.Builder,
        baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .client(okHttpClientBuilder.build())
        .baseUrl(baseUrl)
        .build()


    private fun getGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        gsonBuilder.registerTypeAdapter(
            Double::class.java,
            JsonSerializer<Double?> { src, typeOfSrc, context ->
                val value = (src!!).roundToInt()
                JsonPrimitive(value)
            })

        return gsonBuilder.create()
    }

    private fun getOkhttpClientBuilder(
        connectTimeoutInSec: Long,
        readTimeoutInSec: Long,
        writeTimeoutInSec: Long
    ): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
        okHttpClientBuilder.connectTimeout(connectTimeoutInSec, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(readTimeoutInSec, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(writeTimeoutInSec, TimeUnit.SECONDS)
        return okHttpClientBuilder
    }

}

