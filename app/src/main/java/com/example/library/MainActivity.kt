package com.example.library

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.library.databinding.ActivityMainBinding
import com.example.library.model.Book
import com.example.library.util.AppLifecycleListener
import com.example.library.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val viewModel: MainActivityViewModel by viewModels()
    private lateinit var booksAdapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            booksAdapter = BooksAdapter()
        }
        binding.recyclerView.adapter = booksAdapter

        viewModel.apply {
            viewModel.init(booksAdapter)
        }

        booksObservers()
        progressObservers()

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBookEvents)

        binding.text.setOnClickListener {
            viewModel.setStateEvent(MainStateEvent.GetBookEvents)
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifecycleListener(viewModel))
    }

    private fun booksObservers() {
        viewModel.books.observe(this, Observer {
            binding.text.text = it.size.toString()
        }

        )
    }

    private fun progressObservers() {
        viewModel.inProgress.observe(this, Observer {
            binding.progressbar.visibility = if (it == true) View.VISIBLE else View.GONE
            binding.recyclerView.visibility = if (it == true) View.INVISIBLE else View.VISIBLE
        })
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Book>> -> {
                    appendBookTitles(dataState.data)
                }
                is DataState.Error -> {
                    Log.d("error", "${dataState.exception}")

                }
                is DataState.Loading -> {

                }

            }
        }

        )
    }

    private fun appendBookTitles(books: List<Book>) {
        val bookTitle = books.get(0).title
        val count = books.size
        // val bookTitle2 = books.get(1).title
        Log.d("debug", "$bookTitle size is $count")
    }

    fun sequence() = runBlocking {
        println("Main program starts: ${Thread.currentThread().name}")

        val greetingOne = getGreeting()
        val greetingTwo = getGreeting2()

        println("The entire greeting is: ${greetingOne + greetingTwo}")

        println("Main program ends: ${Thread.currentThread().name}")
    }

    fun sequenceTime() = runBlocking {
        println("Main program starts: ${Thread.currentThread().name}")
        val time = measureTimeMillis {
            val greetingOne = getGreeting()
            val greetingTwo = getGreeting2()
            println("The entire greeting is:${greetingOne + greetingTwo}")
        }
        println("completed in $time ms")
        println("Main program ends: ${Thread.currentThread().name}")
    }

    fun main() = runBlocking {
        println("Main program starts: ${Thread.currentThread().name}")
        val time = measureTimeMillis {
            val greetingOne: Deferred<String> = async { getGreeting() }
            val greetingTwo: Deferred<String> = async { getGreeting2() }
            println("The entire greeting is: ${greetingOne.await() + greetingTwo.await()}")
        }
        println("completed in $time ms")
        println("Main program ends: ${Thread.currentThread().name}")
    }

    suspend fun getGreeting(): String {
        delay(2000L)
        Log.d("Debug", "getGreeting Thread: ${Thread.currentThread().name}")
        return "hello"
    }

    suspend fun getGreeting2(): String {
        delay(2000L)
        Log.d("Debug", "getGreeting2 Thread: ${Thread.currentThread().name}")
        return "world"
    }

    fun volley() {

        val url = "https://fakerapi.it/api/v1/texts?_quantity=1&_characters=300&_seed=1&_locale"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                Log.d("Debug", "Response is: ${response.toString()}")
            },
            { error ->
                // TODO: Handle error
            }
        )

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}

