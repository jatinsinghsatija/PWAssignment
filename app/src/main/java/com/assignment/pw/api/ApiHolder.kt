package com.headway.mybzb.api

import com.google.gson.JsonObject

object ApiHolder {

    var apiService = RetrofitUtil.createBaseApiService()

    suspend fun detailAPI(id:Int): UseCaseResult<JsonObject> {
        return try {
            val result = apiService.detailAPI("character/$id").await()
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }
}