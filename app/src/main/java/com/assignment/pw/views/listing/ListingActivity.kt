package com.assignment.pw.views.listing

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.assignment.pw.listener.Listener
import com.assignment.pw.listener.PagingListener
import com.assignment.pw.model.ListModel
import com.assignment.pw.ui.theme.PWTheme
import com.assignment.pw.utils.Extensions.gotoDetails
import com.assignment.pw.utils.Extensions.transform
import com.google.gson.JsonObject
import com.headway.mybzb.api.ApiHolder
import com.headway.mybzb.api.ApiInterface
import com.headway.mybzb.api.RetrofitUtil

class ListingActivity : ComponentActivity(),Listener {
    lateinit var model: ListingViewModel
    lateinit var factory: ListingViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContent {
            ListingUI.ListUI(model, this)
        }
    }

    fun init() {
        ApiHolder.apiService = RetrofitUtil.createBaseApiService(baseUrl = RetrofitUtil.BASE_URL)
        factory = ListingViewModelFactory()
        model = ViewModelProvider(this, factory).get(ListingViewModel::class.java)
    }

    override fun onItemClick(id: Int) {
        gotoDetails(id)
    }
}