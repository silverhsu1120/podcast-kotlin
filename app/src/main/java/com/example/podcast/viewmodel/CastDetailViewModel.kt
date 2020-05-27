package com.example.podcast.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.podcast.model.bean.Content
import com.example.podcast.model.service.APIService
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class CastDetailViewModel : BaseViewModel() {

    val cover = MutableLiveData<String>()
    val album = MutableLiveData<String>()
    val artist = MutableLiveData<String>()
    val tracks = MutableLiveData<List<Content>>()

    fun getCastDetail() {
        APIService.apiCast.getCastDetail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cover.value = it.data?.collection?.artworkUrl100
                album.value = it.data?.collection?.collectionName
                artist.value = it.data?.collection?.artistName
                tracks.value = it.data?.collection?.contentFeed
            }, {
                Logger.d(it.message)
            })
            .addTo(disposable)
    }
}