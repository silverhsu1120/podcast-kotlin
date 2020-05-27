package com.example.podcast.model.service

import com.example.podcast.model.service.cast.ApiCast
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIService private constructor() {

    companion object {

        val apiCast: ApiCast = Holder.instance.retrofit.create(ApiCast::class.java)
    }

    private object Holder {
        val instance = APIService()
    }

    private val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder()
        client.addInterceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }

        val gson = GsonBuilder().serializeNulls().create()
        Retrofit.Builder()
            .client(client.build())
            .baseUrl("http://demo4491005.mockable.io/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
