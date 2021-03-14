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
    private OnItemClickListener mOnItemClickListener;

    public BookListAdapter(Context context,List<Book> bookList, OnItemClickListener onItemClickListener) {
        this.bookList = bookList;
        this.context = context;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.book_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,mOnItemClickListener);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView mCoverResourceId;
        TextView mTitle;
        TextView mAuthor;
        OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener)  {
            super(itemView);
            mCoverResourceId = (ImageView)itemView.findViewById(R.id.cover_pic_image_view);
            mTitle = (TextView) itemView.findViewById(R.id.title_text_view);
            mAuthor = (TextView)itemView.findViewById(R.id.author_text_view);
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int possition);
    }
}
