package com.assignment.pw.views.details

import com.assignment.pw.listener.Listener
import com.headway.mybzb.api.ApiHolder
import com.headway.mybzb.api.RetrofitUtil
import com.headway.mybzb.api.UseCaseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DetailsRepository {

    fun fetchDetails(id:Int,listener: Listener){
        CoroutineScope(Dispatchers.IO).launch {
            /*ApiHolder.apiService =
                RetrofitUtil.createBaseApiService(baseUrl = RetrofitUtil.BASE_URL)*/


            when (val result = ApiHolder.detailAPI(id)) {
                is UseCaseResult.Success -> {
                    withContext(Dispatchers.Main) {
                        listener.onAPISuccess(result.data)
                    }
                }

                is UseCaseResult.Error -> {
                    withContext(Dispatchers.Main) {
                        listener.onAPIFailure(result.exception)
                    }
                }

                else -> UseCaseResult.Empty
            }
        }

    }
}