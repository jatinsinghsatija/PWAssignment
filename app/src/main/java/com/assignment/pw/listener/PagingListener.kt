package com.assignment.pw.listener

import com.assignment.pw.model.ListModel
import com.google.gson.JsonObject

interface PagingListener {
    fun getData(page:Int):ListModel?
}