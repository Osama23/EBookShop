package com.osama.osama.ebookshop;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.osama.osama.ebookshop.model.Book;

import java.util.ArrayList;

// DiffUtil can calculate the difference between two lists
// and output a list of update operations that converts the first list into the second one
// It can be used to update a list of a recyclerview
public class BooksDiffCallback extends DiffUtil.Callback
{
    // Now, We need to define two arraylist for the old one and the new one
    ArrayList<Book> oldBooksList;
    ArrayList<Book> newBooksList;

    public BooksDiffCallback(ArrayList<Book> oldBooksList, ArrayList<Book> newBooksList)
    {
        this.oldBooksList = oldBooksList;
        this.newBooksList = newBooksList;
    }

    @Override
    public int getOldListSize()
    {
        // Here we should return the old list, but if the old list is null this will return a null pointer exception
        // To avoid the null pointer exception, we will do a little validation using isEqualTo operator
        return oldBooksList == null ? 0 : oldBooksList.size();
    }

    @Override
    public int getNewListSize()
    {
        return newBooksList == null ? 0 : newBooksList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldPosition, int newPosition)
    {
        // If the ids are equal this will return true. So this will be done automatically for all the positions.
        // If one or more operation returns false that indicates items are not the same.
        // We don't have to worry about what to do next if items are not the same. They have written algorithms in the superclass for that.
        // Updating the recyclerview adapter part will be automatically done.
        return oldBooksList.get(oldPosition).getBookId() == newBooksList.get(newPosition).getBookId();
    }

    @Override
    public boolean areContentsTheSame(int oldBookPosition, int newBookPosition)
    {
        return oldBooksList.get(oldBookPosition).equals(newBooksList.get(newBookPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldBookPosition, int newBookPosition)
    {
        return super.getChangePayload(oldBookPosition, newBookPosition);
    }
}
