package com.example.library

import android.util.Log
import android.view.View
import androidx.hilt.Assisted
import androidx.lifecycle.*
import com.example.library.model.Book
import com.example.library.repository.MainRepository
import com.example.library.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private lateinit var booksAdapter: BooksAdapter

    private val _dataState: MutableLiveData<DataState<List<Book>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Book>>>
        get() = _dataState

    private val _books: MutableLiveData<List<Book>> = MutableLiveData()
    val books: LiveData<List<Book>>
        get() = _books

    private val _requestInProgress = MutableLiveData<Boolean>(true)
    val inProgress: LiveData<Boolean>
        get() = _requestInProgress

    /*val progressVisibility: LiveData<Int> = Transformations.map(_requestInProgress) {
        if (it == true) View.VISIBLE else View.GONE
    }*/

    fun init(booksAdapter: BooksAdapter) {
        this.booksAdapter = booksAdapter
    }

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetBookEvents -> {
                    _requestInProgress.value = true

                    /*mainRepository.getBook()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)*/

                    try {
                        val call1 = async {
                            mainRepository.getFirstBook()
                        }
                        val call2 = async {
                            mainRepository.getSecondBooks()
                        }
                        val call3 = async {
                            mainRepository.getThirdBooks()
                        }
                        val call4 = async {
                            mainRepository.getForthBooks()
                        }
                        val call5 = async {
                            mainRepository.getFifthBooks()
                        }

                        _books.value = call1.await() + call2.await() + call3.await() + call4.await() + call5.await()
                        val result = call1.await() + call2.await() + call3.await() + call4.await() + call5.await()
                        Log.d("Await", "${result}")
                        _requestInProgress.value = false

                        booksAdapter.init(books.value as MutableList<Book>)
                    } catch (e: Exception) {
                        Log.d("Error", "${e.message}")
                    }
                }
                is MainStateEvent.None -> {}
            }
        }
    }

}


sealed class MainStateEvent {
    object GetBookEvents : MainStateEvent()
    object None : MainStateEvent()
}