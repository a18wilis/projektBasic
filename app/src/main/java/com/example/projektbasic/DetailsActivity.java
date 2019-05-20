package com.example.projektbasic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import android.content.Intent;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.action_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Get the Intent that started this activity and extract the strings
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String info = b.getString("info");
        final String fact = b.getString("fact");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textFromMain);
        textView.setText(info);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, fact, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }


}
