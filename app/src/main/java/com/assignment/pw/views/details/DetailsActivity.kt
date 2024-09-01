package com.assignment.pw.views.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.assignment.pw.ui.theme.PWTheme
import com.assignment.pw.views.details.DetailsUI.DetailUI
import com.assignment.pw.views.listing.ListingViewModel
import com.assignment.pw.views.listing.ListingViewModelFactory

class DetailsActivity : ComponentActivity() {
    lateinit var model: DetailsViewModel
    lateinit var factory: DetailsViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        init()
        setContent {
            PWTheme {
                DetailUI(model){
                    finish()
                }
            }
        }
    }

    fun init() {
        val id=intent.getIntExtra("id",0)
        factory = DetailsViewModelFactory(id)
        model = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
    }
}