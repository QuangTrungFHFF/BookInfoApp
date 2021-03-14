package com.example.bookinfoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>>, BookListAdapter.OnItemClickListener {

    public static final String LOG_TAG = BookListActivity.class.getSimpleName();

    private static final int BOOK_LOADER_ID = 1;

    private String BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&maxResults=5";
    private ArrayList<Book> bookList = new ArrayList<Book>();
    private BookListAdapter mAdapter;
    private RecyclerView rvBookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);


        rvBookList = (RecyclerView)findViewById(R.id.recycler_view);
        mAdapter = new BookListAdapter(this,bookList,this);
        rvBookList.setAdapter(mAdapter);
        rvBookList.setLayoutManager(new LinearLayoutManager(this));

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(BOOK_LOADER_ID,null,this);


    }

    private void updateUI(ArrayList<Book> list){
        bookList= list;

        mAdapter = new BookListAdapter(this,bookList,this);
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

    @NonNull
    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        return new BookLoader(this,BOOK_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Book>> loader, ArrayList<Book> data) {
        updateUI(data);
        Toast.makeText(this,"here",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Book>> loader) {

    }

    @Override
    public void onItemClick(int possition) {
        Book currentBook = bookList.get(possition);
        Toast.makeText(this,currentBook.toString(),Toast.LENGTH_SHORT).show();
    }
}