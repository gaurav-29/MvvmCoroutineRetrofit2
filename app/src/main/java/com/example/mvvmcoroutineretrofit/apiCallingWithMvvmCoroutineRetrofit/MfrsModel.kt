package com.example.mvvmcoroutineretrofit.apiCallingWithMvvmCoroutineRetrofit

import com.google.gson.annotations.SerializedName

data class MfrsModel(
    @SerializedName("Count")
    var count: Int,
    @SerializedName("Message")
    var message: String,
    @SerializedName("Results")
    var results: ArrayList<Result>,
    @SerializedName("SearchCriteria")
    var searchCriteria: Any?
)

data class Result(
    @SerializedName("Country")
    var country: String,
    @SerializedName("Mfr_CommonName")
    var mfrCommonName: String?,
    @SerializedName("Mfr_ID")
    var mfrID: Int,
    @SerializedName("Mfr_Name")
    var mfrName: String,
    @SerializedName("VehicleTypes")
    var vehicleTypes: ArrayList<VehicleType>,
    var viewType: Int = 1
)

data class VehicleType(
    @SerializedName("IsPrimary")
    var isPrimary: Boolean,
    @SerializedName("Name")
    var name: String,
    var viewType: Int = 2
)