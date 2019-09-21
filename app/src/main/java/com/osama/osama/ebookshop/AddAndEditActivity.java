package com.osama.osama.ebookshop;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.osama.osama.ebookshop.databinding.ActivityAddAndEditBinding;
import com.osama.osama.ebookshop.model.Book;

public class AddAndEditActivity extends AppCompatActivity
{
    private Book book;
    // In Java, static varaiables are application level so, you can use them from any other classs in the project
    public static final String BOOK_ID = "bookId";
    public static final String BOOK_NAME = "bookName";
    public static final String UNIT_PRICE = "unitPrice";
    private ActivityAddAndEditBinding activityAddAndEditBinding;
    private AddAndEditActivityClickHandlers addAndEditActivityClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit);

        book = new Book();
        activityAddAndEditBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_and_edit);
        // bind the book instance
        activityAddAndEditBinding.setBook(book);

        addAndEditActivityClickHandlers = new AddAndEditActivityClickHandlers(this);
        // bind it
        activityAddAndEditBinding.setClickHandler(addAndEditActivityClickHandlers);

        // there are two scenairos for this activity
        // By checking intent values we can recognize the purpose of this activity
        Intent intent = getIntent();
        // if this intent is coming from the result of clicking on recyclerview this should have a book_id
        if(intent.hasExtra(BOOK_ID))
        {
            setTitle("Update Book");
            book.setBookName(intent.getStringExtra(BOOK_NAME));
            book.setUnitPrice(intent.getStringExtra(UNIT_PRICE));
        }
        else
        {
            setTitle("Add New Book");
        }
    }

    public class AddAndEditActivityClickHandlers
    {
        Context context;
        public AddAndEditActivityClickHandlers(Context context)
        {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view)
        {
            if(book.getBookName()==null)
            {
                Toast.makeText(getApplicationContext(),"Name field cannot be empty",Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent intent = new Intent();
                intent.putExtra(BOOK_NAME,book.getBookName());
                intent.putExtra(UNIT_PRICE,book.getUnitPrice());
                setResult(RESULT_OK,intent);
                finish();
            }
        }

    }
}
