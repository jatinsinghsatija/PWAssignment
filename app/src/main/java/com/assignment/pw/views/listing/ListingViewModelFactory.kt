package com.assignment.pw.views.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.pw.listener.Listener
import com.assignment.pw.views.listing.ListingViewModel
import com.headway.mybzb.api.ApiInterface


class ListingViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListingViewModel::class.java)) {
            return ListingViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}