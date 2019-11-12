package com.preetika.usample.network

import com.preetika.usample.model.Model
import retrofit2.Call
import retrofit2.http.GET

interface ApiCall {

    @GET("bins/1bh618")
    fun getCvData(): Call<Model>
}