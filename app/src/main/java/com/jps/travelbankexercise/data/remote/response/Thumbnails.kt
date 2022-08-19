package com.jps.travelbankexercise.data.remote.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Thumbnails(
    @SerializedName("full")
    @Expose
    var full: String?,
    @SerializedName("list")
    @Expose
    var list: String?
)