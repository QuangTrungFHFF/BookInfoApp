package com.example.bookinfoapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.net.URL;
import java.util.ArrayList;

public class BookLoader extends AsyncTaskLoader<ArrayList<Book>> {

    Context context;
    String mUrl;

    public BookLoader(@NonNull Context context, String mUrl) {
        super(context);
        this.context = context;
        this.mUrl = mUrl;
    }

    @Nullable
    @Override
    public ArrayList<Book> loadInBackground() {
        if(mUrl == null){
            return null;
        }
        ArrayList<Book> bookList = QueryUtils.getBookFromServer(mUrl);

        return bookList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
