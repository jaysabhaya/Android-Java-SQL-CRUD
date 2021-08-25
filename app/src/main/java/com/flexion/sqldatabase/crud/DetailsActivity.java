package com.flexion.sqldatabase.crud;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    TextView image, name, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        image = findViewById(R.id.imagedetail);
        name = findViewById(R.id.namedetail);
        number = findViewById(R.id.numberdetails);

        image.setText(getIntent().getStringExtra("name"));
        name.setText(getIntent().getStringExtra("name"));
        number.setText(getIntent().getStringExtra("number"));
    }

}