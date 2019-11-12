package com.preetika.usample.model


import com.google.gson.annotations.SerializedName

data class Skill(
    @SerializedName("languages")
    val languages: List<String>,
    @SerializedName("type")
    val type: String
)