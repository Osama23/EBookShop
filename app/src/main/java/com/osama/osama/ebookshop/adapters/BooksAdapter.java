package com.osama.osama.ebookshop.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osama.osama.ebookshop.BooksDiffCallback;
import com.osama.osama.ebookshop.R;
import com.osama.osama.ebookshop.databinding.BookListItemBinding;
import com.osama.osama.ebookshop.model.Book;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder>
{
    private OnItemClickListener listener;
    // let's create arraylist for books
    private ArrayList<Book> books = new ArrayList<>();

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        BookListItemBinding bookListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.book_list_item,viewGroup,false);

        return new BookViewHolder(bookListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int postion)
    {
        // get the current book
        Book book = books.get(postion);
        // pass this book instance to the layout
        bookViewHolder.bookListItemBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> newBooks)
    {
      //  this.books = books;
        // invoke the notifychanged to refresh the recyclerview
      //  notifyDataSetChanged();

        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new BooksDiffCallback(books,newBooks),false);
        books = newBooks;
        result.dispatchUpdatesTo(BooksAdapter.this);

    }

    class BookViewHolder extends RecyclerView.ViewHolder
    {
        private BookListItemBinding bookListItemBinding;

        // As we using data binding the notnull parameter of the constructor should be an instance of BookListItemBinding
        // Argument passed to the super class should be passed accordingly
        public BookViewHolder(@NonNull BookListItemBinding bookListItemBinding)
        {
            super(bookListItemBinding.getRoot());
            // assign passed instance to the local refernce
            this.bookListItemBinding = bookListItemBinding;
            bookListItemBinding.getRoot().setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int clickedPosition = getAdapterPosition();

                    if(listener != null && clickedPosition != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClicked(books.get(clickedPosition));
                    }

                }
            });
        }


    }

    // handle the click events to the recyclerview
    public interface OnItemClickListener
    {
        // when a user click on a recyclerview's item, we need to get the book instance showing on that clicked item
        void onItemClicked(Book book);
    }

    public void setListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

}
