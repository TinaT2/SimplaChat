package com.example.simplechat

import com.stfalcon.chatkit.commons.models.IUser

data class MemberPresenter(val id: Int, val isMine: Boolean) : IUser {
    companion object {
        const val IS_MINE = "0"
        const val NOT_IS_MINE = "1"
    }

    override fun getId(): String {
        return if (isMine)
            IS_MINE
        else
            NOT_IS_MINE
    }

    //todo-soon
    override fun getName(): String = "tt2"

    override fun getAvatar(): String =
        "https://walyou.com/wp-content/uploads//2012/08/homemade-wall-e.jpg"

}