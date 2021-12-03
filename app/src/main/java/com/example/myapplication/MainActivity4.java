package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Button bt = (Button) findViewById(R.id.button7);
        User user = new User(MainActivity4.this);
        String name = user.getUsername();
        String lang = user.getLang();
        System.out.println(lang);
        Toast.makeText(MainActivity4.this, "Welcome Back" + name, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity4.this, "Language:" + lang, Toast.LENGTH_SHORT).show();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity4.this, MainActivity3.class));
            }
        });
        Button bt1 = (Button) findViewById(R.id.button12);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity4.this, MainActivity5.class));
            }
        });
    }
        public void logout(View view)
        {
            new User(MainActivity4.this).removeuser();
            Intent intent = new Intent(MainActivity4.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }