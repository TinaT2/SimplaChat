package com.example.simplechat

import com.tinder.scarlet.Event
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface GetMessageWebSocketApiInterface {

    @Receive
    fun getMessage(): Flowable<String>

    @Receive
    fun receiveEvent(): Flowable<Event>
//    @Send
//    fun sendMessage(value: String)
}

interface SendMessageWebSocketApiInterface {
    @Send
    fun sendMessage(value: String)
}
object WebSocket {
//    fun getService() =
//        ScarletApiWebSocket.getWebSocket()
//            .create<MessageWebSocketApiInterface>()
    fun getService() =
        ScarletApiWebSocket.getWebSocket(webSocketBaseUrl = "wss://echo.websocket.org")
            .create<GetMessageWebSocketApiInterface>()
//    fun sendService() =
//        ScarletApiWebSocket.getWebSocket(webSocketBaseUrl = "wss://echo.websocket.org")
//            .create<SendMessageWebSocketApiInterface>()

}
