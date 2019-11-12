package com.preetika.usample.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.preetika.usample.model.Model
import com.preetika.usample.network.ApiCall
import com.preetika.usample.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CvRepository {

    private val apiCall: ApiCall=
        RetrofitClient.cteateService(ApiCall::class.java)


    fun getCvDetails(): MutableLiveData<Model> {
        val cvData = MutableLiveData<Model>()
        apiCall.getCvData().enqueue(object : Callback<Model> {
            override fun onResponse(call: Call<Model>,
                                    response: Response<Model>
            ) {
                if (response.isSuccessful) {
                    Log.e("abc", ""+response.body().toString())
                    cvData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                cvData.value = null
            }
        })
        return cvData
    }

    companion object {

        private var cvRepository: CvRepository ? = null

        val instance: CvRepository
            get() {
                if (cvRepository == null) {
                    cvRepository = CvRepository()
                }
                return this.cvRepository as CvRepository
            }
    }
}