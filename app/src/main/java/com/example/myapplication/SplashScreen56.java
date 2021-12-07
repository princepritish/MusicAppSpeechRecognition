package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen56 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen56);
        Bundle extras = getIntent().getExtras();

        String res=extras.getString("mood");
        Thread thread=new Thread(){
            public void run() {
                try {
                    sleep(5000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            };

        Thread thread1=new Thread()
        {
            public void run() {
                try {
                    Intent intent = new Intent(SplashScreen56.this, MainActivity5.class);
                    intent.putExtra("mood",res);
                    sleep(5000);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            };
        thread.start();
        thread1.start();
    }
}