package com.example.simplechat

import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.util.*

data class MessagePresenter(
    var createdAt: String? = null,
    var body: String? = null,
    var id: Int,
    var isMine: Boolean,
    var senderId: Int

) : IMessage {
    companion object {
        fun makeMineMessage(body: String) =
            MessagePresenter(
                createdAt = Date().toString(),
                body = body,
                isMine = true,
                id = 100,
                senderId = 1 ?: 0
            )

        fun makeTheirMessage(body: String) =
            MessagePresenter(
                createdAt = Date().toString(),
                body = body,
                isMine = false,
                id = 100,
                senderId =100
            )
    }

    override fun getId(): String {
        return id.toString()
    }

    override fun getText(): String {
        return body ?: ""
    }

    override fun getUser(): IUser {
        return MemberPresenter(senderId, isMine)
    }

    override fun getCreatedAt(): Date {
        return Date()
    }
}
