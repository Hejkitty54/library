package com.example.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.library.databinding.ItemBookBinding
import com.example.library.model.Book

class BookHolder(private val binding: ItemBookBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book) {
        binding.bookTitle.text = book.title
        binding.bookAuthor.text = book.author
    }

    companion object {
        fun create(
            parent: ViewGroup
        ): BookHolder {
            return BookHolder(
                ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }
}