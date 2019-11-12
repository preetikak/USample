package com.preetika.usample.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("education_bg")
    val educationBg: List<EducationBg>,
    @SerializedName("name")
    val name: String,
    @SerializedName("skills")
    val skills: List<Skill>,
    @SerializedName("summary")
    val summary: String
)