package com.example.uihomework.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uihomework.api.ApiService
import com.example.uihomework.api.RetrofitInstance
import com.example.uihomework.model.AirPollution
import com.example.uihomework.model.Record
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AirPollutionViewModel: ViewModel() {

    companion object {
        const val PM2_5_THRESHOLD: Int = 30
    }
    private var airPollution: AirPollution? = null
    private var highPollutionList: MutableLiveData<List<Record>> = MutableLiveData()
    private var lowPollutionList: MutableLiveData<List<Record>> = MutableLiveData()
    private var searchList: MutableLiveData<List<Record>> = MutableLiveData()

    fun getLowPollutionListObservable() : MutableLiveData<List<Record>> {
        return lowPollutionList
    }

    fun getHighPollutionListObservable() : MutableLiveData<List<Record>> {
        return highPollutionList
    }

    fun getSearchListObservable() : MutableLiveData<List<Record>> {
        return searchList
    }

    fun getAirPollution() {
        val retrofitInstance = RetrofitInstance.getInstance().create(ApiService::class.java)

        Log.d("", "getUsersList start")
        retrofitInstance.getUserList().enqueue(object : Callback<AirPollution> {
            override fun onFailure(call: Call<AirPollution>, t: Throwable) {
                Log.d("", "getUsersList fail call: $call t: $t")
                airPollution = null
            }

            override fun onResponse(call: Call<AirPollution>, response: Response<AirPollution>) {
                Log.d("", "getUsersList onResponse ${response.isSuccessful}")
                if(response.isSuccessful) {
                    Log.d("", "getUsersList isSuccessful")
                    val newLowPollutions = mutableListOf<Record>()
                    val newHighPollutions = mutableListOf<Record>()
                    airPollution = response.body()
                    airPollution?.records?.forEach {
                        if (it.pm25.isEmpty() || it.pm25.toFloat() < PM2_5_THRESHOLD) {
                            newLowPollutions.add(it)
                        } else {
                            newHighPollutions.add(it)
                        }
                    }
                    lowPollutionList.postValue(newLowPollutions)
                    highPollutionList.postValue(newHighPollutions)
                } else {
                    lowPollutionList.postValue(null)
                    highPollutionList.postValue(null)
                }
            }
        })
    }

    fun search(searchKey: String) {
        val searchResult = mutableListOf<Record>()
        airPollution?.records?.forEach {
            if (it.siteName.contains(searchKey) || it.county.contains(searchKey)) {
                searchResult.add(it)
            }
        }
        searchList.postValue(searchResult)
    }
}