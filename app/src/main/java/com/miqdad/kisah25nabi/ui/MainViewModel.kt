package com.miqdad.kisah25nabi.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miqdad.kisah25nabi.data.KisahResponse
import com.yoenas.kisah25nabi.data.network.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel: ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var isError = MutableLiveData<Throwable>()

    var kisahResponse = MutableLiveData<List<KisahResponse>>()

    fun getData(responseHandler : (List<KisahResponse>) -> Unit, errorHandler : (Throwable) -> Unit){
        ApiClient.getApiService().getKisahNabi()
                //membuat background thread / asnycronus
            .subscribeOn(Schedulers.io())
            //menentukan dimana thread akan dibuat
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            },{
                errorHandler(it)
            })
    }

    fun getKisahNabi(){
        isLoading.value = true
        getData({
            isLoading.value = false
            kisahResponse.value = it
        }, {
            isLoading.value = true
            isError.value = it
        })
    }
}