package com.flexion.sqldatabase.crud;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton createNew;
    RecyclerView recyclerView;

    SearchView searchView;

    ArrayList<User> arrayList = new ArrayList();

    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNew = findViewById(R.id.createNew);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,InsertActivity.class));
                finish();
            }
        });

        myDatabase = new MyDatabase(this);

        Cursor cursor = myDatabase.selectData();
        while (cursor.moveToNext()) {
            int userid = cursor.getInt(0);
            String name = cursor.getString(1);
            String contact = cursor.getString(2);
            User user = new User(userid, name, contact);
            arrayList.add(user);
        }
        MainAdapter mainAdapter = new MainAdapter(this, arrayList, myDatabase);
        recyclerView.setAdapter(mainAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<User> templist = new ArrayList<>();

                for (int i = 0; i < arrayList.size(); i++) {

                    String name = arrayList.get(i).getName().toLowerCase();
                    String contact = arrayList.get(i).getContact();

                    if (name.contains(newText.toLowerCase().trim()) || contact.contains(newText.toLowerCase().trim())) {
                        templist.add(arrayList.get(i));
                    }
                }

                MainAdapter mainAdapter = new MainAdapter(MainActivity.this, templist, myDatabase);
                recyclerView.setAdapter(mainAdapter);

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchmenu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<User> templist = new ArrayList<>();

                for (int i = 0; i < arrayList.size(); i++) {

                    String name = arrayList.get(i).getName().toLowerCase();
                    String contact = arrayList.get(i).getContact();

                    if (name.contains(newText.toLowerCase().trim()) || contact.contains(newText.toLowerCase().trim())) {
                        templist.add(arrayList.get(i));
                    }
                }

                MainAdapter mainAdapter = new MainAdapter(MainActivity.this, templist, myDatabase);
                recyclerView.setAdapter(mainAdapter);

                return true;
            }
        });

        return true;
    }


}