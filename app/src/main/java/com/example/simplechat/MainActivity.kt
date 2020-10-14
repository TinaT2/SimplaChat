package com.example.simplechat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessagesListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MessagesListAdapter<MessagePresenter>
    private val getService = WebSocket.getService()
    private val sendService = WebSocket.sendService()
     private var compositeDisposable =  CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUiComponent()
        initUiListener()
        initObservers()
    }

    private fun initUiComponent() {
        initAdapter()
    }

    private fun initUiListener() {
        input.setInputListener {
            adapter.addToStart(
                MessagePresenter.makeMineMessage(it.toString()),
                true
            )
            sendService.sendMessage(it.toString())
            true
        }
    }

    private fun initObservers() {
        compositeDisposable.add(getService.getMessage()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                adapter.addToStart(
                    MessagePresenter.makeTheirMessage(it.toString()),
                    true
                )
            },{
            }))
        compositeDisposable.add(getService.receiveEvent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                adapter.addToStart(
                    MessagePresenter.makeMineMessage(it.toString()),
                    true
                )
            },{
            }))
    }

    private fun initAdapter() {
        adapter = MessagesListAdapter<MessagePresenter>("0", ImageLoader { _, _, _ -> })
        messagesList.setAdapter(adapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}