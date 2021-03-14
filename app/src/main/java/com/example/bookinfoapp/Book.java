package com.example.bookinfoapp;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class Book {
    private String mTitle;
    private ArrayList<String> mAuthors;
    private String mCoverResourceId;
    private boolean hasCoverImage = false;

    public Book(String mTitle, ArrayList<String> mAuthors) {
        this.mTitle = mTitle;
        this.mAuthors = mAuthors;
    }

    public Book(String mTitle, ArrayList<String> mAuthors, String mCoverResourceId) {
        this.mTitle = mTitle;
        this.mAuthors = mAuthors;
        this.mCoverResourceId = mCoverResourceId;
        this.hasCoverImage = true;
    }

    public ArrayList<String> getmAuthor() {
        return mAuthors;
    }

    public String getmAuthorsString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< mAuthors.size();i++){
            sb.append(mAuthors.get(i) + " ");
        }
        return sb.toString();
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmCoverResourceId() {
        return mCoverResourceId;
    }

    public boolean hasCoverImage(){
        return hasCoverImage;
    }

    @Override
    public String toString() {
        return "Book{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthors=" + mAuthors +
                ", mCoverResourceId='" + mCoverResourceId + '\'' +
                ", hasCoverImage=" + hasCoverImage +
                '}';
    }
}
