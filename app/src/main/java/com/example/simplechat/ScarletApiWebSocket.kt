package com.example.simplechat

import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.OkHttpClient

object ScarletApiWebSocket {

     fun getWebSocket(
         webSocketBaseUrl:String
    ): Scarlet {

        val okHttp = OkHttpClient.Builder()
            .build()

        return Scarlet.Builder()
            .webSocketFactory(
                okHttp.newWebSocketFactory(
                    webSocketBaseUrl
                )
            )
            .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
            .addMessageAdapterFactory(GsonMessageAdapter.Factory())
            .build()

    }


}