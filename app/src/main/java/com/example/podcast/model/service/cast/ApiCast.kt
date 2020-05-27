package com.example.podcast.model.service.cast

import com.example.podcast.model.bean.GetCastDetailRes
import com.example.podcast.model.bean.GetCastsRes
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiCast {

    @GET("getcasts")
    fun getCastList(): Observable<GetCastsRes>

    @GET("getcastdetail")
    fun getCastDetail(): Observable<GetCastDetailRes>
}