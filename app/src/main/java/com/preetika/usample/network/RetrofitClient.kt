package com.preetika.usample.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Preetika Kaur
 * class returning retrofit object
 */

object RetrofitClient {

    /*private var mRetrofit: Retrofit? = null
    private val BASE_URL = "https://api.myjson.com/"
    val client: Retrofit
        get() {
            if (mRetrofit == null) {
                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return mRetrofit!!
        }*/

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.myjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun <S> cteateService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}