package com.example.podcast.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.podcast.model.bean.Cast
import com.example.podcast.model.service.APIService
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class CastListViewModel : BaseViewModel() {

    val casts = MutableLiveData<List<Cast>>()

    fun getCasts() {
        APIService.apiCast.getCastList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.data?.podcast?.let { list ->
                    casts.value = list
                }
            }, {
                Logger.d(it.message)
            })
            .addTo(disposable)
    }
}
