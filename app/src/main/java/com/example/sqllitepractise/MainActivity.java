package com.example.sqllitepractise;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycleview;
    FloatingActionButton add_btn;
    MyDataBaseHelper MyDB ;

    ArrayList<String> book_id,book_title,book_author,book_pages;
    Adapter adpater;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recycleview =findViewById(R.id.recycleView);
        add_btn=findViewById(R.id.AddfloatingButton);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        MyDB =new MyDataBaseHelper(MainActivity.this);
        book_id =new ArrayList<>();
        book_pages =new ArrayList<>();
        book_author =new ArrayList<>();
        book_title =new ArrayList<>();

        StoreDataInArray();
        adpater=new Adapter(MainActivity.this, book_title,book_pages, book_author,book_id);
        recycleview.setAdapter(adpater);

        recycleview.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    void StoreDataInArray() {
        Cursor cursor =MyDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this,"there is No Data",Toast.LENGTH_SHORT).show();

        }else {
            while (cursor.moveToNext()) {
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));

            }
        }
    }
}