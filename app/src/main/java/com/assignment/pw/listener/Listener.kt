package com.assignment.pw.listener

import com.assignment.pw.model.ListModel
import com.google.gson.JsonObject

interface Listener {

    fun onAPISuccess(result: JsonObject){}

    fun onAPIFailure(e:Exception){}

    fun onItemClick(id:Int){}
}