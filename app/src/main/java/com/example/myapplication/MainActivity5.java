package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Button bt = (Button) findViewById(R.id.button17);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity5.this, MainActivity3.class));
            }
        });
    }
    public void logout(View view)
    {
        new User(MainActivity5.this).removeuser();
        Intent intent=new Intent(MainActivity5.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}