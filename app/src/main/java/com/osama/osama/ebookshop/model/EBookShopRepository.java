package com.osama.osama.ebookshop.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EBookShopRepository
{
    private CategoryDAO categoryDAO;
    private BookDAO bookDAO;

    private LiveData<List<Category>> categories;
    private LiveData<List<Book>> books;

    public EBookShopRepository(Application application)
    {
        // create a new bookdatabase instance
        BooksDatabase booksDatabase = BooksDatabase.getInstance(application);
        // get out dao instances using database instance
        categoryDAO = booksDatabase.categoryDAO();
        bookDAO = booksDatabase.bookDAO();
    }

    // generate a method to get all categories
    public LiveData<List<Category>> getCategories()
    {
        return categoryDAO.getAllCategories();
    }

    // generate a method to get all books belong to a given category
    public LiveData<List<Book>> getBooks(int categoryId)
    {
        return bookDAO.getBooks(categoryId);
    }

    // insert a new category
    public void insertCategory(final Category category)
    {
      //  new InsertCategoryAsyncTask(categoryDAO).execute(category);

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable()
        {
            @Override
            public void run()
            {
                categoryDAO.insert(category);
            }
        });
    }

    // insert a new book
    public void insertBook(final Book book)
    {
       // new InsertBookAsyncTask(bookDAO).execute(book);
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable()
        {
            @Override
            public void run()
            {
                bookDAO.insert(book);
            }
        });
    }

    // delete a category
    public void deleteCategory(Category category)
    {
        new DeleteCategoryAsyncTask(categoryDAO).execute(category);
    }

    // delete a book
    public void deleteBook(Book book)
    {
        new DeleteBookAsyncTask(bookDAO).execute(book);
    }

    // update a category
    public void updateCategory(Category category)
    {
        new UpdateCategoryAsyncTask(categoryDAO).execute(category);
    }

    // update a book
    public void updateBook(Book book)
    {
        new UpdateBookAsyncTask(bookDAO).execute(book);
    }


    // create an async task to insert a new category
    private static class InsertCategoryAsyncTask extends AsyncTask<Category,Void,Void>
    {
        private CategoryDAO categoryDAO;

        public InsertCategoryAsyncTask(CategoryDAO categoryDAO)
        {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories)
        {
            // this method takes category object as an array, But we know we are passing just one object
            // so let's get the first object of the array
            categoryDAO.insert(categories[0]);
            return null;
        }
    }

    // create an async task to insert a new book
    private static class InsertBookAsyncTask extends AsyncTask<Book,Void,Void>
    {
        private BookDAO bookDAO;

        public InsertBookAsyncTask(BookDAO bookDAO)
        {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books)
        {
            // this method takes category object as an array, But we know we are passing just one object
            // so let's get the first object of the array
            bookDAO.insert(books[0]);
            return null;
        }
    }

    // create an async task to delete category
    private static class DeleteCategoryAsyncTask extends AsyncTask<Category,Void,Void>
    {
        private CategoryDAO categoryDAO;

        public DeleteCategoryAsyncTask(CategoryDAO categoryDAO)
        {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories)
        {
            // this method takes category object as an array, But we know we are passing just one object
            // so let's get the first object of the array
            categoryDAO.delete(categories[0]);
            return null;
        }
    }

    // create an async task to delete book
    private static class DeleteBookAsyncTask extends AsyncTask<Book,Void,Void>
    {
        private BookDAO bookDAO;

        public DeleteBookAsyncTask(BookDAO bookDAO)
        {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books)
        {
            // this method takes category object as an array, But we know we are passing just one object
            // so let's get the first object of the array
            bookDAO.delete(books[0]);
            return null;
        }
    }

    // create an async task to update category
    private static class UpdateCategoryAsyncTask extends AsyncTask<Category,Void,Void>
    {
        private CategoryDAO categoryDAO;

        public UpdateCategoryAsyncTask(CategoryDAO categoryDAO)
        {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories)
        {
            // this method takes category object as an array, But we know we are passing just one object
            // so let's get the first object of the array
            categoryDAO.update(categories[0]);
            return null;
        }
    }

    // create an async task to update book
    private static class UpdateBookAsyncTask extends AsyncTask<Book,Void,Void>
    {
        private BookDAO bookDAO;

        public UpdateBookAsyncTask(BookDAO bookDAO)
        {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books)
        {
            // this method takes category object as an array, But we know we are passing just one object
            // so let's get the first object of the array
            bookDAO.update(books[0]);
            return null;
        }
    }
}
