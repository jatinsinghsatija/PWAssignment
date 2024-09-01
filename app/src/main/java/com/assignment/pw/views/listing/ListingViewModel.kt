package com.assignment.pw.views.listing

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.assignment.pw.model.ItemModel
import com.headway.mybzb.api.ApiHolder
import kotlinx.coroutines.flow.Flow

class ListingViewModel : ViewModel() {

    val pagedData: Flow<PagingData<ItemModel>> = Pager(
        config = PagingConfig(pageSize = 42, enablePlaceholders = false),
        pagingSourceFactory = { ListingRepository(ApiHolder.apiService,this) }
    ).flow.cachedIn(viewModelScope)

    val loading = mutableStateOf(false)


}