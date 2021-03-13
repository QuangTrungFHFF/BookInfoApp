package com.example.bookinfoapp;

public class Book {
    private String mTitle;
    private String mAuthor;
    private String mCoverResourceId;

    public Book(String mTitle, String mAuthor) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
    }

    public Book(String mTitle, String mAuthor, String mCoverResourceId) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mCoverResourceId = mCoverResourceId;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmCoverResourceId() {
        return mCoverResourceId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                '}';
    }
}
