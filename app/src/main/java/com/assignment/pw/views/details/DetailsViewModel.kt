package com.assignment.pw.views.details

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.assignment.pw.listener.Listener
import com.assignment.pw.model.ItemModel
import com.assignment.pw.model.ListModel
import com.assignment.pw.utils.Extensions.transform
import com.google.gson.JsonObject

class DetailsViewModel(val id: Int? = 0):ViewModel() {
    val episodes = mutableStateListOf<String?>()
    val item = mutableStateOf<ItemModel?>(null)
    val loading = mutableStateOf(false)

    init {
        getDetails()
    }

    private fun getDetails() {
        if (item.value == null) {
            loading.value=true
            DetailsRepository.fetchDetails(id?:0, object : Listener {
                override fun onAPISuccess(result: JsonObject) {
                    loading.value=false
                    val model = result.transform(ItemModel::class.java)
                    model?.let {
                        item.value = it
                        it.episode?.let { episode->
                            episodes.addAll(episode)
                        }
                    }
                }

                override fun onAPIFailure(e: Exception) {
                    loading.value=false
                }
            })
        }
    }
}