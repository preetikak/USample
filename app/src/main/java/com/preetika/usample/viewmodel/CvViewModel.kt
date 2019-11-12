package com.preetika.usample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.preetika.usample.model.Model
import com.preetika.usample.repository.CvRepository

class CvViewModel: ViewModel(){

    private var mutableLiveData: MutableLiveData<Model>? = null
    private var cvRepository: CvRepository? = null

    fun init() {
        if (mutableLiveData != null) {
            return
        }
        cvRepository = CvRepository.instance
        mutableLiveData = cvRepository!!.getCvDetails()
    }

   /* fun getNewsRepository(): LiveData<Model>? {
        return mutableLiveData
    }*/

    fun getNewsRepository(): LiveData<Model>? {
        return CvRepository.instance.getCvDetails()
    }
}