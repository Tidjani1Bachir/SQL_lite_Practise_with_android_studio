package com.example.sqllitepractise;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

   private Context context;
   int position;
    private ArrayList book_title , book_pages ,book_author ,book_id;
    private MyViewHolder holder;

    Adapter(Context context
            ,ArrayList book_title,
                        ArrayList book_pages,ArrayList book_author,
                        ArrayList book_id
                        ) {
        this.context=context;
        this.book_title=book_title;
        this.book_id=book_id;
        this.book_author=book_author;
        this.book_pages=book_pages;

    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate =LayoutInflater.from(context);
        View view = inflate.inflate(R.layout.my_row,parent,true);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder,  int position) {


        holder.book_id_txt.setText(String.valueOf(book_id.get(position)));
        holder.book_author_txt.setText(String.valueOf(book_author.get(position)));
        holder.book_title_txt.setText(String.valueOf(book_title.get(position)));
        holder.book_pages_txt.setText(String.valueOf(book_pages.get(position)));



    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
          TextView book_id_txt ,book_title_txt,book_pages_txt,book_author_txt;

          LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt =itemView.findViewById(R.id.book_id_text);
            book_title_txt =itemView.findViewById(R.id.book_title_txt);
            book_pages_txt =itemView.findViewById(R.id.book_pages_txt);
            book_author_txt =itemView.findViewById(R.id.book_author_txt);
            mainLayout  =itemView.findViewById(R.id.mainLayout);
        }
    }
}
