package com.osama.osama.ebookshop.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.osama.osama.ebookshop.model.Book;
import com.osama.osama.ebookshop.model.Category;
import com.osama.osama.ebookshop.model.EBookShopRepository;

import java.util.List;

// we extend AndroidViewModel when we want to use the application context
// otherwise we extend the ViewModel,
// but in our project when we are creating an instance of our repository we pass the context as the application as an argument
public class MainActivityViewModel extends AndroidViewModel
{
    // we need a repository instance to invoke the repository methods
    public EBookShopRepository eBookShopRepository;

    // we need a livedata of list of categories to get all categories
    // then we create a getter method for it
    private LiveData<List<Category>> allCategories;

    // we need list of books that belong to a selected category
    // then we create a getter method for it
    private LiveData<List<Book>> booksOfASelectedCategory;

    public MainActivityViewModel(@NonNull Application application)
    {
        super(application);
        eBookShopRepository = new EBookShopRepository(application);
    }

    public LiveData<List<Category>> getAllCategories()
    {
        // here we can get the list from the repository
        allCategories = eBookShopRepository.getCategories();
        return allCategories;
    }

    public LiveData<List<Book>> getBooksOfASelectedCategory(int categoryId)
    {
        booksOfASelectedCategory = eBookShopRepository.getBooks(categoryId);
        return booksOfASelectedCategory;
    }

    // add new book
    public void addNewBook(Book book)
    {
        eBookShopRepository.insertBook(book);
    }

    // delete book
    public void deleteBook(Book book)
    {
        eBookShopRepository.deleteBook(book);
    }

    // update book
    public void updateBook(Book book)
    {
        eBookShopRepository.updateBook(book);
    }

}
