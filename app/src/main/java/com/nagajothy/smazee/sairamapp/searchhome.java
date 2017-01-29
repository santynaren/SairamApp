package com.nagajothy.smazee.sairamapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class searchhome extends AppCompatActivity {
private String keyword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchhome);
        EditText search_query = (EditText)findViewById(R.id.search);
        Button search_button = (Button)findViewById(R.id.search_button);
        keyword = search_query.getText().toString();



    }
}
