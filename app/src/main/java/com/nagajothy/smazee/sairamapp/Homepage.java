package com.nagajothy.smazee.sairamapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {
    EditText emp_id , password ;
    Button login ;
    private String TAG = Homepage.class.getSimpleName();
    String tid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        emp_id = (EditText) findViewById(R.id.emp_id);

        login = (Button)findViewById(R.id.login_button);


        // Code for implementing the login and user check



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tid = emp_id.getText().toString();
                Intent hometostudent = new Intent(Homepage.this,StudentListActivity.class);
                hometostudent.putExtra("teacher",tid);
                startActivity(hometostudent);
            }
        });

    }
}
