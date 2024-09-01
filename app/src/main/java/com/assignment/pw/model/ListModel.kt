package com.assignment.pw.model

import com.google.gson.annotations.SerializedName

data class ListModel(

	@field:SerializedName("results")
	val results: List<ItemModel>? = null,

	@field:SerializedName("info")
	val info: Info? = null
)

data class Info(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("pages")
	val pages: Int? = null,

	@field:SerializedName("prev")
	val prev: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null
)

