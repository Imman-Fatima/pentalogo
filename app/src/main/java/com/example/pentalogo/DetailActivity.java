package com.example.pentalogo;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ((TextView) findViewById(R.id.tvName)).setText("Name: " + getIntent().getStringExtra("name"));
        ((TextView) findViewById(R.id.tvEmail)).setText("Email: " + getIntent().getStringExtra("email"));
        ((TextView) findViewById(R.id.tvAddress)).setText("Address: " + getIntent().getStringExtra("address"));
        ((TextView) findViewById(R.id.tvPassword)).setText("Password: " + getIntent().getStringExtra("password"));
    }
}