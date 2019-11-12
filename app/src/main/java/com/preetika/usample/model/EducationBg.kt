package com.preetika.usample.model


import com.google.gson.annotations.SerializedName

data class EducationBg(
    @SerializedName("from")
    val from: String,
    @SerializedName("institute_name")
    val instituteName: String,
    @SerializedName("Major")
    val major: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("to")
    val to: String
)