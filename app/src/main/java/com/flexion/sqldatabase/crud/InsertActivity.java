package com.flexion.sqldatabase.crud;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class InsertActivity extends AppCompatActivity {

    Button submit;
    TextInputEditText ename,econtact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        submit = findViewById(R.id.submit);
        ename = findViewById(R.id.name);
        econtact = findViewById(R.id.contact);

        MyDatabase myDatabase = new MyDatabase(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = ename.getText().toString();
                String contact = econtact.getText().toString();

                myDatabase.insertData(name, contact);

                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
    }

}