package com.example.bookinfoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    public static final String LOG_TAG = BookListActivity.class.getSimpleName();

    private String BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=isbn:9781461492986";
    ArrayList<Book> bookList;

    RecyclerView rvBookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        createFakeList();
        rvBookList = (RecyclerView)findViewById(R.id.recycler_view);
        BookListAdapter mAdapter = new BookListAdapter(this,bookList);
        rvBookList.setAdapter(mAdapter);
        rvBookList.setLayoutManager(new LinearLayoutManager(this));

    }


    private void createFakeList(){
        bookList = new ArrayList<Book>();
        ArrayList<String> authors = new ArrayList<String>();
        authors.add("kanade");
        authors.add("BBB");
        bookList.add(new Book("ABCD", authors));
        bookList.add(new Book("sidfnasodofi", authors));
        bookList.add(new Book("dwqdwqeqw", authors));
    }
}