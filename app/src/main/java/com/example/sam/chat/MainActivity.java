package com.example.sam.chat;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }
    public void signUpOn(View view)
    {
        Intent intent=new Intent(MainActivity.this,signUp.class);
        startActivity(intent);
    }
    public void signInOn(View view)
    {
        Intent intent=new Intent(MainActivity.this,first.class);
        startActivity(intent);
    }
}
