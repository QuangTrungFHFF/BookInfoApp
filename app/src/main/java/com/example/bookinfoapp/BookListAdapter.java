package com.example.bookinfoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    public static final String LOG_TAG = BookListAdapter.class.getSimpleName();

    private List<Book> bookList;
    private Context context;

    public BookListAdapter(Context context,List<Book> bookList) {
        this.bookList = bookList;
        this.context = context;
    }

    @NonNull
    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.book_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.ViewHolder holder, int position) {
        Book currentBook = bookList.get(position);
        holder.mAuthor.setText(currentBook.getmAuthorsString());
        holder.mTitle.setText(currentBook.getmTitle());


    }
    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mCoverResourceId;
        TextView mTitle;
        TextView mAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCoverResourceId = (ImageView)itemView.findViewById(R.id.cover_pic_image_view);
            mTitle = (TextView) itemView.findViewById(R.id.title_text_view);
            mAuthor = (TextView)itemView.findViewById(R.id.author_text_view);
        }
    }
}
