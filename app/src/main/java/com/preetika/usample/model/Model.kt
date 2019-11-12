package com.preetika.usample.model


import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("data")
    val `data`: Data
)