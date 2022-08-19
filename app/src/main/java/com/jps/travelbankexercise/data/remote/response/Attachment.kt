package com.jps.travelbankexercise.data.remote.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Attachment(
    @SerializedName("filename")
    @Expose
    var filename: String?,
    @SerializedName("hash")
    @Expose
    var hash: String?,
    @SerializedName("_id")
    @Expose
    var id: String?,
    @SerializedName("mime")
    @Expose
    var mime: String?,
    @SerializedName("original")
    @Expose
    var original: String?,
    @SerializedName("size")
    @Expose
    var size: Int?,
    @SerializedName("thumbnails")
    @Expose
    var thumbnails: Thumbnails?,
    @SerializedName("userId")
    @Expose
    var userId: String?
)