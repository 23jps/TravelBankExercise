package com.jps.travelbankexercise.data.remote.response


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class TravelBankDataItem(
    @SerializedName("amount")
    @Expose
    var amount: Double?,
    @SerializedName("attachments")
    @Expose
    var attachments: List<Attachment?>?,
    @SerializedName("currencyCode")
    @Expose
    var currencyCode: String?,
    @SerializedName("date")
    @Expose
    var date: String?,
    @SerializedName("description")
    @Expose
    var description: String?,
    @SerializedName("expenseVenueTitle")
    @Expose
    var expenseVenueTitle: String?,
    @SerializedName("type")
    @Expose
    var type: String?
)