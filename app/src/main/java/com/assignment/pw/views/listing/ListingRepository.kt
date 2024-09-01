package com.assignment.pw.views.listing

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.assignment.pw.model.ItemModel
import com.assignment.pw.model.ListModel
import com.assignment.pw.utils.Extensions.transform
import com.headway.mybzb.api.ApiInterface

class ListingRepository(val api: ApiInterface,val model:ListingViewModel) : PagingSource<Int, ItemModel>() {

    /*override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemModel> {
        val page = params.key ?: 1
        return try {
            val response = api.getData(page)
            LoadResult.Page(
                data = response.results?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isNullOrEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }*/

    private var dynamicPageSize: Int = 20 // Default value

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemModel> {
        val page = params.key ?: 1
        return try {
            if(page==1)model.loading.value=true
            val response = api.listAPI(page).await()
            val apiResponse = response.transform(ListModel::class.java)
            if(page==1)model.loading.value=false
            // Determine the page size from the initial response
            if (page == 1 && apiResponse != null) {
                dynamicPageSize = apiResponse.info?.pages?:1
            }

            val data = apiResponse?.results ?: emptyList()
            val nextPage = if (page < (apiResponse?.info?.pages ?: 0)) page + 1 else null

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemModel>): Int? {
        return state.anchorPosition
    }
}