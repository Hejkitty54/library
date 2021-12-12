package com.example.library

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.library.model.Book

class BooksAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val booksList: MutableList<Book> = mutableListOf<Book>()

    fun init(bookList: MutableList<Book>) {
        this.booksList.clear()
        this.booksList.addAll(bookList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookHolder) holder.bind(booksList[position])
    }

    override fun getItemCount(): Int = booksList.size
}