package com.assignment.pw.utils

import android.app.Activity
import android.content.Intent
import com.assignment.pw.views.details.DetailsActivity
import com.google.gson.Gson
import com.google.gson.JsonObject

object Extensions {

    @JvmOverloads
    fun <T : Any> JsonObject.transform(classType: Class<T>): T? {
        try {
            val gson = Gson()
            return gson.fromJson(this, classType)
        }catch (e:Exception){
            return null
        }
    }

    fun Activity.gotoDetails(id: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id",id)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.startActivity(intent)
    }
}