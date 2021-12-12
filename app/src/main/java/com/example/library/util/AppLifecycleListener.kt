package com.example.library.util

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.library.MainActivityViewModel
import com.example.library.MainStateEvent

class AppLifecycleListener(private val viewModel: MainActivityViewModel) :
    DefaultLifecycleObserver {

    override fun onStart(owner: LifecycleOwner) { // app moved to foreground
        Log.d("LifecycleOwner", " app moved to foreground")
        viewModel.setStateEvent(MainStateEvent.GetBookEvents)
    }

    override fun onStop(owner: LifecycleOwner) { // app moved to background
        Log.d("LifecycleOwner", " app moved to background")
    }
}