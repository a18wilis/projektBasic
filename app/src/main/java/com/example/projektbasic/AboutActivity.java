package com.example.projektbasic;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.action_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        final String about = intent.getStringExtra(MainActivity.info);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textFromMain);
        textView.setText(about);

    }


}
